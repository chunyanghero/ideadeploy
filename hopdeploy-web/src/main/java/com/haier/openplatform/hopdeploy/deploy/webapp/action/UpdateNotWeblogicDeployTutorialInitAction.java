package com.haier.openplatform.hopdeploy.deploy.webapp.action;

public class UpdateNotWeblogicDeployTutorialInitAction extends BaseDeployAction {
	private static final long serialVersionUID = 5898884344397063229L;
	private int applicationId;
	private String deployTutorial;
	private Integer cpage;

	@Override
	public String execute() throws Exception {
		deployTutorial = appService.searchDeployTutorial(applicationId);
		return SUCCESS;
	}

	public Integer getCpage() {
		return cpage;
	}

	public void setCpage(Integer cpage) {
		this.cpage = cpage;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getDeployTutorial() {
		return deployTutorial;
	}

	public void setDeployTutorial(String deployTutorial) {
		this.deployTutorial = deployTutorial;
	}
}
