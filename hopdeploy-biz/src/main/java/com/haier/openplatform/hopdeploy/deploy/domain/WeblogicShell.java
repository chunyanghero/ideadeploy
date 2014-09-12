package com.haier.openplatform.hopdeploy.deploy.domain;

import com.haier.openplatform.domain.BaseDomain;

public class WeblogicShell extends BaseDomain<Integer> {

	private static final long serialVersionUID = -4276852928336768345L;
	private String weblogicShellIp;
	private String weblogicShellUsername;
	private String weblogicShellPassword;

	public String getWeblogicShellIp() {
		return weblogicShellIp;
	}

	public void setWeblogicShellIp(String weblogicShellIp) {
		this.weblogicShellIp = weblogicShellIp;
	}

	public String getWeblogicShellUsername() {
		return weblogicShellUsername;
	}

	public void setWeblogicShellUsername(String weblogicShellUsername) {
		this.weblogicShellUsername = weblogicShellUsername;
	}

	public String getWeblogicShellPassword() {
		return weblogicShellPassword;
	}

	public void setWeblogicShellPassword(String weblogicShellPassword) {
		this.weblogicShellPassword = weblogicShellPassword;
	}
}
