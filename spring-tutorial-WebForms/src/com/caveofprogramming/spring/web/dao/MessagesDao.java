package com.caveofprogramming.spring.web.dao;

import java.util.List;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Repository
@Transactional
@Component("messagesDao")
public class MessagesDao {

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
	public List<Message> getMessages() {

		Criteria crit = session().createCriteria(Message.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));

		return crit.list();


	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {

		Criteria crit = session().createCriteria(Message.class);
		crit.add(Restrictions.eq("username", username));

		return crit.list();
	}

	public void saveOrUpdate(Message message) {
		System.out.println(message);
		session().saveOrUpdate(message);
		

	}

	public boolean delete(int id) {

		// this is not SQL query, this is hibernate SQL language!!
		Query query = session().createQuery("delete from Message where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;

	}

	public Message getMessage(int id) {
		// with this approach the method returns null if message doesnt exist
		Criteria crit = session().createCriteria(Message.class);
		crit.add(Restrictions.idEq(id));

		return (Message) crit.uniqueResult();

	

	}

}
