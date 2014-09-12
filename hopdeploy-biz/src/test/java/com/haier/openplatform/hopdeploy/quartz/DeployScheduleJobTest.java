package com.haier.openplatform.hopdeploy.quartz;

import org.junit.Test;
import org.quartz.JobExecutionException;

public class DeployScheduleJobTest extends BaseQuartzTest {
	@Test
	public void testExecute() {
		DeployScheduleJob dsj = new DeployScheduleJob();
		try {
			dsj.execute(null);
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}
	}
}