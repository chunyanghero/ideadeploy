package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;
import com.haier.openplatform.util.ExecuteResult;

public class ChangeWeblogicPasswordAction extends BaseDeployAction {
	private static final long serialVersionUID = 3374380660963549927L;
	private AppRelateServer appRelateServer = new AppRelateServer();

	@Override
	public String execute() throws Exception {
		ExecuteResult<AppRelateServer> result = appRelateServerService.changeWeblogicPassword(appRelateServer);
		if (!result.isSuccess())
			addErrorsFromResult(result);
		return INPUT;
	}

	public AppRelateServer getAppRelateServer() {
		return appRelateServer;
	}

	public void setAppRelateServer(AppRelateServer appRelateServer) {
		this.appRelateServer = appRelateServer;
	}

}
