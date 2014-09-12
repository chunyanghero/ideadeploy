package com.haier.openplatform.hopdeploy.portal.webapp.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haier.openplatform.hopdeploy.webapp.action.BaseShowcaseAction;
import com.haier.openplatform.security.SessionSecurityConstants;
import com.haier.openplatform.util.AESOperator;

public class PortalRedirectAction extends BaseShowcaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6970134302501429648L;
	private static final Logger LOG = LoggerFactory.getLogger(PortalRedirectAction.class);
	/**
	 * 跳转到的url
	 */
	private String redirectUrl;

	public String execute() {
		if (StringUtils.isBlank(redirectUrl)) {
			return SUCCESS;
		}
		String reverPwd = (String) this.getRequest().getSession().getAttribute(
				SessionSecurityConstants.KEY_REVERSIBLE_PASSWORD);
		String casSessionId = (String) this.getRequest().getSession().getAttribute(
				SessionSecurityConstants.KEY_CAS_SESSIONID);
		if (redirectUrl.contains(reverPwd)) {
			try {
				String decodePwd = AESOperator.decrypt(reverPwd, casSessionId.substring(0, 16),
						casSessionId.substring(casSessionId.length() - 16));
				redirectUrl = redirectUrl.replace(reverPwd, decodePwd);
			} catch (Exception e) {
				LOG.error("decode error,pwd=" + reverPwd, e);
			}
		}
		return SUCCESS;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
