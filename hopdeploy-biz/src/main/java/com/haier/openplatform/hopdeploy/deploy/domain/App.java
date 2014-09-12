package com.haier.openplatform.hopdeploy.deploy.domain;

import com.haier.openplatform.domain.BaseDomain;

/**
 * 对应数据库应用表
 * 根据applicationCode、applicationAbbr确定唯一性
 * 
 * @author spring
 * 
 */
public class App extends BaseDomain<Integer> {
	private static final long serialVersionUID = -3324425640596009793L;
	private Integer applicationId;
	private String applicationCode;
	private String applicationName;
	private Integer weblogicId;
	private String applicationAbbr;
	private String deployTutorial;
	private Integer deployType; // 0:不用此系统部署,1:热部署,2:卸载后命令部署
	private String engineer;
	private String engineerMail;
	private String engineerNumber;
	private String psiConner;
	private String psiConnerNumber;
	private Integer packageType; // 1:war 2:tar 3:zip
	private String deployNodes; // all: 默认所有的节点 或者多个节点以“,”隔开

	@Override
	public String toString() {
		return "App [applicationId=" + applicationId + ", applicationCode=" + applicationCode + ", applicationName="
				+ applicationName + ", weblogicId=" + weblogicId + ", applicationAbbr=" + applicationAbbr
				+ ", deployTutorial=" + deployTutorial + ", deployType=" + deployType + ", engineer=" + engineer
				+ ", engineerMail=" + engineerMail + ", engineerNumber=" + engineerNumber + ", psiConner=" + psiConner
				+ ", psiConnerNumber=" + psiConnerNumber + ", packageType=" + packageType + ", deployNodes="
				+ deployNodes + "]";
	}

	public String getDeployNodes() {
		return deployNodes;
	}

	public void setDeployNodes(String deployNodes) {
		this.deployNodes = deployNodes;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
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

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Integer getWeblogicId() {
		return weblogicId;
	}

	public void setWeblogicId(Integer weblogicId) {
		this.weblogicId = weblogicId;
	}

	public String getApplicationAbbr() {
		return applicationAbbr;
	}

	public void setApplicationAbbr(String applicationAbbr) {
		this.applicationAbbr = applicationAbbr;
	}

	public String getDeployTutorial() {
		return deployTutorial;
	}

	public void setDeployTutorial(String deployTutorial) {
		this.deployTutorial = deployTutorial;
	}

}
