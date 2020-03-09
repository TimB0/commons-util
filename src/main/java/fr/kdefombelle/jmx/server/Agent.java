package fr.kdefombelle.jmx.server;

import fr.kdefombelle.jmx.ObjectNameFactory;

import javax.management.MBeanServer;

public interface Agent {

	void startJmxConnector();

	void stopJmxConnector();

	void registerMBean(Object beanToRegister, String objectName);

	void unregisterMBean(String type, String objectName);

	void registerMBean(Object beanToRegister, String type, String objectName);

	ObjectNameFactory getObjectNameFactory();

	MBeanServer getMbeanServer();
	
}
