package com.haier.openplatform.hopdeploy.deploy.service;

import com.haier.openplatform.hopdeploy.deploy.domain.AppServer;
import com.haier.openplatform.util.ExecuteResult;
import com.haier.openplatform.util.Pager;

public interface AppServerService {
	/**
	 * 查询应用服务器信息
	 * 
	 * @param pager
	 * @param appServer
	 * @return
	 */
	public Pager<AppServer> searchAppServer(Pager<AppServer> pager, Integer id);

	/**
	 * 添加应用服务器信息
	 * 
	 * @param appServer
	 * @return
	 */
	public ExecuteResult<AppServer> createAppServer(AppServer appServer);

	/**
	 * 更新应用服务器信息
	 * 
	 * @param appServer
	 * @return
	 */
	public ExecuteResult<AppServer> updateAppServer(AppServer appServer);

	/**
	 * 根据id获取AppServer对象
	 * 
	 * @param id
	 * @return
	 */
	public AppServer getAppServerById(Integer id);

	/**
	 * 根据id删除AppServer
	 * 
	 * @param id
	 * @return
	 */
	public ExecuteResult<AppServer> deleteAppServerById(Integer id);
}
