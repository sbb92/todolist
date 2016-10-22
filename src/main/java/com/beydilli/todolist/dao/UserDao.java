package com.beydilli.todolist.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beydilli.todolist.model.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmail(String email);

	public User findById(Long id);

	public void deleteById(Long id);

}
