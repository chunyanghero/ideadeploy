package com.haier.openplatform.hopdeploy.deploy.service.impl;

import org.junit.Test;

import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;

public class AppRelateServerServiceImplTest_changeWeblogicPassword extends BaseDeployTestCase {
	@Test
	public void test_changeWeblogicPassword() {
		AppRelateServer appRelateServer = new AppRelateServer();
		appRelateServer.setId(38);
		System.out.println("==============================================");
		appRelateServerService.changeWeblogicPassword(appRelateServer);
		System.out.println("==============================================");
	}
}
