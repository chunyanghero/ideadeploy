package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.App;
import com.haier.openplatform.util.ExecuteResult;

public class UpdateNotWeblogicDeployTutorialAction extends BaseDeployAction {
	private static final long serialVersionUID = -760914400866662168L;
	private int applicationId;
	private String deployTutorial;

	@Override
	public String execute() throws Exception {
		App app = new App();
		app.setApplicationId(applicationId);
		app.setDeployTutorial(deployTutorial);
		ExecuteResult<App> result = appService.updateDeployTutorial(app);
		if (!result.isSuccess()) {
			addErrorsFromResult(result);
		}
		return INPUT;
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
