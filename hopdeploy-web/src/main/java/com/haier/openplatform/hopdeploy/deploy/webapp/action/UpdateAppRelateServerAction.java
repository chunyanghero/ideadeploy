package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;
import com.haier.openplatform.util.ExecuteResult;

public class UpdateAppRelateServerAction extends BaseDeployAction {
	private static final long serialVersionUID = 1325269051319728373L;
	private AppRelateServer appRelateServer = new AppRelateServer();

	@Override
	public String execute() throws Exception {
		ExecuteResult<AppRelateServer> result = appRelateServerService.updateAppRelateServer(appRelateServer);
		if (!result.isSuccess()) {
			addErrorsFromResult(result);
		}
		return INPUT;
	}

	public AppRelateServer getAppRelateServer() {
		return appRelateServer;
	}

	public void setAppRelateServer(AppRelateServer appRelateServer) {
		this.appRelateServer = appRelateServer;
	}
}
