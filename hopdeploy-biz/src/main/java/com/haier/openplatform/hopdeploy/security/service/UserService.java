package com.haier.openplatform.hopdeploy.security.service;

public interface UserService {
	/**
	 * 根据用户名获得密码
	 * 
	 * @param username
	 * @return
	 */
	public String getPassword(String username);

	/**
	 * 判断用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean ifUserExsits(String username);
}
