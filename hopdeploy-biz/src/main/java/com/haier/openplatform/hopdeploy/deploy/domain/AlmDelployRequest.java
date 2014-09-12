package com.haier.openplatform.hopdeploy.deploy.domain;

public class AlmDelployRequest {
	private Long id;
	private String packageName;
	private Integer isUrgent;
	private Integer isSuccessful;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getIsSuccessful() {
		return isSuccessful;
	}

	public void setIsSuccessful(Integer isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

}
