package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;
import com.haier.openplatform.util.ExecuteResult;

public class CreateAppRelateServerAction extends BaseDeployAction {
	private static final long serialVersionUID = -6882299629965433035L;
	private AppRelateServer appRelateServer;

	@Override
	public String execute() throws Exception {

		appRelateServer.setApplicationAbbr(appRelateServer.getApplicationAbbr().trim());
		appRelateServer.setApplicationCode(appRelateServer.getApplicationCode().trim());
		appRelateServer.setCheckUrl(appRelateServer.getCheckUrl().trim());
		appRelateServer.setDeployNodes(appRelateServer.getDeployNodes().trim());
		appRelateServer.setIp(appRelateServer.getIp().trim());
		appRelateServer.setMiddlewarePath(appRelateServer.getMiddlewarePath().trim());

		ExecuteResult<AppRelateServer> result = appRelateServerService.createAppRelateServer(appRelateServer);

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
