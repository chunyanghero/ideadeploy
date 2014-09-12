package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.WeblogicShell;

public class ManualDeployInitAction extends BaseDeployAction {
	private static final long serialVersionUID = 8692375609172888449L;
	private String packageName;
	private WeblogicShell weblogicShell;

	@Override
	public String execute() throws Exception {
		weblogicShell = deployService.searchWeblogicShell();
		return SUCCESS;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public WeblogicShell getWeblogicShell() {
		return weblogicShell;
	}

	public void setWeblogicShell(WeblogicShell weblogicShell) {
		this.weblogicShell = weblogicShell;
	}

}
