package com.beydilli.todolist.domain;

public enum SessionKey {
	USER_ID("userId"),USER("user");

	private final String name;

	private SessionKey(String s) {
		name = s;
	}

	public String toString() {
		return this.name;
	}
}
