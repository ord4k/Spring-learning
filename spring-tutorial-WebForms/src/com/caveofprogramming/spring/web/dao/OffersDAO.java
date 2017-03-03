package com.caveofprogramming.spring.web.dao;

import java.util.List;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component("offersDao")
@Transactional
public class OffersDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers() {

		Criteria crit = session().createCriteria(Offer.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));

		return crit.list();

		/*
		 * in the old way return jdbc.
		 * query("select * from offers, users where offers.username=users.username and users.enabled=true"
		 * , new OfferRowMapper());
		 */
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers(String username) {

		Criteria crit = session().createCriteria(Offer.class);
		crit.createAlias("user", "u");

		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.eq("u.username", username));

		return crit.list();
	}

	public void saveOrUpdate(Offer offer) {
		session().save(offer);

	}

	public boolean delete(int id) {

		// this is not SQL query, this is hibernate SQL language!!
		Query query = session().createQuery("delete from Offer where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;

	}

	public Offer getOffer(int id) {
		// with this approach the method returns null if offer doesnt exist
		Criteria crit = session().createCriteria(Offer.class);
		crit.createAlias("user", "u");

		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.idEq(id));

		return (Offer) crit.uniqueResult();

		/*
		 * jdbc implementation MapSqlParameterSource params = new
		 * MapSqlParameterSource(); params.addValue("id", id);
		 * 
		 * return jdbc.queryForObject(
		 * "select * from offers, users where offers.username=users.username and users.enabled=true and id=:id"
		 * , params, new OfferRowMapper());
		 */

	}

}
