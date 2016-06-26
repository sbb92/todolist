package com.beydilli.todolist.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import com.beydilli.todolist.TodolistApplicationTests;
import com.beydilli.todolist.domain.SessionKey;

public class UtilTest extends TodolistApplicationTests {
	@Autowired
	MockHttpServletRequest request;

	@Test
	public void userCredentialsValidatorTest() {
		assertTrue(UserCredentialsValidator.validateUserCredentials("ahmet@gmail.com", "fdsf"));
		assertTrue(UserCredentialsValidator.validateUserCredentials("ahmet", "yılmaz", "ahmet@ahmet.com", "2345"));

		assertFalse(UserCredentialsValidator.validateUserCredentials("ahmetgmail.com", "fdsf"));
		assertFalse(UserCredentialsValidator.validateUserCredentials("ahmet", "yılmaz", "ahmetahmet.com", "2345"));

		assertFalse(UserCredentialsValidator.validateUserCredentials("ahmetgmail.com", null));
		assertFalse(UserCredentialsValidator.validateUserCredentials("ahmet", "yılmaz", null, "2345"));

	}

	@Test
	public void webUtilTest() {
		WebUtil.addToSession(request, 5L, SessionKey.USER_ID);
		assertEquals(((Long)WebUtil.getFromSession(request, SessionKey.USER_ID)).longValue(),5L);
		assertTrue(WebUtil.isUserLoggedIn(request));
		
		WebUtil.removeFromSession(request, SessionKey.USER_ID);
		assertFalse(WebUtil.isUserLoggedIn(request));
	}
}
