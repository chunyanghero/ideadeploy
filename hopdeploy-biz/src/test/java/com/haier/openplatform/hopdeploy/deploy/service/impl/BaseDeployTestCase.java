package com.haier.openplatform.hopdeploy.deploy.service.impl;

import javax.annotation.Resource;

import com.haier.openplatform.hopdeploy.BaseTestCase;
import com.haier.openplatform.hopdeploy.deploy.service.AppRelateServerService;
import com.haier.openplatform.hopdeploy.deploy.service.DeployScheduleService;
import com.haier.openplatform.hopdeploy.deploy.service.DeployService;
import com.haier.openplatform.hopdeploy.deploy.service.TaskService;

public class BaseDeployTestCase extends BaseTestCase {
	@Resource
	protected DeployScheduleService deployScheduleService;
	@Resource
	protected TaskService taskService;
	@Resource
	protected DeployService deployService;
	@Resource
	protected AppRelateServerService appRelateServerService;
}
