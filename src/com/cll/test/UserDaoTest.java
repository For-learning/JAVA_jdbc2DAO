package com.cll.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.cll.domain.User;
import com.cll.impl.DaoFactory;
import com.cll.inte.UserDao;

public class UserDaoTest {

	@Test
	public void testAddUser() {
		User user = new User();

		user.setId("3");
		user.setName("xiaozheng");
		user.setBirthday(new Date());
		user.setMoney(366f);

		UserDao userDao = DaoFactory.getInstance().getUserDao();
		userDao.addUser(user);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUser() {
		fail("Not yet implemented");
	}

}
