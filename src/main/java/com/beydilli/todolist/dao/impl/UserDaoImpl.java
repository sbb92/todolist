package com.beydilli.todolist.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beydilli.todolist.dao.UserDao;
import com.beydilli.todolist.model.User;


@Repository("userDao")
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public User findByEmailAndPassword(String email, String password) {
		return (User) currentSession().getNamedQuery("User.getUserByEmailAndPassword").setParameter("email", email).setParameter("password", password).uniqueResult();
	}

}
