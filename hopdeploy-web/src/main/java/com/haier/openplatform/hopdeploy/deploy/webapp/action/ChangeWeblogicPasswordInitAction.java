package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AdminServer;

public class ChangeWeblogicPasswordInitAction extends BaseDeployAction {
	private static final long serialVersionUID = 4863229918279571713L;
	private int id;
	private String username;
	private String password;

	@Override
	public String execute() throws Exception {
		AdminServer as = appRelateServerService.getUsernamePasswdById(id);
		username = as.getLogin();
		password = as.getPasswd();
		return SUCCESS;
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

}
