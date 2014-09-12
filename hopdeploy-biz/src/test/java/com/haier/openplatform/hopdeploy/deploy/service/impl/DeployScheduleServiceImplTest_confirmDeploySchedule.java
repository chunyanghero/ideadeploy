package com.haier.openplatform.hopdeploy.deploy.service.impl;

import org.junit.Test;

public class DeployScheduleServiceImplTest_confirmDeploySchedule extends BaseDeployTestCase {
	@Test
	public void test_confirmDeploySchedule() {
		Integer id = 42;
		Integer flag = 1;
		deployScheduleService.confirmDeploySchedule(id, flag);
	}
}
