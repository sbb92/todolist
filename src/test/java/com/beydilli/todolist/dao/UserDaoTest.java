package com.beydilli.todolist.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

import com.beydilli.todolist.TodolistApplicationTests;
import com.beydilli.todolist.model.User;

@Transactional
public class UserDaoTest extends TodolistApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	@Transactional
	@Rollback(true)
	public void crudTest() throws Exception {
		User user = new User("Ahmet", "Yılmaz", "ahmet@ahmet.com", "1234");
		user = userDao.save(user);
		assertNotNull(user.getId());
		
		User findByEmailAndPassword = userDao.findByEmailAndPassword("ahmet@ahmet.com", "1234");
		assertNotNull(findByEmailAndPassword);

		User findById = userDao.findById(user.getId());
		assertNotNull(findById);
				
		userDao.deleteById(findById.getId());
	}
}
