package com.haier.openplatform.hopdeploy.deploy.service.impl;

import java.util.List;

import org.junit.Test;

import com.haier.openplatform.hopdeploy.deploy.domain.PreTask;

public class DeployServiceImpl_composePreTaskByPackageName extends BaseDeployTestCase {
	@Test
	public void test_composePreTaskByPackageName() {
		List<PreTask> ptl = deployService.composePreTaskByPackageName("GOMSRPT.2014090501.war");
		System.out.println("=================================================");
		System.out.println(ptl.toString());
		System.out.println("=================================================");
	}
}
