package com.haier.openplatform.hopdeploy.util;

import java.util.Hashtable;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class ChangeUserPassword {
	public static final String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	public static final String MBEAN_SERVER = "weblogic.management.mbeanservers.domainruntime";
	public static final String JNDI_ROOT = "/jndi/";
	public static final String DEFAULT_PROTOCOL = "t3";
	public static final String PROTOCOL_PROVIDER_PACKAGES = "weblogic.management.remote";
	// This how we get our DomainRuntimeService, this is where
	// DomainConfigurationMBeans exists
	public static final String DOMAIN_MBEAN_NAME = "com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean";
	private static MBeanServerConnection connection;
	private static ObjectName defaultAuthenticator;
	private static ObjectName[] authenticationProviders;
	private static String authenticatorName = "DefaultAuthenticator";

	private static ObjectName getDefaultAuthenticator(String host, String port, String username, String password) {
		try {
			Hashtable<String, Object> h = new Hashtable<String, Object>();
			JMXServiceURL serviceURL;

			serviceURL = new JMXServiceURL(DEFAULT_PROTOCOL, host, Integer.valueOf(port).intValue(),
					"/jndi/weblogic.management.mbeanservers.domainruntime");

			h.put("java.naming.security.principal", username);
			h.put("java.naming.security.credentials", password);
			h.put("jmx.remote.protocol.provider.pkgs", "weblogic.management.remote");

			// Creating a JMXConnector to connect to JMX
			JMXConnector connector = JMXConnectorFactory.connect(serviceURL, h);

			connection = connector.getMBeanServerConnection();

			ObjectName configurationMBeans = new ObjectName(DOMAIN_MBEAN_NAME);
			ObjectName domain = (ObjectName) connection.getAttribute(configurationMBeans, "DomainConfiguration");

			ObjectName security = (ObjectName) connection.getAttribute(domain, "SecurityConfiguration");

			ObjectName realm = (ObjectName) connection.getAttribute(security, "DefaultRealm");

			authenticationProviders = (ObjectName[]) connection.getAttribute(realm, "AuthenticationProviders");

			for (int i = 0; i < authenticationProviders.length; i++) {
				String name = (String) connection.getAttribute(authenticationProviders[i], "Name");

				if (name.equals(authenticatorName))
					defaultAuthenticator = authenticationProviders[i];
			}
			return defaultAuthenticator;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean changeUserPassword(String host, String port, String username, String oldPassword, String newPassword) {
		try {
			defaultAuthenticator = getDefaultAuthenticator(host, port, username, oldPassword);
			connection.invoke(defaultAuthenticator, "changeUserPassword", new Object[] { username, oldPassword,
					newPassword }, new String[] { "java.lang.String", "java.lang.String", "java.lang.String" });

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}