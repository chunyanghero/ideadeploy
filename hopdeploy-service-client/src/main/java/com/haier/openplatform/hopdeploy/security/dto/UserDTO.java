package com.haier.openplatform.hopdeploy.security.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = -6696200705881135054L;
	private long id;
	private String name;
	private String nickName;
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
