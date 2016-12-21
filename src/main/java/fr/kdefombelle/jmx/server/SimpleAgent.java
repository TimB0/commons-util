package fr.kdefombelle.jmx.server;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import fr.kdefombelle.jmx.ObjectNameFactory;

/**
 * <p>Demo JMX Agent. It is used to start and stop the JMX connector and register / unregister MBean.</p>
 *
 * @author kdefombelle
 */
public class SimpleAgent implements Agent {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private final JMXConnectorServer jmxConnectorServer;
    private final MBeanServer mbeanServer;
    private final ObjectNameFactory objectNameFactory;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public SimpleAgent(String stringUrl, Map<String, ?> env, ObjectNameFactory objectNameFactory) {
        JMXServiceURL url;
        try {
            url = new JMXServiceURL(stringUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Get the platform MBeanServer
        mbeanServer = ManagementFactory.getPlatformMBeanServer();

        try {
            jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, env, mbeanServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.objectNameFactory = objectNameFactory;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public MBeanServer getMbeanServer() {
        return mbeanServer;
    }

    @Override
    public void startJmxConnector() {
        try {
            jmxConnectorServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stopJmxConnector() {
        try {
            jmxConnectorServer.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerMBean(Object beanToRegister, String objectName) {
        try {
            getMbeanServer().registerMBean(beanToRegister, objectNameFactory.createObjectName(objectName));
        } catch (InstanceAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (MBeanRegistrationException e) {
            throw new RuntimeException(e);
        } catch (NotCompliantMBeanException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unregisterMBean(String type, String objectName) {        
        try {
            getMbeanServer().unregisterMBean(objectNameFactory.createObjectName(type, objectName));
        } catch (MBeanRegistrationException e) {
            throw new RuntimeException(e);
        } catch (InstanceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerMBean(Object beanToRegister, String type, String objectName) {
        try {
            getMbeanServer().registerMBean(beanToRegister, objectNameFactory.createObjectName(type, objectName));
        } catch (InstanceAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (MBeanRegistrationException e) {
            throw new RuntimeException(e);
        } catch (NotCompliantMBeanException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObjectNameFactory getObjectNameFactory() {
        return objectNameFactory;
    }

}
