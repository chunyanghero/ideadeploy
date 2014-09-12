package com.haier.openplatform.hopdeploy.deploy.webapp.action;

public class CreateAppServerInitAction extends BaseDeployAction {
	private static final long serialVersionUID = 3304725134868922672L;
	private int parentId;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
