package com.caveofprogramming.spring.web.test.tests;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caveofprogramming.spring.web.dao.Message;
import com.caveofprogramming.spring.web.dao.MessagesDao;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTest {

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


	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testSave() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

	Message message1 = new Message("Test subject 1", "Test content 1", "Isac Newton","IsacNewton@op.pl", user1.getUsername());
		messagesDao.saveOrUpdate(message1);
	
		
	}

}
