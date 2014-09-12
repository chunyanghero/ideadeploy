package com.haier.openplatform.hopdeploy.deploy.service.impl;

import java.util.List;

import com.haier.openplatform.hopdeploy.deploy.dao.AppServerDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.AppServer;
import com.haier.openplatform.hopdeploy.deploy.service.AppServerService;
import com.haier.openplatform.util.ExecuteResult;
import com.haier.openplatform.util.Pager;

public class AppServerServiceImpl implements AppServerService {
	private AppServerDAO appServerDAO;

	@Override
	public Pager<AppServer> searchAppServer(Pager<AppServer> pager, Integer id) {
		List<AppServer> list = appServerDAO.searchAppServer(pager, id);
		Long total = appServerDAO.searchAppServerCount(pager, id);
		return Pager.cloneFromPager(pager, total, list);

	}

	@Override
	public ExecuteResult<AppServer> createAppServer(AppServer appServer) {
		ExecuteResult<AppServer> executeResult = new ExecuteResult<AppServer>();
		AppServer as = appServerDAO.searchAppServerByIpPort(appServer);
		if (as != null && as.getId() != appServer.getId() && as.getParentId() == appServer.getParentId()) {
			executeResult.addErrorMessage("应用节点服务器已经存在，不可重复添加！");
		} else {
			appServerDAO.save(appServer);
		}

		return executeResult;
	}

	@Override
	public ExecuteResult<AppServer> updateAppServer(AppServer appServer) {
		ExecuteResult<AppServer> executeResult = new ExecuteResult<AppServer>();
		AppServer as = appServerDAO.searchAppServerByIpPort(appServer);
		if (as != null && as.getId() != appServer.getId() && as.getParentId() == appServer.getParentId()) {
			executeResult.addErrorMessage("应用节点服务器已经存在，不可重复添加！");
		} else {
			appServerDAO.update(appServer);
		}
		return executeResult;
	}

	@Override
	public AppServer getAppServerById(Integer id) {
		return appServerDAO.get(id);
	}

	@Override
	public ExecuteResult<AppServer> deleteAppServerById(Integer id) {
		ExecuteResult<AppServer> executeResult = new ExecuteResult<AppServer>();
		if (appServerDAO.get(id) == null) {
			executeResult.addErrorMessage("指定AppServer不存在！");
			return executeResult;
		}
		appServerDAO.delete(id);
		return executeResult;
	}

	public AppServerDAO getAppServerDAO() {
		return appServerDAO;
	}

	public void setAppServerDAO(AppServerDAO appServerDAO) {
		this.appServerDAO = appServerDAO;
	}

}
