package com.haier.openplatform.hopdeploy.deploy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haier.openplatform.dao.CommonDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.AppServer;
import com.haier.openplatform.util.Pager;

public interface AppServerDAO extends CommonDAO<AppServer, Integer> {
	/**
	 * 查询应用服务器信息
	 * 
	 * @return
	 */
	public List<AppServer> searchAppServer(@Param("pager") Pager<AppServer> pager, @Param("id") Integer id);

	/**
	 * 总条数
	 * 
	 * @return
	 */
	public Long searchAppServerCount(@Param("pager") Pager<AppServer> pager, @Param("id") Integer id);

	/**
	 * 根据Ip、port检查AppServer是否已经存在
	 * 
	 * @param appServer
	 * @return
	 */
	public AppServer searchAppServerByIpPort(@Param("appServer") AppServer appServer);

	/**
	 * 当AdminServer被删除时，其子节点也将被删除
	 * 
	 * @param weblogicId
	 */
	public void deleteByParentId(@Param("weblogicId") Integer weblogicId);
}
