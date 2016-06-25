package com.beydilli.todolist.domain;

public class RestfulResult {
	private Status status;
	private String message;

	public RestfulResult(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	public RestfulResult() {

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
