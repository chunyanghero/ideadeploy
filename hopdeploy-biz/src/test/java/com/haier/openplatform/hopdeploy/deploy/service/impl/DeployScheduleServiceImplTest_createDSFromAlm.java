package com.haier.openplatform.hopdeploy.deploy.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;

public class DeployScheduleServiceImplTest_createDSFromAlm extends BaseDeployTestCase {
	@Test
	public void testCreateDSFromALM() {
		List<String> phoneNums = new ArrayList<String>();
		phoneNums.add("18354231940");
		phoneNums.add("15866867422");
		phoneNums.add("18765901113");
		List<DeploySchedule> deploySchedules = new ArrayList<DeploySchedule>();

		DeploySchedule deploySchedule = new DeploySchedule();
		deploySchedule.setPackageName("alm.2014090501.war");
		deploySchedule.setPhoneNums(phoneNums);
		deploySchedule.setPublishId(33);
		deploySchedule.setExecutor("ALM");
		deploySchedule.setStatus(0);
		// deploySchedules.add(deploySchedule);

		deploySchedule = new DeploySchedule();
		deploySchedule.setPackageName("qdzzjs.2014090501.war");
		deploySchedule.setPhoneNums(phoneNums);
		deploySchedule.setPublishId(34);
		deploySchedule.setExecutor("ALM");
		deploySchedule.setStatus(0);
		deploySchedules.add(deploySchedule);

		/*List<DeploySchedule> deploySchedules = deployScheduleService.searchDeployScheduleFromAlm();*/
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
		System.out.println("========================================================");
		System.out.println(deploySchedules.toString());
		System.out.println("========================================================");
		deployScheduleService.doDeploySchedule(deploySchedules);
	}
}
