package com.tutorials.restapi.model;

public class Error {
	private int id;
	private String message;
	
	public Error() {}
	public Error(int id,String message)
	{
		this.id=id;
		this.message=message;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Error [id=" + id + ", message=" + message + "]";
	}

}
