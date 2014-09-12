package com.haier.openplatform.hopdeploy.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockServletContext;

/**
 * @author WangXuzheng
 * 
 */
public class SysconfigInitListenerTest {
	private ServletContextEvent contextEvent;
	private ServletContext servletContext;

	@Before
	public void initMock() {
		servletContext = new MockServletContext();
		contextEvent = new ServletContextEvent(servletContext);
	}

	@Test
	public void testOnStartup() {
		SysconfigInitListener initListener = new SysconfigInitListener();
		initListener.onStartup(contextEvent);
		assertThat(servletContext.getAttribute("staticURL"), notNullValue());
		assertThat(servletContext.getAttribute("dynamicURL"), notNullValue());
	}
}
