package com.beydilli.todolist.dao;

import com.beydilli.todolist.model.User;

public interface UserDao extends BaseDao<User> {
	public User findByEmailAndPassword(String email, String password);
}
