package com.beydilli.todolist.util;

public class UserCredentialsValidator {

	private final static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	public static boolean validateUserCredentials(String name, String surname, String email, String password) {
		if ((name == null || "".equals(name)) || (surname == null || "".equals(surname))
				|| (email == null || "".equals(email)) || (password == null || "".equals(password))) {
			return false;
		}
		if (!email.matches(EMAIL_REGEX)) {
			return false;
		}
		return true;
	}

	public static boolean validateUserCredentials(String email, String password) {
		if ((email == null || "".equals(email)) || (password == null || "".equals(password))) {
			return false;
		}
		return true;
	}
}
