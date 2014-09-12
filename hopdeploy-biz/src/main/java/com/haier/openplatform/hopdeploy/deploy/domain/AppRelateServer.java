package com.haier.openplatform.hopdeploy.deploy.domain;

import com.haier.openplatform.domain.BaseDomain;

public class AppRelateServer extends BaseDomain<Integer> {
	private static final long serialVersionUID = 2870011832387611824L;
	private Integer applicationId;
	private String applicationCode;
	private String applicationName;
	private String applicationAbbr;
	private Integer weblogicId;
	private Integer id;
	private String ip;
	private String port;
	private Integer parentId;
	private String login;
	private String passwd;
	private String nodeName;
	private String memo;
	private Integer middlewareType; // 1:weblogic, 2:tar, 3:tomcat, 4:jboss
	private String middlewarePath;
	private String specificPath;
	private Integer osType; // 0:linux 1:windows
	private String checkUrl;
	private Integer deployType;

	private String engineer;
	private String engineerMail;
	private String engineerNumber;
	private String psiConner;
	private String psiConnerNumber;
	private Integer packageType; // 1:war 2:tar 3:zip
	private String deployNodes; // all:所有节点 或者多个节点以“,”隔开

	@Override
	public String toString() {
		return "AppRelateServer [applicationId=" + applicationId + ", applicationCode=" + applicationCode
				+ ", applicationName=" + applicationName + ", applicationAbbr=" + applicationAbbr + ", weblogicId="
				+ weblogicId + ", id=" + id + ", ip=" + ip + ", port=" + port + ", parentId=" + parentId + ", login="
				+ login + ", passwd=" + passwd + ", nodeName=" + nodeName + ", memo=" + memo + ", middlewareType="
				+ middlewareType + ", middlewarePath=" + middlewarePath + ", specificPath=" + specificPath
				+ ", osType=" + osType + ", checkUrl=" + checkUrl + ", deployType=" + deployType + ", engineer="
				+ engineer + ", engineerMail=" + engineerMail + ", engineerNumber=" + engineerNumber + ", psiConner="
				+ psiConner + ", psiConnerNumber=" + psiConnerNumber + ", packageType=" + packageType
				+ ", deployNodes=" + deployNodes + "]";
	}

	public String getDeployNodes() {
		return deployNodes;
	}

	public void setDeployNodes(String deployNodes) {
		this.deployNodes = deployNodes;
	}

	public Integer getDeployType() {
		return deployType;
	}

	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}

	public String getEngineer() {
		return engineer;
	}

	public String getSpecificPath() {
		return specificPath;
	}

	public void setSpecificPath(String specificPath) {
		this.specificPath = specificPath;
	}

	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}

	public String getEngineerMail() {
		return engineerMail;
	}

	public void setEngineerMail(String engineerMail) {
		this.engineerMail = engineerMail;
	}

	public String getEngineerNumber() {
		return engineerNumber;
	}

	public void setEngineerNumber(String engineerNumber) {
		this.engineerNumber = engineerNumber;
	}

	public String getPsiConner() {
		return psiConner;
	}

	public void setPsiConner(String psiConner) {
		this.psiConner = psiConner;
	}

	public String getPsiConnerNumber() {
		return psiConnerNumber;
	}

	public void setPsiConnerNumber(String psiConnerNumber) {
		this.psiConnerNumber = psiConnerNumber;
	}

	public Integer getPackageType() {
		return packageType;
	}

	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
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

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationAbbr() {
		return applicationAbbr;
	}

	public void setApplicationAbbr(String applicationAbbr) {
		this.applicationAbbr = applicationAbbr;
	}

	public Integer getWeblogicId() {
		return weblogicId;
	}

	public void setWeblogicId(Integer weblogicId) {
		this.weblogicId = weblogicId;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getMiddlewareType() {
		return middlewareType;
	}

	public void setMiddlewareType(Integer middlewareType) {
		this.middlewareType = middlewareType;
	}

}
