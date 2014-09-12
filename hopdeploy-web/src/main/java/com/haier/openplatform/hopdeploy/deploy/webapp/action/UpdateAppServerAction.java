package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppServer;
import com.haier.openplatform.util.ExecuteResult;

public class UpdateAppServerAction extends BaseDeployAction {
	private static final long serialVersionUID = -4226824991126885814L;
	private AppServer appServer = new AppServer();

	@Override
	public String execute() throws Exception {
		appServer.setMiddlewarePath(appServer.getMiddlewarePath().trim());
		ExecuteResult<AppServer> result = appServerService.updateAppServer(appServer);
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
}
