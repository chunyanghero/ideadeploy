package com.haier.openplatform.hopdeploy.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 账号过期定期执行扫描
 * 
 * @author WangJian
 * 
 */
public class AccountExpireJob implements Job {
	private static final Log LOG = LogFactory.getLog(AccountExpireJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info("用户过期状态任务检查更新完成");
	}
}
