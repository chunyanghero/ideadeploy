package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.UnixInfo;
import com.haier.openplatform.util.Pager;

public class SearchUnixInfoAction extends BaseDeployAction {
	private static final long serialVersionUID = -2365194746435785521L;
	private int id;
	private Pager<UnixInfo> pager = new Pager<UnixInfo>();

	@Override
	public String execute() throws Exception {
		this.pager = this.buildPagerFromRequestParameters();
		pager = appRelateServerService.searchUnixInfo(id);
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pager<UnixInfo> getPager() {
		return pager;
	}

	public void setPager(Pager<UnixInfo> pager) {
		this.pager = pager;
	}

}
