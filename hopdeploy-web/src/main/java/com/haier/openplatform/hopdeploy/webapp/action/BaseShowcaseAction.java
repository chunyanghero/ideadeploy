package com.haier.openplatform.hopdeploy.webapp.action;

import org.apache.commons.lang.StringUtils;

import com.haier.openplatform.security.AbstractAuthenticator;
import com.haier.openplatform.security.Authentication;
import com.haier.openplatform.security.DefaultUrlAuthenticator;
import com.haier.openplatform.security.SessionSecurityConstants;
import com.haier.openplatform.util.Pager;
import com.haier.openplatform.webapp.action.BaseAppAction;

/**
 * Show case工程的基类action
 * 
 * @author WangXuzheng
 * 
 */
public class BaseShowcaseAction extends BaseAppAction {
	private static final long serialVersionUID = 8161343308502020496L;

	// easyui分页相关的参数
	protected long rows;
	protected long total;
	protected long pageSize;
	protected String sortName;
	protected String sortOrder;
	protected long page;
	protected String queryWord;
	protected String queryType;

	public boolean hasComponentAuth(String componentCode) {
		return getAuthenticator().hasComponentAuth(componentCode);
	}

	public boolean hasUrlAuth(String url) {
		return getAuthenticator().hasUrlAuth(url);
	}

	private AbstractAuthenticator getAuthenticator() {
		Authentication authentication = (Authentication) getSession().getAttribute(
				SessionSecurityConstants.KEY_AUTHENTICATION);
		AbstractAuthenticator authenticator = new DefaultUrlAuthenticator(authentication);
		return authenticator;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getRows() {
		return this.rows;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotal() {
		return this.total;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public String getQueryWord() {
		return queryWord;
	}

	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	/**
	 * 从分页参数组装分页对象
	 * 
	 * @return
	 */
	protected <T> Pager<T> buildPagerFromRequestParameters() {
		Pager<T> pager = new Pager<T>();
		pager.setPageSize(this.getRows());
		pager.setCurrentPage(this.getPage());
		if (StringUtils.isNotBlank(this.getSortName())) {
			pager.setOrderProperty(this.getSortName());
		}
		if (StringUtils.isNotBlank(this.getSortOrder())) {
			pager.setOrder(this.getSortOrder());
		}
		return pager;
	}
}
