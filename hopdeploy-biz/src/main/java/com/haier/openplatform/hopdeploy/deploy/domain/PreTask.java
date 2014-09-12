package com.haier.openplatform.hopdeploy.deploy.domain;

import java.sql.Date;

import com.haier.openplatform.domain.BaseDomain;

public class PreTask extends BaseDomain<Long> {
	private static final long serialVersionUID = 8880794568377568015L;
	private Long publishId;
	private Long taskId; // 发版任务号
	private String packageName;
	private Integer packageType; // 1:war 2:tar 3:zip
	private String applicationCode; // 即alm系统中的war_id
	private String state; // 状态：5-评估中(需要发版)
	private Date requestDate;
	private Integer isUrgent;
	private String applicationAbbr;
	private Integer deployType; // 0:手动发版 1:热部署 2:先卸载后脚本部署
	private String deployNodes;
	private String ip;
	private String port;
	private String login; // weblogic 登录用户名
	private String passwd; // weblogic 登录密码
	private String unixUsername; // 服务器用户名
	private String unixPassword; // 服务器密码
	private String ftpIp;
	private String ftpUsername;
	private String ftpPassword;
	private Integer middlewareType; // 1:weblogic, 2:tar, 3:tomcat, 4:jboss
	private String middlewarePath; // 中间件路径 主要指tomcat 或者tar jboss
	private String specificPath; // tomcat webapp路径可能被指定成别的路径 ; hop4.0的jetty路径
	private Integer osType; // 0:linux, 1:windows
	private String checkUrl;

	@Override
	public String toString() {
		return "PreTask [publishId=" + publishId + ", taskId=" + taskId + ", packageName=" + packageName
				+ ", packageType=" + packageType + ", applicationCode=" + applicationCode + ", state=" + state
				+ ", requestDate=" + requestDate + ", isUrgent=" + isUrgent + ", applicationAbbr=" + applicationAbbr
				+ ", deployType=" + deployType + ", deployNodes=" + deployNodes + ", ip=" + ip + ", port=" + port
				+ ", login=" + login + ", passwd=" + passwd + ", unixUsername=" + unixUsername + ", unixPassword="
				+ unixPassword + ", ftpIp=" + ftpIp + ", ftpUsername=" + ftpUsername + ", ftpPassword=" + ftpPassword
				+ ", middlewareType=" + middlewareType + ", middlewarePath=" + middlewarePath + ", specificPath="
				+ specificPath + ", osType=" + osType + ", checkUrl=" + checkUrl + "]";
	}

	public String getDeployNodes() {
		return deployNodes;
	}

	public void setDeployNodes(String deployNodes) {
		this.deployNodes = deployNodes;
	}

	public Integer getIsUrgent() {
		return isUrgent;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public String getUnixUsername() {
		return unixUsername;
	}

	public void setUnixUsername(String unixUsername) {
		this.unixUsername = unixUsername;
	}

	public String getSpecificPath() {
		return specificPath;
	}

	public void setSpecificPath(String specificPath) {
		this.specificPath = specificPath;
	}

	public String getUnixPassword() {
		return unixPassword;
	}

	public void setUnixPassword(String unixPassword) {
		this.unixPassword = unixPassword;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public void setIsUrgent(Integer isUrgent) {
		this.isUrgent = isUrgent;
	}

	public Integer getPackageType() {
		return packageType;
	}

	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}

	public Long getPublishId() {
		return publishId;
	}

	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationAbbr() {
		return applicationAbbr;
	}

	public void setApplicationAbbr(String applicationAbbr) {
		this.applicationAbbr = applicationAbbr;
	}

	public Integer getDeployType() {
		return deployType;
	}

	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	public Integer getMiddlewareType() {
		return middlewareType;
	}

	public void setMiddlewareType(Integer middlewareType) {
		this.middlewareType = middlewareType;
	}

	public String getMiddlewarePath() {
		return middlewarePath;
	}

	public void setMiddlewarePath(String middlewarePath) {
		this.middlewarePath = middlewarePath;
	}

	public Integer getOsType() {
		return osType;
	}

	public void setOsType(Integer osType) {
		this.osType = osType;
	}

	public String getCheckUrl() {
		return checkUrl;
	}

	public void setCheckUrl(String checkUrl) {
		this.checkUrl = checkUrl;
	}

}
