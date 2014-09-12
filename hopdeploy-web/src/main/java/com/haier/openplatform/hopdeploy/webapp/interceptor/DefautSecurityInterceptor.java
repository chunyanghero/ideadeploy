package com.haier.openplatform.hopdeploy.webapp.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.haier.openplatform.security.AbstractAuthenticator;
import com.haier.openplatform.security.Authentication;
import com.haier.openplatform.security.interceptor.AbstractAuthenticationInterceptor;

/**
 * @author WangXuzheng
 * 
 */
public class DefautSecurityInterceptor extends AbstractAuthenticationInterceptor {
	private static final long serialVersionUID = -7145007423822243392L;
	private static final String SUFFIX = ".action";
	/**
	 * 琚拷鐣ヤ笉琚畨鍏ㄦ鏌ョ殑url
	 */
	private List<String> ignoralList = new ArrayList<String>();

	public List<String> getIgnoralList() {
		return ignoralList;
	}

	public void setIgnoralList(List<String> ignoralList) {
		this.ignoralList = ignoralList;
	}

	@Override
	public Authentication getAuthentication(Long userId) {
		// List<ResourceDTO> resources =
		// resourceServiceClientAdapter.getResource(userId,
		// LoginContextHolder.get()
		// .getLanguage());
		Authentication authentication = new Authentication();
		// for (ResourceDTO resource : resources) {
		// ResourceTypeEnum resourceType =
		// ResourceTypeEnum.toEnum(resource.getType());
		// if (resourceType == ResourceTypeEnum.URL_RESOURCE || resourceType ==
		// ResourceTypeEnum.TODO_RESOURCE) {
		// authentication.getUrlResources().add(resource.getUrl());
		// if (StringUtils.isNotEmpty(resource.getCode())) {
		// authentication.getComponentResources().add(resource.getCode());
		// }
		// } else if (resourceType == ResourceTypeEnum.COMPONENT_RESOURCE) {
		// authentication.getComponentResources().add(resource.getCode());
		// }
		// }
		return authentication;
	}

	@Override
	protected AbstractAuthenticator getAuthenticator(Authentication authentication) {
		return new AbstractAuthenticator(authentication) {
			@Override
			public boolean hasUrlAuth(String url) {
				String actualUrl = StringUtils.defaultIfEmpty(url, "/");
				if (!actualUrl.startsWith("/")) {
					actualUrl = "/" + url;
				}
				for (String resource : getAuthentication().getUrlResources()) {
					if (resource != null && actualUrl.startsWith(resource) && resource.contains(SUFFIX)) {
						return true;
					}
				}
				for (String resource : ignoralList) {
					if (actualUrl.startsWith(resource)) {
						return true;
					}
				}
				if (!url.contains(SUFFIX)) {
					return true;
				}
				return false;
			}
		};
	}
}
