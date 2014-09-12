package com.haier.openplatform.hopdeploy.deploy.service.impl;

import org.junit.Test;

public class DeployServiceImpl_searchManualDeployResultByTaskId extends BaseDeployTestCase {
	@Test
	public void test_searchManualDeployResultByTaskId() {
		System.out.println("=========DeployServiceImpl_searchManualDeployResultByTaskId==========");
		System.out.println(deployService.searchManualDeployResultByTaskId(16));
		System.out.println("=========DeployServiceImpl_searchManualDeployResultByTaskId==========");
	}
}
