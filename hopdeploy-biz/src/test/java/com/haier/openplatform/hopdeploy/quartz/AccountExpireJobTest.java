package com.haier.openplatform.hopdeploy.quartz;

import org.junit.Test;
import org.quartz.JobExecutionException;

public class AccountExpireJobTest extends BaseQuartzTest {

	@Test
	public void testExecute() {
		AccountExpireJob job = new AccountExpireJob();
		try {
			job.execute(null);
		} catch (JobExecutionException e) {
		}
	}

}
