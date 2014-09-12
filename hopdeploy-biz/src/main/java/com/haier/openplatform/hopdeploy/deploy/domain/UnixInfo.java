package com.haier.openplatform.hopdeploy.deploy.domain;

import com.haier.openplatform.domain.BaseDomain;

public class UnixInfo extends BaseDomain<Integer> {
	private static final long serialVersionUID = 4187294202884599769L;
	private Integer id;
	private Integer parentId;
	private String ip;
	private String unixUsername;
	private String unixPassword;
	private String ftpIp;
	private String ftpUsername;
	private String ftpPassword;

	@Override
	public String toString() {
		return "UnixInfo [id=" + id + ", ip=" + ip + ", unixUsername=" + unixUsername + ", unixPassword="
				+ unixPassword + ", ftpIp=" + ftpIp + ", ftpUsername=" + ftpUsername + ", ftpPassword=" + ftpPassword
				+ "]";
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUnixUsername() {
		return unixUsername;
	}

	public void setUnixUsername(String unixUsername) {
		this.unixUsername = unixUsername;
	}

	public String getUnixPassword() {
		return unixPassword;
	}

	public void setUnixPassword(String unixPassword) {
		this.unixPassword = unixPassword;
	}

	public String getFtpIp() {
		return ftpIp;
	}

	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	public String getFtpUsername() {
		return ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

}
