package com.haier.openplatform.hopdeploy.deploy.service.impl;

import org.junit.Test;

import com.haier.openplatform.hopdeploy.deploy.domain.Task;
import com.haier.openplatform.util.Pager;

public class TaskServiceImplTest_SearchManualDeployResult extends BaseDeployTestCase {
	@Test
	public void test_SearchManualDeployResult() {
		Pager<Task> pager = new Pager<Task>();
		Task task = new Task();
		String startTime = "";
		String endTime = "";

		Pager<Task> result = taskService.searchManualDeployResult(pager, task, startTime, endTime);
		System.out.println("=========TaskServiceImplTest_SearchManualDeployResult=========");
		System.out.println(result.getRecords().toString());
		System.out.println("=========TaskServiceImplTest_SearchManualDeployResult=========");
	}
}
