package com.haier.openplatform.hopdeploy.deploy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haier.openplatform.dao.CommonDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.AlmTask;
import com.haier.openplatform.hopdeploy.deploy.domain.Task;
import com.haier.openplatform.util.Pager;

public interface TaskDAO extends CommonDAO<Task, Integer> {
	/**
	 * 扫描发版的结果
	 * 
	 * @return
	 */
	public List<Task> searchDeployTask();

	/**
	 * 将alm里获得的信息插入Task表中，如publish_id task_id等
	 * 
	 * @param almTask
	 * @return
	 */
	public int insertAlmTask(List<AlmTask> almTaskList);

	public int insertManualTask(Task task);

	public List<Task> searchManualDeployResult(@Param("pager") Pager<Task> pager, @Param("task") Task task,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

	public Long searchManualDeployResultCount(@Param("pager") Pager<Task> pager, @Param("task") Task task,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

	public String searchManualDeployResultByTaskId(@Param("id") Integer id);

}
