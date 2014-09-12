package com.haier.openplatform.hopdeploy;

import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;

import com.haier.openplatform.hopdeploy.util.CacheNames;
import com.haier.openplatform.hopdeploy.util.SysconfigInitListener;
import com.haier.openplatform.hopdeploy.util.SystemBootstrap;
import com.haier.openplatform.security.LoginContext;
import com.haier.openplatform.security.LoginContextHolder;
import com.haier.openplatform.test.dbunit.BaseHopTestCase;

/**
 * @author WangXuzheng
 * 
 */
@ContextConfiguration(locations = { "classpath*:/hmc/hornetq/hmc-jms-hornetq.xml",
		"classpath*:/console/audit/console-audit.xml", "classpath*:/console/jms/console-jms-provider.xml",
		"classpath*:/spring/spring-*.xml", "classpath*:/spring/cache/spring-cache-test.xml",
		"classpath*:/spring/cache/spring-cache-security.xml", "classpath*:/spring/security/spring-*.xml",
		"classpath*:/spring/deploy/spring-*.xml", "classpath*:/spring/portal/spring-portal.xml" })
@Ignore
public class BaseTestCase extends BaseHopTestCase {
	@Resource
	protected CacheManager ehcacheManager;
	protected Cache userCache;
	protected Cache roleCache;
	protected Cache resourceCache;
	protected Cache sessionCache;
	protected Cache moduleCache;

	@Before
	public void prepareCache() {
		userCache = ehcacheManager.getCache(CacheNames.CACHE_NAME_USER);
		roleCache = ehcacheManager.getCache(CacheNames.CACHE_NAME_ROLE);
		resourceCache = ehcacheManager.getCache(CacheNames.CACHE_NAME_RESOURCE);
		sessionCache = ehcacheManager.getCache(CacheNames.CACHE_NAME_SESSION);
		moduleCache = ehcacheManager.getCache(CacheNames.CACHE_NAME_MODULE);
	}

	@Before
	public void prepareLoginContext() {
		LoginContext context = new LoginContext();
		context.setUserId(99999999L);
		context.setUserName("UNIT_TEST");
		LoginContextHolder.put(context);
	}

	@BeforeClass
	public static void initEnv() throws Exception {
		SystemBootstrap.init();
		SysconfigInitListener sysconfigInitListener = new SysconfigInitListener();
		ServletContextEvent contextEvent = new ServletContextEvent(new MockServletContext());
		sysconfigInitListener.onStartup(contextEvent);

	}

	@Override
	public Properties getDBunitConfigProperties() {
		Properties properties = new Properties();
		// properties.put(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES,
		// "true");//每个表前缀添加schema名
		return properties;
	}
}
