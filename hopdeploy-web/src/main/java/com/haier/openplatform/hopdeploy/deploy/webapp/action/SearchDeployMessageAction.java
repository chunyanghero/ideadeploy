package com.haier.openplatform.hopdeploy.deploy.webapp.action;

public class SearchDeployMessageAction extends BaseDeployAction {
	private static final long serialVersionUID = 7786365212837562115L;
	private int id;
	private String deployMessage;

	@Override
	public String execute() throws Exception {
		deployMessage = deployScheduleService.searchDeployMessage(id);
		return SUCCESS;
	}

	public String getDeployMessage() {
		return deployMessage;
	}

	public void setDeployMessage(String deployMessage) {
		this.deployMessage = deployMessage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
