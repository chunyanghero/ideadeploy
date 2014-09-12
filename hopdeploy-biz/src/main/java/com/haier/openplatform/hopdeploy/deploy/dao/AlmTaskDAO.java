package com.haier.openplatform.hopdeploy.deploy.dao;

import java.util.List;

import com.haier.openplatform.dao.CommonDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.AlmTask;

public interface AlmTaskDAO extends CommonDAO<AlmTask, Long> {
	/**
	 * 从alm数据库中获取数据以备与hopdeploy数据库中得到的PreTask合并
	 * 
	 * @return
	 */
	public List<AlmTask> searchAlmTask() throws DAOException;
}
