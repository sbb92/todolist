package com.beydilli.todolist.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.beydilli.todolist.TodolistApplicationTests;
import com.beydilli.todolist.domain.SessionKey;

public class MainControllerTest extends TodolistApplicationTests {
	private MockMvc mvc;
	@Autowired
	WebApplicationContext wac;
	@Autowired
	MockHttpSession session;
	@Autowired
	MockHttpServletRequest request;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new MainController()).build();
	}

	@Test
	public void redirectLogin() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().is3xxRedirection()).andExpect(MockMvcResultMatchers.redirectedUrl("login"));
	}

	@Test
	public void redirectHome() throws Exception {
		session.setAttribute(SessionKey.USER_ID.toString(), "5");
		mvc.perform(MockMvcRequestBuilders.get("/").session(session)).andExpect(status().is3xxRedirection()).andExpect(MockMvcResultMatchers.redirectedUrl("home"));
	}
}
