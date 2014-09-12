package com.haier.openplatform.hopdeploy.deploy.domain;

import java.util.List;

import com.haier.openplatform.domain.BaseDomain;

public class DeploySchedule extends BaseDomain<Integer> {
	private static final long serialVersionUID = 2787357845400528170L;
	private Integer id;
	private String packageName;
	private String executeTime;
	private String executor;
	private Integer status;
	private String deployMessage;
	private List<String> phoneNums;
	private Integer publishId;

	@Override
	public String toString() {
		return "DeploySchedule [id=" + id + ", packageName=" + packageName + ", executeTime=" + executeTime
				+ ", executor=" + executor + ", phoneNums=" + phoneNums + ", status=" + status + ", deployMessage="
				+ deployMessage + ", publishId=" + publishId + "]";
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public List<String> getPhoneNums() {
		return phoneNums;
	}

	public void setPhoneNums(List<String> phoneNums) {
		this.phoneNums = phoneNums;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDeployMessage() {
		return deployMessage;
	}

	public void setDeployMessage(String deployMessage) {
		this.deployMessage = deployMessage;
	}

	public Integer getPublishId() {
		return publishId;
	}

	public void setPublishId(Integer publishId) {
		this.publishId = publishId;
	}
}
