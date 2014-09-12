package com.haier.openplatform.hopdeploy.deploy.webapp.action;

public class SearchUnixInfoInitAction extends BaseDeployAction {
	private static final long serialVersionUID = 4811413299077434184L;
	private int weblogicId;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public int getWeblogicId() {
		return weblogicId;
	}

	public void setWeblogicId(int weblogicId) {
		this.weblogicId = weblogicId;
	}
}
