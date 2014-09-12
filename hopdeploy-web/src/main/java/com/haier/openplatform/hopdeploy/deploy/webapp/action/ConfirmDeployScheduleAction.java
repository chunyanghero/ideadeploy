package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;
import com.haier.openplatform.util.ExecuteResult;

public class ConfirmDeployScheduleAction extends BaseDeployAction {
	private static final long serialVersionUID = -3573481167230976011L;
	private Integer id;
	private Integer flag;

	@Override
	public String execute() throws Exception {
		ExecuteResult<DeploySchedule> result = deployScheduleService.confirmDeploySchedule(id, flag);
		if (!result.isSuccess()) {
			addErrorsFromResult(result);
		}
		return INPUT;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
