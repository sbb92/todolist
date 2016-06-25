package com.beydilli.todolist.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
	SUCCES("succes"), FAILED("failed");

	private final String name;

	private Status(String s) {
		name = s;
	}

	public String toString() {
		return this.name;
	}
}
