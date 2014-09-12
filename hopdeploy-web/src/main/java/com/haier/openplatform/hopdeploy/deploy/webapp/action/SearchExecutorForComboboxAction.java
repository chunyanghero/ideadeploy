package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import java.util.ArrayList;
import java.util.List;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;

public class SearchExecutorForComboboxAction extends BaseDeployAction {
	private static final long serialVersionUID = -8831493728089339081L;
	private List<DeploySchedule> combobox = new ArrayList<DeploySchedule>();

	@Override
	public String execute() throws Exception {
		combobox = deployScheduleService.searchExecutorForCombobox();
		return SUCCESS;
	}

	public List<DeploySchedule> getCombobox() {
		return combobox;
	}

	public void setCombobox(List<DeploySchedule> combobox) {
		this.combobox = combobox;
	}

}
