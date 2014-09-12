package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import java.util.ArrayList;
import java.util.List;

import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;

public class SearchAppRelateServerForComboboxAction extends BaseDeployAction {
	private static final long serialVersionUID = 4074845195776016610L;
	private List<AppRelateServer> combobox = new ArrayList<AppRelateServer>();

	@Override
	public String execute() throws Exception {
		combobox = appRelateServerService.searchAppRelateServerForCombobox();
		return SUCCESS;
	}

	public List<AppRelateServer> getCombobox() {
		return combobox;
	}

	public void setCombobox(List<AppRelateServer> combobox) {
		this.combobox = combobox;
	}
}
