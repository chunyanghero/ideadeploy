package com.haier.openplatform.hopdeploy.security.service.impl;

import com.haier.openplatform.hopdeploy.security.dao.UserDAO;
import com.haier.openplatform.hopdeploy.security.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO;

	@Override
	public String getPassword(String username) {
		return userDAO.getPasswordByUsername(username);
	}

	@Override
	public boolean ifUserExsits(String username) {
		if (userDAO.getUserAmount(username) > 0) {
			return true;
		}
		return false;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
