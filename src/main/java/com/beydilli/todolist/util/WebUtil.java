package com.beydilli.todolist.util;

import javax.servlet.http.HttpServletRequest;

import com.beydilli.todolist.domain.SessionKey;

public class WebUtil {

	@SuppressWarnings("unchecked")
	public static <T> T getFromSession(HttpServletRequest request, SessionKey key) {
		return (T) request.getSession().getAttribute(key.toString());
	}

	public static void addToSession(HttpServletRequest request, Object object, SessionKey key) {
		request.getSession().setAttribute(key.toString(), object);
	}
	
	public static void removeFromSession(HttpServletRequest request, SessionKey key){
		request.getSession().removeAttribute(key.toString());
	}
	
	public static boolean isUserLoggedIn(HttpServletRequest request){
		return getFromSession(request, SessionKey.USER_ID) != null;
	}
}
