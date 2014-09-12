package com.haier.openplatform.hopdeploy.quartz;

import org.junit.Test;
import org.quartz.JobExecutionException;

public class DeployScheduleFromAlmJobTest extends BaseQuartzTest {
	@Test
	public void testExecute() {
		DeployScheduleFromAlmJob dsfaj = new DeployScheduleFromAlmJob();
		try {
			dsfaj.execute(null);
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}
	}
}
