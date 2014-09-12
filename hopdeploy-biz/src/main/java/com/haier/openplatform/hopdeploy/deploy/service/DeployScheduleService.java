package com.haier.openplatform.hopdeploy.deploy.service;

import java.util.Date;
import java.util.List;

import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;
import com.haier.openplatform.util.ExecuteResult;
import com.haier.openplatform.util.Pager;

public interface DeployScheduleService {
	public List<DeploySchedule> searchExecutorForCombobox();

	public ExecuteResult<DeploySchedule> createDeploySchedule(DeploySchedule deploySchedule);

	public ExecuteResult<DeploySchedule> deleteDeployScheduleById(Integer id);

	public ExecuteResult<DeploySchedule> confirmDeploySchedule(Integer id, Integer flag);

	public Pager<DeploySchedule> searchDeploySchedule(Pager<DeploySchedule> pager, DeploySchedule deploySchedule,
			String startTime, String endTime);

	public List<DeploySchedule> searchDeployScheduleFromAlm();

	public List<DeploySchedule> getToBeDeployedApps(Date date);

	public void doDeploySchedule(List<DeploySchedule> deploySchedules);

	public String searchDeployMessage(Integer id);

}
