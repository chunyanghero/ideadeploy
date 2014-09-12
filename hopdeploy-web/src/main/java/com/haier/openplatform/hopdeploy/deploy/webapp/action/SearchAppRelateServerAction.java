package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppRelateServer;
import com.haier.openplatform.util.Pager;

public class SearchAppRelateServerAction extends BaseDeployAction {
	private static final long serialVersionUID = 1616268338984515666L;
	private Pager<AppRelateServer> pager = new Pager<AppRelateServer>();
	private AppRelateServer appRelateServer = new AppRelateServer();

	@Override
	public String execute() throws Exception {
		this.pager = this.buildPagerFromRequestParameters();
		pager = appRelateServerService.searchAppRelateServer(pager, appRelateServer);
		return SUCCESS;
	}

	public Pager<AppRelateServer> getPager() {
		return pager;
	}

	public void setPager(Pager<AppRelateServer> pager) {
		this.pager = pager;
	}

	public AppRelateServer getAppRelateServer() {
		return appRelateServer;
	}

	public void setAppRelateServer(AppRelateServer appRelateServer) {
		this.appRelateServer = appRelateServer;
	}

}
