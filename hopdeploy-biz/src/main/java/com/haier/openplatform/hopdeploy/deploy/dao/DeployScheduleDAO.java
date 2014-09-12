package com.haier.openplatform.hopdeploy.deploy.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haier.openplatform.dao.CommonDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.DeploySchedule;
import com.haier.openplatform.util.Pager;

public interface DeployScheduleDAO extends CommonDAO<DeploySchedule, Integer> {
	// 获得执行人下拉框选项
	public List<DeploySchedule> searchExecutorForCombobox();

	// 查询存入数据库中所有应用简称，用来检查要创建的定时发版的包名是否存在
	public List<String> searchAllAppAbbrs();

	// 创建定时发版任务
	public int createDeploySchedule(DeploySchedule deploySchedule);

	public List<DeploySchedule> searchDeploySchedule(@Param("pager") Pager<DeploySchedule> pager,
			@Param("deploySchedule") DeploySchedule deploySchedule, @Param("startTime") String startTime,
			@Param("endTime") String endTime);

	public Long searchDeployScheduleCount(@Param("pager") Pager<DeploySchedule> pager,
			@Param("deploySchedule") DeploySchedule deploySchedule, @Param("startTime") String startTime,
			@Param("endTime") String endTime);

	// 确认任务已解决
	public int confirmDeploySchedule(@Param("id") Integer id, @Param("flag") Integer flag);

	public List<DeploySchedule> getToBeDeployedApps(@Param("date") Date date);

	// 将部署信息持久化到数据库里
	public int persistenceDM(@Param("id") Integer id, @Param("s") String s);

	public String searchDeployMessage(@Param("id") Integer id);

	public List<DeploySchedule> searchDeployScheduleFromAlm();

	public List<Integer> getDeployedPublishIds();
}
