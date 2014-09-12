package com.haier.openplatform.hopdeploy.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.haier.openplatform.util.SystemStartupListener;

/**
 * 加载env.properties中的配置项，将静态资源地址和动态资源地址放到application变量中
 * 
 * @author WangXuzheng
 * 
 */
public class SysconfigInitListener implements SystemStartupListener {
	@Override
	public void onStartup(ServletContextEvent contextEvent) {
		SystemBootstrap.init();
		ServletContext servletContext = contextEvent.getServletContext();
		servletContext.setAttribute("appUrl", Env.getProperty(Env.APP_URL));
		servletContext.setAttribute("staticURL", Env.getProperty(Env.KEY_STATIC_URL));
		servletContext.setAttribute("dynamicURL", Env.getProperty(Env.KEY_DYNAMIC_URL));
		servletContext.setAttribute("easyUIVersion", Env.getProperty(Env.KEY_JQUERY_EASY_VERSION));
		servletContext.setAttribute("easyUITheme", Env.getProperty(Env.KEY_JQUERY_EASY_THEME));
		servletContext.setAttribute("casServerUrlPrefix", Env.getProperty(Env.KEY_CAS_SERVER_URL_PREFIX));
		servletContext.setAttribute("casServerName", Env.getProperty(Env.KEY_CAS_SERVER_NAME));
	}
}
