package com.hzmsc.scada.entity.shiro;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String password;
	private int enabled;
	
	private String salt;//盐值字段  
	//private Boolean locked = Boolean.FALSE;
	
	public User(){
		
	}
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled + ", salt=" + salt + "]";
	}
	
	

}
