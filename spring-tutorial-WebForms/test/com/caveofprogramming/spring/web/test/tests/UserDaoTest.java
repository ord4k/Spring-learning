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
public class UserDaoTest {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from users");
		jdbc.execute("delete from offers");

	}

	@Test
	public void testCreateUser() {

		User user = new User("michal", "haslo", "haslo", "michal@op.pl", true, "user", "admin");

		assertTrue("User creation should return true", usersDao.create(user));

		List<User> users = usersDao.getAllUsers();

		assertEquals("Number of users should be 1", 1, users.size());

		assertTrue("User should exist", usersDao.exists(user.getUsername()));

		assertEquals("User created should be identicial to retrieved user", user, users.get(0));

	}

}
