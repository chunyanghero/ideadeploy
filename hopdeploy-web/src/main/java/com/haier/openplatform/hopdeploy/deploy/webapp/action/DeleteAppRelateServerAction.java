package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;
import com.haier.openplatform.util.ExecuteResult;

public class DeleteAppRelateServerAction extends BaseDeployAction {
	private static final long serialVersionUID = 6555178268588750649L;
	private int applicationId;

	@Override
	public String execute() throws Exception {
		ExecuteResult<AppRelateServer> result = appRelateServerService.deleteAppRelateServerById(applicationId);
		if (!result.isSuccess()) {
			addErrorsFromResult(result);
		}
		return INPUT;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
}
