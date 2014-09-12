package com.haier.openplatform.hopdeploy.webapp.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginDeployInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -8834813606365605767L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例
		ActionContext actx = invocation.getInvocationContext();
		Map<String, Object> session = actx.getSession();
		String user = (String) session.get("username");
		// 如果没有登陆，返回重新登陆
		if (user != null) {
			return invocation.invoke();
		}
		return "login";
	}

}
