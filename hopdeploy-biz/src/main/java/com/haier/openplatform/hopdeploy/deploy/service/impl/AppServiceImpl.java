package com.haier.openplatform.hopdeploy.deploy.service.impl;

import com.haier.openplatform.hopdeploy.deploy.dao.AppDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.App;
import com.haier.openplatform.hopdeploy.deploy.service.AppService;
import com.haier.openplatform.util.ExecuteResult;

public class AppServiceImpl implements AppService {
	private AppDAO appDAO;

	@Override
	public String searchDeployTutorial(Integer applicationId) {
		return appDAO.searchDeployTutorialByApplicationId(applicationId);
	}

	@Override
	public ExecuteResult<App> updateDeployTutorial(App app) {
		ExecuteResult<App> executeResult = new ExecuteResult<App>();
		appDAO.updateDeployTutorialByApplicationId(app);
		return executeResult;
	}

	public AppDAO getAppDAO() {
		return appDAO;
	}

	public void setAppDAO(AppDAO appDAO) {
		this.appDAO = appDAO;
	}

}
