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

import com.caveofprogramming.spring.web.dao.MessagesDao;
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
	private MessagesDao messagesDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("Michal1", "Michal_Nadolny1", "hello_there1", "michal1@op.pl", true, "ROLE_USER");
	private User user2 = new User("Michal2", "Michal_Nadolny2", "hello_there2", "michal2@op.pl", true, "ROLE_ADMIN");
	private User user3 = new User("Michal3", "Michal_Nadolny3", "hello_there3", "michal3@op.pl", true, "lbierator");
	private User user4 = new User("Michal4", "Michal_Nadolny4", "hello_there4", "michal4@op.pl", false, "user");

	private Offer offer1 = new Offer(user1, "This is a test offer nr 1");
	private Offer offer2 = new Offer(user1, "This is a test offer nr 2");
	private Offer offer3 = new Offer(user2, "This is a test offer nr 3");
	private Offer offer4 = new Offer(user3, "This is a test offer nr 4");
	private Offer offer5 = new Offer(user3, "This is a test offer nr 5");
	private Offer offer6 = new Offer(user3, "This is a test offer nr 6");
	private Offer offer7 = new Offer(user4, "This is a test offer nr 7");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");

	}

	@Test
	public void testGetUsername() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		List<Offer> offers1 = offersDao.getOffers(user3.getUsername());
		assertEquals("Should be three offers", 3, offers1.size());

		List<Offer> offers2 = offersDao.getOffers("zess");
		assertEquals("Should be zero offers", 0, offers2.size());

		List<Offer> offers3 = offersDao.getOffers(user2.getUsername());
		assertEquals("Should be three offers", 1, offers3.size());

	}

	@Test
	public void testCreate() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.saveOrUpdate(offer1);
		List<Offer> offers1 = offersDao.getOffers();
		assertEquals("Should be a single offer for enabled true", 1, offers1.size());
		assertEquals("Retrieved offer should equals inserted offer", offer1, offers1.get(0));

		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		List<Offer> offers = offersDao.getOffers();
		assertEquals("Should be six for enabled true", 6, offers.size());

	}
	
	@Test
	public void testDelete() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		
		
		Offer retrieved1 = offersDao.getOffer(offer2.getId());
		assertNotNull("Offer with id:" +retrieved1 + "should be not null",retrieved1);
		
		offersDao.delete(offer2.getId());
		Offer retrieved2 = offersDao.getOffer(offer2.getId());
		assertNull("Offer with id:" +retrieved2 + "should be null, deleted",retrieved2);
		
	}

	@Test
	public void testSaveOrUpdate() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		offer3.setText("This is updatefd offer3");
		offersDao.saveOrUpdate(offer3);

		Offer retrieveved = offersDao.getOffer(offer3.getId());
		assertEquals("Retrieved offer should be updated", offer3, retrieveved);

	}
	@Test
	public void testGetOfferById() {
		
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		Offer retrieved1 = offersDao.getOffer(offer1.getId());
		assertEquals("OFfers should match", offer1,retrieved1);
		
		Offer retrieved2 = offersDao.getOffer(offer7.getId());
		assertNull("Offer should not exist for not enabled user: "+user4.getUsername(), retrieved2);
		
		
	}

	

}
