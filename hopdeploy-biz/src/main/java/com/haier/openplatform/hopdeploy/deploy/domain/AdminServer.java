package com.haier.openplatform.hopdeploy.deploy.domain;

/**
 * 根据ip、port确定server的唯一性
 * 
 * @author spring
 * 
 */
public class AdminServer extends Server {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3053309217958512608L;
	private String login;
	private String passwd;
	private Integer parentId;
	private String nodeName;

	public AdminServer() {
		this.setParentId(0);
	}

	@Override
	public String toString() {
		return "AdminServer [login=" + login + ", passwd=" + passwd + ", getIp()=" + getIp() + ", getPort()="
				+ getPort() + ", getParentId()=" + getParentId() + ", getNodeName()=" + getNodeName() + ", getMemo()="
				+ getMemo() + "]";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
