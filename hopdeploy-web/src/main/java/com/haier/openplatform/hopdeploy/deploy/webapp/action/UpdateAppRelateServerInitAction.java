package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;

public class UpdateAppRelateServerInitAction extends BaseDeployAction {
	private static final long serialVersionUID = 3467935452966035247L;
	private int applicationId;
	private AppRelateServer appRelateServer = new AppRelateServer();

	@Override
	public String execute() throws Exception {
		appRelateServer = appRelateServerService.getAppRelateServerByApplicationId(applicationId);
		return SUCCESS;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public AppRelateServer getAppRelateServer() {
		return appRelateServer;
	}

	public void setAppRelateServer(AppRelateServer appRelateServer) {
		this.appRelateServer = appRelateServer;
	}
}
