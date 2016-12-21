
package fr.kdefombelle.jmx;

import java.text.MessageFormat;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import fr.kdefombelle.jmx.JmxConstants;


public class ObjectNameFactory {   
    
    private String objectNamePrefix;
    
    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public ObjectNameFactory(String domain) {
    	this.objectNamePrefix = domain + ":" + JmxConstants.KEY_TYPE + "={0}," + JmxConstants.KEY_NAME + "={1}";
	}

	public ObjectName createObjectName(String objectName) {
        ObjectName o = null;
        try {
            o = new ObjectName(MessageFormat.format(objectNamePrefix, "", objectName));
        } catch (MalformedObjectNameException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
        return o;
    }

    public ObjectName createObjectName(String type, String objectName) {
        ObjectName o = null;
        try {
            o = new ObjectName(MessageFormat.format(objectNamePrefix, type, objectName));
        } catch (MalformedObjectNameException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
        return o;
    }
}
