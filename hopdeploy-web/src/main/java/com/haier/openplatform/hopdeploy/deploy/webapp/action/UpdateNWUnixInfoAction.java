package com.haier.openplatform.hopdeploy.deploy.webapp.action;

import com.haier.openplatform.hopdeploy.deploy.domain.UnixInfo;

public class UpdateNWUnixInfoAction extends BaseDeployAction {
	private static final long serialVersionUID = 7939477434048328613L;
	private Integer id;
	private String ip;
	private String uu;
	private String up;
	private String fi;
	private String fu;
	private String fp;

	@Override
	public String execute() throws Exception {
		UnixInfo unixInfo = new UnixInfo();
		unixInfo.setId(id);
		unixInfo.setUnixUsername(uu);
		unixInfo.setUnixPassword(up);
		unixInfo.setFtpIp(fi);
		unixInfo.setFtpUsername(fu);
		unixInfo.setFtpPassword(fp);
		appRelateServerService.updateNWUnixInfo(unixInfo);
		return INPUT;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
