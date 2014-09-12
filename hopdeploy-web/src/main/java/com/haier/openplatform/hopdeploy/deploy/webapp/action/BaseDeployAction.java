package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.service.AppRelateServerService;
import com.haier.openplatform.hopdeploy.deploy.service.AppServerService;
import com.haier.openplatform.hopdeploy.deploy.service.AppService;
import com.haier.openplatform.hopdeploy.deploy.service.DeployScheduleService;
import com.haier.openplatform.hopdeploy.deploy.service.DeployService;
import com.haier.openplatform.hopdeploy.webapp.action.BaseShowcaseAction;

public class BaseDeployAction extends BaseShowcaseAction {
	private static final long serialVersionUID = 7878057157725255980L;
	protected AppRelateServerService appRelateServerService;
	protected AppServerService appServerService;
	protected AppService appService;
	protected DeployService deployService;
	protected DeployScheduleService deployScheduleService;

	public DeployScheduleService getDeployScheduleService() {
		return deployScheduleService;
	}

	public void setDeployScheduleService(DeployScheduleService deployScheduleService) {
		this.deployScheduleService = deployScheduleService;
	}

	public DeployService getDeployService() {
		return deployService;
	}

	public void setDeployService(DeployService deployService) {
		this.deployService = deployService;
	}

	public AppRelateServerService getAppRelateServerService() {
		return appRelateServerService;
	}

	public void setAppRelateServerService(AppRelateServerService appRelateServerService) {
		this.appRelateServerService = appRelateServerService;
	}

	public AppServerService getAppServerService() {
		return appServerService;
	}

	public void setAppServerService(AppServerService appServerService) {
		this.appServerService = appServerService;
	}

	public AppService getAppService() {
		return appService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}
}
