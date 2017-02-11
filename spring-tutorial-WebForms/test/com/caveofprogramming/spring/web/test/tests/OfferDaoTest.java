package com.caveofprogramming.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;


@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTest {
	

	@Autowired
	private OffersDao offersDao;
	
	@Autowired
	private UsersDao usersDao;
	
	
	@Autowired
	private DataSource dataSource;
		
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");

		
	}
	
	@Test
	public void testCreateOffer() {
		
		User user = new User("michal","haslo","haslo","michal@op.pl", true, "user", "admin");
		
		assertTrue("User creation should return true", usersDao.create(user));
		
		Offer offer = new Offer("This is test offer",user);
		
		assertTrue("Offer creation should return true", offersDao.create(offer));
		
		List<Offer> offers = offersDao.getOffers();
		
		assertEquals("Should be one offer in database", 1, offers.size());
		
		assertEquals("Offer retrieved should match offer created", offer, offers.get(0));
		
		//Get the offer with ID filled in
		offer = offers.get(0);
		
		offer.setText("Update for test purpose");
		assertTrue("Offer update should return true", offersDao.update(offer));
		
		Offer updated = offersDao.getOffer(offer.getId());
		
		assertEquals("Updated offer should match retrieved updated offer", offer, updated);
		
		offersDao.delete(offer.getId());
		
		List<Offer> empty = offersDao.getOffers();
		
		assertEquals("OFfers list should be empty", 0, empty.size());
		
		
		
	}

}
