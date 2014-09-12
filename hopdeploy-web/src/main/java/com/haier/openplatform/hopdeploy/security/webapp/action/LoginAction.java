package com.haier.openplatform.hopdeploy.security.webapp.action;

import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseSecurityAction {
	private static final long serialVersionUID = -1357826387079806536L;
	private String username;
	private String password;

	@Override
	public String execute() throws Exception {
		if (isValid(username)) {
			setSession();
			return SUCCESS;
		}
		return LOGIN;
	}

	private boolean isValid(String username) {

		if (password.equals(userService.getPassword(username))) {
			return true;
		}
		return false;
	}

	private void setSession() {
		ActionContext.getContext().getSession().put("username", username);
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

}
