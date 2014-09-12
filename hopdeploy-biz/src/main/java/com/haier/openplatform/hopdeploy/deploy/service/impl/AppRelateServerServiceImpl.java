package com.haier.openplatform.hopdeploy.deploy.service.impl;

import java.util.List;

import com.haier.openplatform.hopdeploy.deploy.dao.AdminServerDAO;
import com.haier.openplatform.hopdeploy.deploy.dao.AppDAO;
import com.haier.openplatform.hopdeploy.deploy.dao.AppRelateServerDAO;
import com.haier.openplatform.hopdeploy.deploy.dao.AppServerDAO;
import com.haier.openplatform.hopdeploy.deploy.domain.AdminServer;
import com.haier.openplatform.hopdeploy.deploy.domain.App;
import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;
import com.haier.openplatform.hopdeploy.deploy.domain.UnixInfo;
import com.haier.openplatform.hopdeploy.deploy.service.AppRelateServerService;
import com.haier.openplatform.hopdeploy.util.ChangeUserPassword;
import com.haier.openplatform.util.ExecuteResult;
import com.haier.openplatform.util.Pager;

public class AppRelateServerServiceImpl implements AppRelateServerService {
	private AppRelateServerDAO appRelateServerDAO;
	private AdminServerDAO adminServerDAO;
	private AppServerDAO appServerDAO;
	private AppDAO appDAO;

	@Override
	public Pager<AppRelateServer> searchAppRelateServer(Pager<AppRelateServer> pager, AppRelateServer appRelateServer) {
		List<AppRelateServer> list = appRelateServerDAO.searchAppRelateServer(pager, appRelateServer);
		Long total = appRelateServerDAO.searchAppRelateServerCount(pager, appRelateServer);
		return Pager.cloneFromPager(pager, total, list);
	}

	@Override
	public List<AppRelateServer> searchAppRelateServerForCombobox() {
		return appRelateServerDAO.searchAppRelateServerForCombobox();
	}

	@Override
	public ExecuteResult<AppRelateServer> createAppRelateServer(AppRelateServer appRelateServer) {
		ExecuteResult<AppRelateServer> executeResult = new ExecuteResult<AppRelateServer>();
		AdminServer adminServer = new AdminServer();
		adminServer.setIp(appRelateServer.getIp());
		adminServer.setPort(appRelateServer.getPort());
		adminServer.setMiddlewareType(appRelateServer.getMiddlewareType());
		adminServer.setMiddlewarePath(appRelateServer.getMiddlewarePath());
		AdminServer as = adminServerDAO.searchAdminServerByIpPortMTP(adminServer);
		App app = new App();
		app.setApplicationCode(appRelateServer.getApplicationCode());
		app.setApplicationAbbr(appRelateServer.getApplicationAbbr());
		app.setPackageType(appRelateServer.getPackageType());
		App a = appDAO.searchAppByCodeAbbrPT(app);
		if (as != null && a == null) {
			app.setWeblogicId(as.getId());
			setAppFromAppRelateServer(app, appRelateServer);
			appDAO.save(app);
		} else if (as != null && a != null) {
			executeResult.addErrorMessage("应用及其服务器已经存在，不可重复添加!");
			return executeResult;
		} else if (as == null && a != null) {
			executeResult.addErrorMessage("一个应用不可对应多个服务器!");
			return executeResult;
		} else if (as == null && a == null) {
			setAdminServerFromAppRelateServer(adminServer, appRelateServer);
			if (adminServer.getMiddlewareType() != 1) {
				adminServer.setLogin("");
				adminServer.setPasswd("");
			}
			adminServerDAO.save(adminServer);
			// 保存上adminServer后，取出id放到应用表weblogic_id中
			as = adminServerDAO.searchAdminServerByIpPortMTP(adminServer);
			app.setWeblogicId(as.getId());

			setAppFromAppRelateServer(app, appRelateServer);
			appDAO.save(app);
		}
		return executeResult;
	}

	@Override
	public ExecuteResult<AppRelateServer> updateAppRelateServer(AppRelateServer appRelateServer) {
		ExecuteResult<AppRelateServer> executeResult = new ExecuteResult<AppRelateServer>();

		/* 从appRelateServer中取出AdminServer的信息 */
		AdminServer adminServer = new AdminServer();
		adminServer.setId(appRelateServer.getWeblogicId());
		adminServer.setPort(appRelateServer.getPort());
		adminServer.setMiddlewareType(appRelateServer.getMiddlewareType());
		adminServer.setMiddlewarePath(appRelateServer.getMiddlewarePath());
		AdminServer as = adminServerDAO.searchAdminServerByIpPortMTP(adminServer);

		/* 从appRelateServer中取出App的信息 */
		App app = new App();
		app.setApplicationId(appRelateServer.getApplicationId());
		app.setApplicationCode(appRelateServer.getApplicationCode());
		app.setApplicationAbbr(appRelateServer.getApplicationAbbr());
		app.setWeblogicId(appRelateServer.getWeblogicId());
		app.setPackageType(appRelateServer.getPackageType());
		App a = appDAO.searchAppByCodeAbbrPT(app);

		/* 此处if是为了满足一个应用要更新到其他应用的管理服务器ip、port时 */
		if (as != null && !as.getId().equals(adminServer.getId())) {
			app.setWeblogicId(as.getId());
		} else {
			// 只有符合条件才从appRelateServer中把其他信息添加上，并更新
			setAdminServerFromAppRelateServer(adminServer, appRelateServer);

			adminServerDAO.update(adminServer);
		}

		if (a != null && !a.getApplicationId().equals(app.getApplicationId())) {
			executeResult.addErrorMessage("应用已经存在，不可重复添加!");
			return executeResult;
		}
		// 只有符合条件才从appRelateServer中把其他信息添加上，并更新
		setAppFromAppRelateServer(app, appRelateServer);
		appDAO.update(app);

		return executeResult;
	}

	private void setAdminServerFromAppRelateServer(AdminServer adminServer, AppRelateServer appRelateServer) {
		adminServer.setIp(appRelateServer.getIp());
		adminServer.setLogin(appRelateServer.getLogin());
		adminServer.setPasswd(appRelateServer.getPasswd());
		adminServer.setNodeName(appRelateServer.getNodeName());
		adminServer.setMemo(appRelateServer.getMemo());
		adminServer.setOsType(appRelateServer.getOsType());
		adminServer.setCheckUrl(appRelateServer.getCheckUrl());
		adminServer.setSpecificPath(appRelateServer.getSpecificPath());
	}

	private void setAppFromAppRelateServer(App app, AppRelateServer appRelateServer) {
		app.setApplicationName(appRelateServer.getApplicationName());

		app.setEngineer(appRelateServer.getEngineer());
		app.setEngineerMail(appRelateServer.getEngineerMail());
		app.setEngineerNumber(appRelateServer.getEngineerNumber());
		app.setPsiConner(appRelateServer.getPsiConner());
		app.setPsiConnerNumber(appRelateServer.getPsiConnerNumber());
		app.setDeployType(appRelateServer.getDeployType());
		app.setDeployNodes(appRelateServer.getDeployNodes());
	}

	@Override
	public AppRelateServer getAppRelateServerByApplicationId(Integer applicationId) {

		return appRelateServerDAO.get(applicationId);
	}

	@Override
	public ExecuteResult<AppRelateServer> deleteAppRelateServerById(Integer applicationId) {
		ExecuteResult<AppRelateServer> result = new ExecuteResult<AppRelateServer>();
		Integer weblogicId = appDAO.get(applicationId).getWeblogicId();
		if (weblogicId == null) {
			result.addErrorMessage("指定的应用没有关联管理服务器！");
			return result;
		}
		int count = appDAO.searchAppCountByWeblogicId(weblogicId);
		if (count == 1) {
			appDAO.delete(applicationId);
			adminServerDAO.delete(weblogicId);
			appServerDAO.deleteByParentId(weblogicId);

		} else if (count > 1) {
			appDAO.delete(applicationId);
		}

		return result;
	}

	@Override
	public Pager<UnixInfo> searchUnixInfo(Integer id) {
		Pager<UnixInfo> pager = new Pager<UnixInfo>();
		List<UnixInfo> list = appRelateServerDAO.searchUnixInfo(id);
		long total = 0;
		return Pager.cloneFromPager(pager, total, list);
	}

	@Override
	public int updateUnixInfo(UnixInfo unixInfo) {
		return appRelateServerDAO.updateUnixInfo(unixInfo);
	}

	@Override
	public Pager<UnixInfo> searchNWUnixInfo(Integer id) {
		Pager<UnixInfo> pager = new Pager<UnixInfo>();
		List<UnixInfo> list = appRelateServerDAO.searchNWUnixInfo(id);

		long total = 0;
		return Pager.cloneFromPager(pager, total, list);
	}

	@Override
	public int updateNWUnixInfo(UnixInfo unixInfo) {
		return appRelateServerDAO.updateNWUnixInfo(unixInfo);
	}

	@Override
	public ExecuteResult<AppRelateServer> changeWeblogicPassword(AppRelateServer appRelateServer) {

		ExecuteResult<AppRelateServer> executeResult = new ExecuteResult<AppRelateServer>();
		AppRelateServer ars = appRelateServerDAO.searchChangingPasswordServers(appRelateServer.getId());

		Boolean bool = new ChangeUserPassword().changeUserPassword(ars.getIp(), ars.getPort(), ars.getLogin(),
				ars.getPasswd(), appRelateServer.getPasswd());

		if (bool != true) {
			executeResult.addErrorMessage("weblogic console更改密码时失败");
			return executeResult;
		}

		if (appRelateServerDAO.changeWeblogicPassword(appRelateServer) != 1)
			executeResult.addErrorMessage("发版系统数据库里密码更新失败");

		return executeResult;
	}

	@Override
	public AdminServer getUsernamePasswdById(int id) {
		return adminServerDAO.getUsernamePasswdById(id);
	}

	public AppRelateServerDAO getAppRelateServerDAO() {
		return appRelateServerDAO;
	}

	public void setAppRelateServerDAO(AppRelateServerDAO appRelateServerDAO) {
		this.appRelateServerDAO = appRelateServerDAO;
	}

	public AdminServerDAO getAdminServerDAO() {
		return adminServerDAO;
	}

	public void setAdminServerDAO(AdminServerDAO adminServerDAO) {
		this.adminServerDAO = adminServerDAO;
	}

	public AppServerDAO getAppServerDAO() {
		return appServerDAO;
	}

	public void setAppServerDAO(AppServerDAO appServerDAO) {
		this.appServerDAO = appServerDAO;
	}

	public AppDAO getAppDAO() {
		return appDAO;
	}

	public void setAppDAO(AppDAO appDAO) {
		this.appDAO = appDAO;
	}

}
