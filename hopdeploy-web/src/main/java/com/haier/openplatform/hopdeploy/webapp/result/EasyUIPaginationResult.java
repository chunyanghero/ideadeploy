package com.haier.openplatform.hopdeploy.webapp.result;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.json.JSONResult;

import com.haier.openplatform.hopdeploy.webapp.action.BaseShowcaseAction;
import com.haier.openplatform.util.Pager;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * easyui分页json返回结果
 * 
 * @author WangXuzheng
 * 
 */
public class EasyUIPaginationResult extends JSONResult {
	private static final long serialVersionUID = 5107585748929317547L;

	@SuppressWarnings("unchecked")
	@Override
	protected Object findRootObject(ActionInvocation invocation) {
		Object object = invocation.getAction();
		BaseShowcaseAction action = (BaseShowcaseAction) object;
		Pager<Object> resultPager = null;
		try {
			resultPager = (Pager<Object>) PropertyUtils.getProperty(action, "pager");
		} catch (Exception e) {
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", resultPager == null ? "0" : resultPager.getTotalRecords());
		jsonMap.put("rows", resultPager == null ? "" : resultPager.getRecords());
		return JSONObject.fromObject(jsonMap);
	}
}
