package com.haier.openplatform.hopdeploy.deploy.webapp.action;

public class SearchManualDeployResultByTaskIdAction extends BaseDeployAction {
	private static final long serialVersionUID = -1453294623991597807L;
	private int id;
	private String deployMessage;

	@Override
	public String execute() throws Exception {
		deployMessage = deployService.searchManualDeployResultByTaskId(id);
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeployMessage() {
		return deployMessage;
	}

	public void setDeployMessage(String deployMessage) {
		this.deployMessage = deployMessage;
	}

}
