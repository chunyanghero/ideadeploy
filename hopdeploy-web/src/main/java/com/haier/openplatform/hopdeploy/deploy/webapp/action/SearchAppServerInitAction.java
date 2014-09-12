package com.haier.openplatform.hopdeploy.deploy.webapp.action;

public class SearchAppServerInitAction extends BaseDeployAction {
	private static final long serialVersionUID = 945948859431980659L;
	private Integer weblogicId;

	@Override
	public String execute() {
		return SUCCESS;
	}

	public Integer getWeblogicId() {
		return weblogicId;
	}

	public void setWeblogicId(Integer weblogicId) {
		this.weblogicId = weblogicId;
	}

}
