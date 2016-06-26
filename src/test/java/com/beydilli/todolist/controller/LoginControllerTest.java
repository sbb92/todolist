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
import com.beydilli.todolist.dao.UserDao;
import com.beydilli.todolist.domain.SessionKey;
import com.beydilli.todolist.model.User;

public class LoginControllerTest extends TodolistApplicationTests {
	//this is a standalone unit testing for controller. 

	private MockMvc mvc;


	@Autowired
	private MockHttpSession session;


	@Mock
	private UserDao userDao;

	@InjectMocks
	LoginController loginController;

	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}

	@Test
	public void login() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/login")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.view().name("views/login"));

		session.setAttribute(SessionKey.USER_ID.toString(), 5L);
		mvc.perform(MockMvcRequestBuilders.get("/login").session(session)).andExpect(status().is3xxRedirection()).andExpect(MockMvcResultMatchers.redirectedUrl("home"));

	}

	@Test
	public void signUp() throws Exception {

		Mockito.when(userDao.save(Matchers.isA(User.class))).thenReturn(new User());
		mvc.perform(MockMvcRequestBuilders.post("/login/signUp").param("name", "Ahmet").param("surname", "Yılmaz").param("email", "ahmet@gmail.com").param("password", "1234")
				.accept("application/json")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Kullanıcınız başarı ile oluşturulmuştur."));

		mvc.perform(MockMvcRequestBuilders.post("/login/signUp").param("name", "Ahmet").param("surname", "Yılmaz").param("email", "ahmetgmail.com").param("password", "1234")
				.accept("application/json")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("FAILED"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Kullanıcı bilgilerini lütfen doğru giriniz."));

	}

	@Test
	public void signIn() throws Exception {

		Mockito.when(userDao.findByEmailAndPassword(Matchers.isA(String.class), Matchers.isA(String.class))).thenReturn(new User());
		mvc.perform(MockMvcRequestBuilders.post("/login/signIn").param("email", "ahmet@gmail.com").param("password", "1234").accept("application/json")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS")).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Başarı ile giriş yaptınız."));

		mvc.perform(MockMvcRequestBuilders.post("/login/signIn").param("email", "ahmetgmail.com").param("password", "1234").accept("application/json")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("FAILED"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Kullanıcı bilgilerini lütfen doğru giriniz."));

		Mockito.when(userDao.findByEmailAndPassword(Matchers.isA(String.class), Matchers.isA(String.class))).thenReturn(null);
		mvc.perform(MockMvcRequestBuilders.post("/login/signIn").param("email", "ahmet@gmail.com").param("password", "1234").accept("application/json")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("FAILED"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Kullanıcı bilgilerini lütfen doğru giriniz."));

	}

	@Test
	public void logout() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/login/logout").accept("application/json")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS")).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Başarı ile çıkış yaptınız."));
	}
}
