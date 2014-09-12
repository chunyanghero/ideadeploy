package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.WeblogicShell;
import com.haier.openplatform.util.ExecuteResult;

public class UpdateWeblogicShellAction extends BaseDeployAction {
	private static final long serialVersionUID = -7820297980656390881L;
	private WeblogicShell weblogicShell;

	@Override
	public String execute() throws Exception {
		ExecuteResult<WeblogicShell> result = deployService.updateWeblogicShell(weblogicShell);
		if (!result.isSuccess()) {
			addErrorsFromResult(result);
		}
		return INPUT;
	}

	public WeblogicShell getWeblogicShell() {
		return weblogicShell;
	}

	public void setWeblogicShell(WeblogicShell weblogicShell) {
		this.weblogicShell = weblogicShell;
	}
}
