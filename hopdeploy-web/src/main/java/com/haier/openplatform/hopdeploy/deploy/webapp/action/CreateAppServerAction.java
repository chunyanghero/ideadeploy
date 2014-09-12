package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppServer;
import com.haier.openplatform.util.ExecuteResult;

public class CreateAppServerAction extends BaseDeployAction {
	private static final long serialVersionUID = 4122108531063303457L;
	private int id;
	private AppServer appServer = new AppServer();

	@Override
	public String execute() throws Exception {
		appServer.setParentId(id);
		appServer.setMiddlewarePath(appServer.getMiddlewarePath().trim());
		ExecuteResult<AppServer> result = appServerService.createAppServer(appServer);
		if (!result.isSuccess()) {
			addErrorsFromResult(result);
		}
		return INPUT;
	}

	public AppServer getAppServer() {
		return appServer;
	}

	public void setAppServer(AppServer appServer) {
		this.appServer = appServer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
