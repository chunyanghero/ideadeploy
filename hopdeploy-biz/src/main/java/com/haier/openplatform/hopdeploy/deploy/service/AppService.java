package com.haier.openplatform.hopdeploy.deploy.service;

import com.haier.openplatform.hopdeploy.deploy.domain.App;
import com.haier.openplatform.util.ExecuteResult;

public interface AppService {
	/**
	 * 查询发版指南
	 * 
	 * @return
	 */
	public String searchDeployTutorial(Integer applicationId);

	/**
	 * 更新发版指南
	 * 
	 * @param app
	 * @return
	 */
	public ExecuteResult<App> updateDeployTutorial(App app);
}
