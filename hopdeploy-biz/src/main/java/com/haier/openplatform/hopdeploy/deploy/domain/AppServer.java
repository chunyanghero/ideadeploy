package com.haier.openplatform.hopdeploy.deploy.domain;

/**
 * 根据ip、port确定server的唯一性
 * 
 * @author spring
 * 
 */
public class AppServer extends Server {
	private static final long serialVersionUID = 8851108107647675395L;
	private Integer parentId;
	private String nodeName;

	@Override
	public String toString() {
		return "AppServer [parentId=" + parentId + ", nodeName=" + nodeName + ", getSpecificPath()="
				+ getSpecificPath() + ", getId()=" + getId() + ", getMiddlewarePath()=" + getMiddlewarePath()
				+ ", getOsType()=" + getOsType() + ", getCheckUrl()=" + getCheckUrl() + ", getIp()=" + getIp()
				+ ", getPort()=" + getPort() + ", getMemo()=" + getMemo() + ", getMiddlewareType()="
				+ getMiddlewareType() + "]";
	}

	public AppServer() {
		this.setMiddlewareType(1);
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

}
