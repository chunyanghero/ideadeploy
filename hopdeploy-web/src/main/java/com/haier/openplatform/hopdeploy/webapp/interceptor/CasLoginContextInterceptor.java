package com.haier.openplatform.hopdeploy.webapp.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;

import com.haier.openplatform.security.LoginContext;
import com.haier.openplatform.security.LoginContextHolder;
import com.haier.openplatform.security.SessionSecurityConstants;
import com.haier.openplatform.security.interceptor.LoginContextInterceptor;
import com.haier.openplatform.util.IpUtil;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * @author WangXuzheng
 * 
 */
public class CasLoginContextInterceptor extends LoginContextInterceptor {
	private static final long serialVersionUID = -6282763466404724965L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
		HttpSession httpSession = httpServletRequest.getSession();
		Assertion assertion = AssertionHolder.getAssertion();
		String userName = (String) httpSession.getAttribute(SessionSecurityConstants.KEY_USER_NAME);
		if (userName == null) {// 通过单点登录系统第一次跳转过来
			userName = AssertionHolder.getAssertion().getPrincipal().getName();
			httpSession.setAttribute(SessionSecurityConstants.KEY_USER_NAME, userName);
			// 其他一些session的信息在这里设置
			httpSession.setAttribute(SessionSecurityConstants.KEY_USER_ID,
					Long.valueOf(assertion.getPrincipal().getAttributes().get("userId").toString()));
			httpSession.setAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME, assertion.getPrincipal().getName());
			httpSession.setAttribute(SessionSecurityConstants.KEY_REVERSIBLE_PASSWORD,
					assertion.getPrincipal().getAttributes().get(SessionSecurityConstants.KEY_REVERSIBLE_PASSWORD));
			httpSession.setAttribute(SessionSecurityConstants.KEY_CAS_SESSIONID,
					assertion.getPrincipal().getAttributes().get(SessionSecurityConstants.KEY_CAS_SESSIONID));
		}
		LoginContext loginContext = buildLoginContext(assertion);
		String result = null;
		try {
			LoginContextHolder.put(loginContext);
			invocation.invoke();
		} finally {
			LoginContextHolder.clear();
		}
		return result;
	}

	protected LoginContext buildLoginContext(Assertion assertion) {
		String userName = (String) assertion.getPrincipal().getAttributes().get("loginName");
		Long userId = Long.valueOf(assertion.getPrincipal().getAttributes().get("userId").toString());
		Locale language = new Locale(assertion.getPrincipal().getAttributes().get("locale").toString());
		LoginContext loginContext = createLoginContext();
		loginContext.setUserId(userId);
		loginContext.setUserName(userName);
		loginContext.setIp(IpUtil.getIpAddress(ServletActionContext.getRequest()));
		loginContext.setLanguage(language.toString());
		return loginContext;
	}
}
