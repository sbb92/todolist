package com.beydilli.todolist.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Status {
	SUCCESS("SUCCESS"), FAILED("FAILED");

	private final String name;

	private Status(String s) {
		name = s;
	}

	public String toString() {
		return this.name;
	}
}
