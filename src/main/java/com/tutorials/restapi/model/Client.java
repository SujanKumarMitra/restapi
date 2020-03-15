package com.tutorials.restapi.model;

public class Client {
	private int id;
	private String username;
	private String authKey;
	@Override
	public String toString() {
		return "ClientAuth [id=" + id + ", username=" + username + ", authKey=" + authKey + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	}
