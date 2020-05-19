package com.example.nhom11.model;

public class Account {
	private long id;
	private String username, password;
	private Role role;
	
	public Account() {
		super();
	}

	public Account(long id, String username, String password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return id+" "+username+" "+password+" "+role.toString();
	}
	
	public boolean isLogin() {		
		if(username==null || password==null || role==null) return false;
		if(id<=0 || username.trim().length()==0 
				|| password.trim().length()==0 || role.toString().trim().length()==0)
			return false;
		
		return true;
	}

}