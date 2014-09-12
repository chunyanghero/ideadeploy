package com.haier.openplatform.hopdeploy.security.domain;

import com.haier.openplatform.domain.BaseDomain;

/**
 * 登录用户
 * 
 * @author spring
 * 
 */
public class User extends BaseDomain<Integer> {
	private static final long serialVersionUID = 5450179012429507881L;
	private Integer id;
	private String username;
	private String password;
	private Integer isAdmin;
	private String email;
	private String phone;
	private String realName;
	private Integer enabled;

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", isAdmin=" + isAdmin
				+ ", email=" + email + ", phone=" + phone + ", realName=" + realName + ", enabled=" + enabled + "]";
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
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

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}
