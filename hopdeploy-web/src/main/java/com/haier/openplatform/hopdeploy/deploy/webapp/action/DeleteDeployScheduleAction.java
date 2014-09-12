package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;
import com.haier.openplatform.util.ExecuteResult;

public class DeleteDeployScheduleAction extends BaseDeployAction {
	private static final long serialVersionUID = 1455653516012789894L;
	private int id;

	@Override
	public String execute() throws Exception {
		ExecuteResult<DeploySchedule> result = deployScheduleService.deleteDeployScheduleById(id);
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
