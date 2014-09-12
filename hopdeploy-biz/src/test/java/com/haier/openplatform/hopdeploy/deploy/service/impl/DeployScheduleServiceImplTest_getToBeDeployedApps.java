package com.haier.openplatform.hopdeploy.deploy.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;

public class DeployScheduleServiceImplTest_getToBeDeployedApps extends BaseDeployTestCase {
	@Test
	public void test_getToBeDeployedApps() {
		Date date = new Date();
		List<DeploySchedule> result = deployScheduleService.getToBeDeployedApps(date);
		System.out.println("=============TEST==============");
		System.out.println(result.toString());
		System.out.println("=============TEST==============");
	}
}
