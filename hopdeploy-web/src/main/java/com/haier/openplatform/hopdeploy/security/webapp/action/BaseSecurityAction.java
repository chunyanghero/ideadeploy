package com.haier.openplatform.hopdeploy.security.webapp.action;

import com.haier.openplatform.hopdeploy.security.service.UserService;
import com.haier.openplatform.hopdeploy.webapp.action.BaseShowcaseAction;

/**
 * Security模块的Action基类
 * 
 * @author WangXuzheng
 * 
 */
public class BaseSecurityAction extends BaseShowcaseAction {
	private static final long serialVersionUID = -6329296055785356921L;
	protected UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
