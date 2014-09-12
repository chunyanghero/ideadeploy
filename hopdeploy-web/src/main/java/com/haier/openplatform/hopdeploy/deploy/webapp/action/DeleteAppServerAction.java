package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppServer;
import com.haier.openplatform.util.ExecuteResult;

public class DeleteAppServerAction extends BaseDeployAction {
	private static final long serialVersionUID = -5073263736560700551L;
	private int id;

	@Override
	public String execute() throws Exception {
		ExecuteResult<AppServer> result = appServerService.deleteAppServerById(id);
		if (!result.isSuccess()) {
			addErrorsFromResult(result);
		}
		return INPUT;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
