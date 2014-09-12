package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppServer;

public class UpdateAppServerInitAction extends BaseDeployAction {
	private static final long serialVersionUID = 7214669327547549064L;
	private int id;
	private int parentId;
	private AppServer appServer = new AppServer();

	@Override
	public String execute() throws Exception {
		appServer = appServerService.getAppServerById(id);
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public AppServer getAppServer() {
		return appServer;
	}

	public void setAppServer(AppServer appServer) {
		this.appServer = appServer;
	}
}
