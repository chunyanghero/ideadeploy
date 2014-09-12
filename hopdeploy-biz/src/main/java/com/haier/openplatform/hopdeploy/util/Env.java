package com.haier.openplatform.hopdeploy.util;

import java.util.Properties;

/**
 * 读取env.properties配置文件中的配置项
 * 
 * @author WangXuzheng
 * 
 */
public final class Env {
	public static final String KEY_STATIC_URL = "server.static";
	public static final String KEY_DYNAMIC_URL = "server.dynamic";
	public static final String KEY_SERVER_NAME = "server.name";
	public static final String APP_NAME = "app.name";
	public static final String APP_URL = "app.url";
	public static final String APP_EAMIL = "app.email";
	public static final String SYS_ADMIN = "system.admin";
	public static final String ENV_TYPE = "env.type";
	public static final String KEY_JQUERY_EASY_VERSION = "jquery.easyui.version";
	public static final String KEY_JQUERY_EASY_THEME = "jquery.easyui.theme";
	public static final String KEY_CAS_SERVER_URL_PREFIX = "cas.server.urlPrefix";
	public static final String KEY_CAS_SERVER_NAME = "cas.server.name";
	private static Properties props;

	public static synchronized void init(Properties properties) {
		props = properties;
	}

	/**
	 * 读取配置项
	 * 
	 * @param key
	 * @return
	 */
	public static final String getProperty(String key) {
		return props.getProperty(key);
	}
}
