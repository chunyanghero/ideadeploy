package com.haier.openplatform.hopdeploy.deploy.webapp.action;

public class SearchNWUnixInfoInitAction extends BaseDeployAction {
	private static final long serialVersionUID = -7094813526109868519L;
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
