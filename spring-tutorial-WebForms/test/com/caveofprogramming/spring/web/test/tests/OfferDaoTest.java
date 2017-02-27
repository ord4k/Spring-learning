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
@ContextConfiguration(locations = { "classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
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

		User user = new User("michal", "michal o", "haslo", "michal@op.pl", true, "user");

		usersDao.create(user);

		Offer offer = new Offer("This is test offer", user);

		offersDao.create(offer);

		List<Offer> offers = offersDao.getOffers(user.getUsername());

		assertEquals("Should be one offer in database", 1, offers.size());

		assertEquals("Offer retrieved should match offer created", offer, offers.get(0));

		// Get the offer with ID filled in
		offer = offers.get(0);

		offer.setText("Update for test purpose");
		assertTrue("Offer update should return true", offersDao.update(offer));

		Offer updated = offersDao.getOffer(offer.getId());
		
		assertEquals("Updated offer should match retrieved updated offer", offer, updated);
		
		Offer offer2 = new Offer("This is test offer2", user);
		
		offersDao.create(offer2);
		
		List<Offer> userOffers = offersDao.getOffers();
		
		assertEquals("Should be two offers", 2, userOffers.size());

		List<Offer> secondList = offersDao.getOffers();
		for (Offer current : secondList) {
			Offer retrieved = offersDao.getOffer(current.getId());

			assertEquals("Offer by ID should match offer from the list", current, retrieved);
		}
		
		offersDao.delete(offer.getId());
		
		List<Offer> finalList = offersDao.getOffers();

		assertEquals("Offers lists should containt one offer", 1, finalList.size());

	}

}
