package com.bean;

public class UserKeyBean {
	private int id;
	private String username;
	private String password;
	private int rid;
	private char[] authority;
	
	
	public char[] getAuthority() {
		return authority;
	}
	public void setAuthority(char[] authority) {
		this.authority = authority;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	
}
