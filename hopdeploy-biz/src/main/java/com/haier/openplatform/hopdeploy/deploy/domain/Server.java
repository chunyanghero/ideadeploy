package com.haier.openplatform.hopdeploy.deploy.domain;

import com.haier.openplatform.domain.BaseDomain;

/**
 * AdminServer & Appserver基类
 * 
 * @author spring
 * 
 */
public class Server extends BaseDomain<Integer> {
	private static final long serialVersionUID = -3533520754680477883L;
	private Integer id;
	private String ip;
	private String port;

	private String memo;
	// 1:weblogic, 2:tar, 3:tomcat, 4:jboss,
	// *5:weblogicShell, 6:hop4.0 static,
	// 7:hop4.0 war
	private Integer middlewareType;
	private String middlewarePath;
	private Integer osType; // 0:linux, 1:windows
	private String checkUrl;
	private String specificPath;

	public String getSpecificPath() {
		return specificPath;
	}

	public void setSpecificPath(String specificPath) {
		this.specificPath = specificPath;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
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
