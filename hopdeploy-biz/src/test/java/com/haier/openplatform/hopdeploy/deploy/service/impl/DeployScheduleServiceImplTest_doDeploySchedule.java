package com.haier.openplatform.hopdeploy.deploy.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;

public class DeployScheduleServiceImplTest_doDeploySchedule extends BaseDeployTestCase {
	@Test
	public void test_doDeploySchedule() {
		Date date = new Date();
		List<DeploySchedule> deploySchedules = deployScheduleService.getToBeDeployedApps(date);
		deployScheduleService.doDeploySchedule(deploySchedules);
	}
}
