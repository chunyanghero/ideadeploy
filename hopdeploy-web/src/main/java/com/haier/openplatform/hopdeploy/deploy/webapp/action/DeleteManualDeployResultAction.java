package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.Task;
import com.haier.openplatform.util.ExecuteResult;

public class DeleteManualDeployResultAction extends BaseDeployAction {
	private static final long serialVersionUID = -2598617295536878090L;
	private int id;

	@Override
	public String execute() throws Exception {
		ExecuteResult<Task> result = deployService.deleteManualDeployResult(id);
		if (!result.isSuccess()) {
			addActionErrorsFromResult(result);
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
