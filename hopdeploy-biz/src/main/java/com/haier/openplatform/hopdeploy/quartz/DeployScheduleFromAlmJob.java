package com.haier.openplatform.hopdeploy.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;
import com.haier.openplatform.hopdeploy.deploy.service.DeployScheduleService;
import com.haier.openplatform.util.SpringApplicationContextHolder;

public class DeployScheduleFromAlmJob implements Job {
	private static DeployScheduleService deployScheduleService = SpringApplicationContextHolder.getBean("deployScheduleService");
	private static final Logger LOG = Logger.getLogger(DeployScheduleFromAlmJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info("============== DeployScheduleFromAlmJob begin =============");
		List<DeploySchedule> deploySchedules = deployScheduleService.searchDeployScheduleFromAlm();
		Date createDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String cDate = sdf.format(createDate);
		for (DeploySchedule ds : deploySchedules) {
			ds.setExecuteTime(cDate);
			ds.setStatus(0);
			ds.setExecutor("ALM");
			deployScheduleService.createDeploySchedule(ds);
		}
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, 1);
		date = cal.getTime();
		deploySchedules = deployScheduleService.getToBeDeployedApps(date);
		LOG.info("======== ALM * ALM * getToBeDeployedApps * ALM * ALM =========");
		LOG.info(deploySchedules.toString());
		LOG.info("======== ALM * ALM * getToBeDeployedApps * ALM * ALM =========");
		deployScheduleService.doDeploySchedule(deploySchedules);
		LOG.info("=============== DeployScheduleFromAlmJob end ==============");
	}
}
