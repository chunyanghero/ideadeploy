package com.haier.openplatform.hopdeploy.deploy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haier.openplatform.dao.CommonDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.AdminServer;
import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;
import com.haier.openplatform.hopdeploy.deploy.domain.UnixInfo;
import com.haier.openplatform.util.Pager;

public interface AppRelateServerDAO extends CommonDAO<AppRelateServer, Integer> {
	/**
	 * 根据条件(AppRelateServer)查询
	 * 
	 * @param appRelateServer
	 * @return
	 */
	public List<AppRelateServer> searchAppRelateServer(@Param("pager") Pager<AppRelateServer> pager,
			@Param("appRelateServer") AppRelateServer appRelateServer);

	/**
	 * 模糊查询时combobox自动补全
	 * 
	 * @return
	 */
	public List<AppRelateServer> searchAppRelateServerForCombobox();

	/**
	 * 总数
	 * 
	 * @param appRelateServer
	 * @return
	 */
	public Long searchAppRelateServerCount(@Param("pager") Pager<AppRelateServer> pager,
			@Param("appRelateServer") AppRelateServer appRelateServer);

	/**
	 * 根据adminServer的ip、port判断管理服务器是否已经存在
	 * 
	 * @param adminServer
	 * @return
	 */
	public List<AdminServer> searchAdminServer(@Param("adminServer") AdminServer adminServer);

	/**
	 * 根据查询页面中服务器地址传来的id（实际上就是T_WEBLOGIC_INFO表的id）
	 * 搜寻出所有当前应用所部署的所有服务器的IP、用户、密码
	 * 
	 * @param id
	 * @return
	 */
	public List<UnixInfo> searchUnixInfo(@Param("id") Integer id);

	public List<UnixInfo> searchNWUnixInfo(@Param("id") Integer id);

	/**
	 * 根据ip更新服务器用户名、密码
	 * 
	 * @param unixinfo
	 * @return
	 */
	public int updateUnixInfo(@Param("unixInfo") UnixInfo unixInfo);

	public int updateNWUnixInfo(@Param("unixInfo") UnixInfo unixInfo);

	public int changeWeblogicPassword(@Param("appRelateServer") AppRelateServer appRelateServer);

	public AppRelateServer searchChangingPasswordServers(@Param("id") int id);

}
