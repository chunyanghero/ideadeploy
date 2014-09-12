package com.haier.openplatform.hopdeploy.deploy.domain;

import com.haier.openplatform.domain.BaseDomain;

public class AlmTask extends BaseDomain<Long> {
	private static final long serialVersionUID = 1028614331299749275L;
	private Long publishId; // alm发版任务主键，将其作为Task表主键
	private Long taskId; // 发版任务号
	private String packageName;
	private String applicationCode; // 即alm系统中的war_id
	private Integer isUrgent;
	private String state; // 状态：5-评估中(需要发版)

	@Override
	public String toString() {
		return "AlmTask [publishId=" + publishId + ", taskId=" + taskId + ", packageName=" + packageName
				+ ", isUrgent=" + isUrgent + "]";
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

	public Integer getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(Integer isUrgent) {
		this.isUrgent = isUrgent;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
