package com.haier.openplatform.hopdeploy.deploy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haier.openplatform.dao.CommonDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.AlmTask;
import com.haier.openplatform.hopdeploy.deploy.domain.PreTask;
import com.haier.openplatform.hopdeploy.deploy.domain.WeblogicShell;

public interface PreTaskDAO extends CommonDAO<PreTask, Long> {
	/**
	 * 根据打包名查询部署此包的必须信息
	 * 
	 * @param packageName
	 * @return
	 */
	public PreTask scanPreTask(String packageName);

	/**
	 * 扫描hopdeploy系统中发版必须的信息
	 * 
	 * @return
	 */
	public PreTask scanPreTask(AlmTask almTask);

	/**
	 * Tomcat可能多个ip
	 * 
	 * @return
	 */
	public List<String> getAllIps();

	/**
	 * 根据包名和打包类型取发版所须信息
	 * 
	 * @param appName
	 * @param packageType
	 * @return
	 */
	public List<PreTask> getPreTaskByPackage(@Param("appName") String appName, @Param("packageType") Integer packageType);

	/**
	 * 查询 weblogic 脚本所在服务器信息
	 * 
	 * @return
	 */
	public WeblogicShell searchWeblogicShell();

	public int changeWeblogicShell(@Param("weblogicShell") WeblogicShell weblogicShell);
}
