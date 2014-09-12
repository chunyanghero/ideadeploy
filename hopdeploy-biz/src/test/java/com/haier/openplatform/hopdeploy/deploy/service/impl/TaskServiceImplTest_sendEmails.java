package com.haier.openplatform.hopdeploy.deploy.service.impl;

import org.junit.Test;

import com.haier.openplatform.hopdeploy.deploy.domain.Task;

public class TaskServiceImplTest_sendEmails extends BaseDeployTestCase {
	@Test
	public void test_sendEmails() {

		Task task = taskService.deployTask(deployService.composePreTaskByPackageName("alm.0805.war"));
		taskService.sendEmails(task);

	}
}
