package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;
import com.haier.openplatform.util.ExecuteResult;

public class CreateDeployScheduleAction extends BaseDeployAction {
	private static final long serialVersionUID = 8412181709962056902L;
	private DeploySchedule deploySchedule;

	@Override
	public String execute() throws Exception {
		deploySchedule.setStatus(0);
		deploySchedule.setPackageName(deploySchedule.getPackageName().trim());
		ExecuteResult<DeploySchedule> result = deployScheduleService.createDeploySchedule(deploySchedule);
		if (!result.isSuccess()) {
			addActionErrorsFromResult(result);
		}
		return INPUT;
	}

	public DeploySchedule getDeploySchedule() {
		return deploySchedule;
	}

	public void setDeploySchedule(DeploySchedule deploySchedule) {
		this.deploySchedule = deploySchedule;
	}

}
