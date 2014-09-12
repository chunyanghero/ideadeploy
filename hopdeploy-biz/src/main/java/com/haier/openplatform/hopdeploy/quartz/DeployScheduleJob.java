package com.haier.openplatform.hopdeploy.quartz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;
import com.haier.openplatform.hopdeploy.deploy.service.DeployScheduleService;
import com.haier.openplatform.util.SpringApplicationContextHolder;

public class DeployScheduleJob implements Job {
	private static DeployScheduleService deployScheduleService = SpringApplicationContextHolder.getBean("deployScheduleService");
	private static final Logger LOG = Logger.getLogger(DeployScheduleJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info("============== DeployScheduleJob begin =============");
		Date date = new Date();
		List<DeploySchedule> deploySchedules = deployScheduleService.getToBeDeployedApps(date);
		LOG.info("======== quartz * quartz * quartz * quartz * quartz * quartz =========");
		LOG.info(deploySchedules.toString());
		LOG.info("======== quartz * quartz * quartz * quartz * quartz * quartz =========");
		deployScheduleService.doDeploySchedule(deploySchedules);
		LOG.info("=============== DeployScheduleJob end ==============");
	}
}
