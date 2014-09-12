package com.haier.openplatform.hopdeploy.deploy.dao;

import org.apache.ibatis.annotations.Param;

import com.haier.openplatform.dao.CommonDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.App;

public interface AppDAO extends CommonDAO<App, Integer> {
	/**
	 * 根据应用编码、应用简称、打包类型判断应用是否已经存在
	 * 
	 * @param app
	 * @return
	 */
	public App searchAppByCodeAbbrPT(@Param("app") App app);

	/**
	 * 删除App时查看有没有其他App对应此AdminServer
	 * 
	 * @param weblogicId
	 * @return
	 */
	public int searchAppCountByWeblogicId(@Param("weblogicId") Integer weblogicId);

	/**
	 * 根据applicationId查询发版指南
	 * 
	 * @param applicationId
	 * @return
	 */
	public String searchDeployTutorialByApplicationId(@Param("applicationId") Integer applicationId);

	/**
	 * 根据applicationId更新发版指南
	 * 
	 * @param applicationId
	 * @return
	 */
	public int updateDeployTutorialByApplicationId(@Param("app") App app);

}
