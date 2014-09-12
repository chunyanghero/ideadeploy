package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.AppServer;
import com.haier.openplatform.util.Pager;

public class SearchAppServerAction extends BaseDeployAction {
	private static final long serialVersionUID = 1329143797481425771L;
	private Pager<AppServer> pager = new Pager<AppServer>();
	private AppServer appServer = new AppServer();
	private Integer id;

	@Override
	public String execute() throws Exception {

		this.pager = this.buildPagerFromRequestParameters();
		pager = appServerService.searchAppServer(pager, id);
		return SUCCESS;
	}

	public Pager<AppServer> getPager() {
		return pager;
	}

	public void setPager(Pager<AppServer> pager) {
		this.pager = pager;
	}

	public AppServer getAppServer() {
		return appServer;
	}

	public void setAppServer(AppServer appServer) {
		this.appServer = appServer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
