
package fr.kdefombelle.jmx;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import javax.management.remote.JMXServiceURL;

/**
 * The JMX Service URL, It could be:<br/>
 *
 * <ul>
 * <li>JMX_RMI_SERVER_PORT fixed: <code>
 * service:jmx:rmi://<HOST>:<JMX_RMI_SERVER_PORT>/jndi/rmi://<HOST>:<RMI_REGISTRY_PORT>/jmxrmi</code></li>
 * <li>usually it is dynamically allocated by the JVM: <code>
 * service:jmx:rmi://<HOST>:/jndi/rmi://<HOST>:<RMI_REGISTRY_PORT>/jmxrmi</code></li>
 * <li>HOST for the JMX_RMI_SERVER_PORT could be deduced from the HOST of the
 * RmiRegistry: <code>
 * service:jmx:rmi:///jndi/rmi://<HOST>:<RMI_REGISTRY_PORT>/jmxrmi</code></li>
 * </ul>
 */
public class JmxUrlBuilder {

	private String host;

	private String rmiRegistryPort;

	public JmxUrlBuilder setHost(String host) {
		this.host = host;
		return this;
	}

	public JmxUrlBuilder setRmiRegistryPort(String rmiRegistryPort) {
		this.rmiRegistryPort = rmiRegistryPort;
		return this;
	}

	public String build() {
		String jmxUrl = getUrlAsString();
		return jmxUrl;
	}

	public JMXServiceURL buildUrl() {
		JMXServiceURL url = null;
		try {
			url = new JMXServiceURL(getUrlAsString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}

	private String getUrlAsString() {
		if (rmiRegistryPort == null) {
			throw new IllegalArgumentException("The rmi registry port cannot be null");
		}
		if (host == null) {
			try {
				host = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return "service:jmx:rmi:///jndi/rmi://" + host + ":" + rmiRegistryPort + "/jmxrmi";
	}

}
