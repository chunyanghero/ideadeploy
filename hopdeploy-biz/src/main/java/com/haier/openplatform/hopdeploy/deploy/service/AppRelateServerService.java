package com.haier.openplatform.hopdeploy.deploy.service;

import java.util.List;

import com.haier.openplatform.hopdeploy.deploy.domain.AdminServer;
import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;
import com.haier.openplatform.hopdeploy.deploy.domain.UnixInfo;
import com.haier.openplatform.util.ExecuteResult;
import com.haier.openplatform.util.Pager;

public interface AppRelateServerService {
	/**
	 * 根据查询条件查询服务器信息
	 * 
	 * @param appRelateServer
	 * @return
	 */
	public Pager<AppRelateServer> searchAppRelateServer(Pager<AppRelateServer> pager, AppRelateServer appRelateServer);

	/**
	 * 模糊查询时combobox自动补全
	 * 
	 * @return
	 */
	public List<AppRelateServer> searchAppRelateServerForCombobox();

	/**
	 * 创建应用及对应服务器信息
	 * 
	 * @param appRelateServer
	 * @return
	 */
	public ExecuteResult<AppRelateServer> createAppRelateServer(AppRelateServer appRelateServer);

	/**
	 * 更新应用及对应服务器信息
	 * 
	 * @param appRelateServer
	 * @return
	 */
	public ExecuteResult<AppRelateServer> updateAppRelateServer(AppRelateServer appRelateServer);

	/**
	 * 根据应用id取AppRelateServer对象
	 * 
	 * @param applicationId
	 * @return
	 */
	public AppRelateServer getAppRelateServerByApplicationId(Integer applicationId);

	/**
	 * 根据应用id删除AdminServer、App
	 * 
	 * @param applicationId
	 * @return
	 */
	public ExecuteResult<AppRelateServer> deleteAppRelateServerById(Integer applicationId);

	/**
	 * 根据id查询应用所在所有服务器的用户名、密码
	 * 
	 * @param id
	 * @return
	 */
	public Pager<UnixInfo> searchUnixInfo(Integer id);

	public Pager<UnixInfo> searchNWUnixInfo(Integer id);

	/**
	 * 根据ip更新服务器用户名、密码信息
	 * 
	 * @param unixInfo
	 * @return
	 */
	public int updateUnixInfo(UnixInfo unixInfo);

	public int updateNWUnixInfo(UnixInfo unixInfo);

	/**
	 * 更改weblogic密码（控制台密码和几个boot.properties文件）
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @return
	 */
	public ExecuteResult<AppRelateServer> changeWeblogicPassword(AppRelateServer appRelateServer);

	/**
	 * 根据id查用户名、密码（修改密码时用）
	 * 
	 * @param id
	 * @return
	 */
	public AdminServer getUsernamePasswdById(int id);

}
