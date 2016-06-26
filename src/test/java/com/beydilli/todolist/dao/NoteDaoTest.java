package com.beydilli.todolist.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

import com.beydilli.todolist.TodolistApplicationTests;
import com.beydilli.todolist.model.Note;
import com.beydilli.todolist.model.User;

@Transactional
public class NoteDaoTest extends TodolistApplicationTests {

	@Autowired
	private NoteDao noteDao;

	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void crudTest() throws Exception {
		User user = new User("Ahmet", "Yılmaz", "ahmet@ahmet.com", "1234");
		user = userDao.save(user);
		
		Note note= new Note("Test ",user, true);
		note = noteDao.save(note);
		assertNotNull(note.getId());
		
		Note findById = noteDao.findById(note.getId());
		assertNotNull(findById);
	
		noteDao.deleteById(findById.getId());
		userDao.deleteById(user.getId());
	}
}
