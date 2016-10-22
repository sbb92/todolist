package com.beydilli.todolist.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.beydilli.todolist.TodolistApplicationTests;
import com.beydilli.todolist.dao.NoteDao;
import com.beydilli.todolist.dao.UserDao;
import com.beydilli.todolist.domain.SessionKey;
import com.beydilli.todolist.model.Note;
import com.beydilli.todolist.model.User;

public class HomeControllerTest extends TodolistApplicationTests {
	//this is a standalone unit testing for controller. 
	private MockMvc mvc;


	@Autowired
	private MockHttpSession session;


	@Mock
	private UserDao userDao;

	@Mock
	private NoteDao noteDao;

	@InjectMocks
	HomeController homeController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@Test
	public void home() throws Exception {
		session.setAttribute(SessionKey.USER_ID.toString(), 5L);
		mvc.perform(MockMvcRequestBuilders.get("/home").session(session)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.view().name("views/home"));
	}

	@Test
	public void redirectLogin() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/home")).andExpect(status().is3xxRedirection()).andExpect(MockMvcResultMatchers.redirectedUrl("login"));
	}

	@Test
	public void addNote() throws Exception {
		session.setAttribute(SessionKey.USER_ID.toString(), 5L);
		Mockito.when(userDao.findById(Matchers.isA(Long.class))).thenReturn(new User());
		Mockito.when(userDao.save(Matchers.isA(User.class))).thenReturn(new User());

		mvc.perform(MockMvcRequestBuilders.post("/home/addNote").session(session).param("noteName", "Yeni Note").accept("application/json")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS")).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("null"));

		mvc.perform(MockMvcRequestBuilders.post("/home/addNote").param("noteName", "Yeni Note").accept("application/json")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("FAILED")).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Lüften giriş yapınız."));

	}

	@Test
	public void updateStatus() throws Exception {

		session.setAttribute(SessionKey.USER_ID.toString(), 5L);

		Mockito.when(userDao.findById(Matchers.isA(Long.class))).thenReturn(new User());
		Mockito.when(userDao.save(Matchers.isA(User.class))).thenReturn(new User());
		Mockito.when(noteDao.findById(Matchers.isA(Long.class))).thenReturn(new Note());
		Mockito.when(noteDao.save(Matchers.isA(Note.class))).thenReturn(new Note());

		mvc.perform(MockMvcRequestBuilders.post("/home/updateStatus").session(session).param("noteId", "1").param("status", "1").accept("application/json"))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Başarı ile güncellendi."));

		mvc.perform(MockMvcRequestBuilders.post("/home/updateStatus").param("noteId", "1").param("status", "1").accept("application/json")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("FAILED")).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Lüften giriş yapınız."));
	}

	@Test
	public void removeNote() throws Exception {
		session.setAttribute(SessionKey.USER_ID.toString(), 5L);

		Mockito.when(userDao.findById(Matchers.isA(Long.class))).thenReturn(new User());
		Mockito.when(userDao.save(Matchers.isA(User.class))).thenReturn(new User());
		
		mvc.perform(MockMvcRequestBuilders.post("/home/removeNote").session(session).param("noteId", "1").accept("application/json"))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Başarı ile silindi."));

		mvc.perform(MockMvcRequestBuilders.post("/home/removeNote").param("noteId", "1").accept("application/json")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("FAILED")).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Lüften giriş yapınız."));
	}
}
