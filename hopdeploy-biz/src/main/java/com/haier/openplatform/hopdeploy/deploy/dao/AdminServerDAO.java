package com.haier.openplatform.hopdeploy.deploy.dao;

import org.apache.ibatis.annotations.Param;

import com.haier.openplatform.dao.CommonDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.AdminServer;

public interface AdminServerDAO extends CommonDAO<AdminServer, Integer> {
	/**
	 * 根据ip、port、MiddlewareType查询AdminServer是否已经存在
	 * 
	 * @return
	 */
	public AdminServer searchAdminServerByIpPortMTP(@Param("adminServer") AdminServer adminServer);

	public AdminServer getUsernamePasswdById(int id);
}
