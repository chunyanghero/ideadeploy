package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.UnixInfo;

public class UpdateUnixInfoAction extends BaseDeployAction {
	private static final long serialVersionUID = 7557898746295013112L;
	private String ip;
	private Integer pi;
	private String uu;
	private String up;
	private String fi;
	private String fu;
	private String fp;

	@Override
	public String execute() throws Exception {
		UnixInfo unixInfo = new UnixInfo();
		unixInfo.setIp(ip);
		unixInfo.setParentId(pi);
		unixInfo.setUnixUsername(uu);
		unixInfo.setUnixPassword(up);
		unixInfo.setFtpIp(fi);
		unixInfo.setFtpUsername(fu);
		unixInfo.setFtpPassword(fp);
		appRelateServerService.updateUnixInfo(unixInfo);
		return INPUT;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPi() {
		return pi;
	}

	public void setPi(Integer pi) {
		this.pi = pi;
	}

	public String getUu() {
		return uu;
	}

	public void setUu(String uu) {
		this.uu = uu;
	}

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

	public String getFi() {
		return fi;
	}

	public void setFi(String fi) {
		this.fi = fi;
	}

	public String getFu() {
		return fu;
	}

	public void setFu(String fu) {
		this.fu = fu;
	}

	public String getFp() {
		return fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
	}

}
