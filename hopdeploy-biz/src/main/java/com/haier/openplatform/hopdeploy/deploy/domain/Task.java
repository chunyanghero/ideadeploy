package com.haier.openplatform.hopdeploy.deploy.domain;

import java.util.List;

import com.haier.openplatform.domain.BaseDomain;

public class Task extends BaseDomain<Long> {
	private static final long serialVersionUID = 1887578907909307504L;
	private Long publishId;
	private Long taskId; // 发版任务号
	private String packageName;
	private String deployDate; // 发版结束时间
	private Integer deployType; // 0:手动发版 1:热部署 2:先卸载后脚本部署
	private Integer isUrgent;
	private Integer isSuccessful;
	// 0 暂且 未检测到有问题
	// 1 参数信息不完整
	// 2 不使用此系统部署
	// 3 windows操作系统，暂不使用此系统部署
	// 4 是不是打包类型选错了？或者没有将相应的数据存入数据库？
	// 5 weblogic部署部分节点，这些节点你配置了吗(╯▔皿▔)╯
	// 6 服务器口令错误
	// 7 FTP上不存在该文件
	// 8 tomcat || hop4.0 checkUrl为空
	// 9 Generic Error 用来做一个错误标识，不一定是什么具体错误
	private String deployMessage; // 发版反馈信息
	private Double executionTime;
	private List<String> result;
	private Integer MiddlewareType;

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", packageName=" + packageName + ", deployDate=" + deployDate
				+ ", deployType=" + deployType + ", isUrgent=" + isUrgent + ", isSuccessful=" + isSuccessful
				+ ", deployMessage=" + deployMessage + ", executionTime=" + executionTime + ", result=" + result + "]";
	}

	public Long getPublishId() {
		return publishId;
	}

	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}

	public List<String> getResult() {
		return result;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}

	public Long getTaskId() {
		return taskId;
	}

	public Integer getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(Integer isUrgent) {
		this.isUrgent = isUrgent;
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

	public String getDeployDate() {
		return deployDate;
	}

	public void setDeployDate(String deployDate) {
		this.deployDate = deployDate;
	}

	public Integer getDeployType() {
		return deployType;
	}

	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}

	public Integer getIsSuccessful() {
		return isSuccessful;
	}

	public void setIsSuccessful(Integer isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public String getDeployMessage() {
		return deployMessage;
	}

	public void setDeployMessage(String deployMessage) {
		this.deployMessage = deployMessage;
	}

	public Double getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Double executionTime) {
		this.executionTime = executionTime;
	}

	public Integer getMiddlewareType() {
		return MiddlewareType;
	}

	public void setMiddlewareType(Integer middlewareType) {
		MiddlewareType = middlewareType;
	}

}
