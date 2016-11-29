package fr.kdefombelle.util;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XpathQuerierTest {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private XpathQuerier xpathQuerier = new XpathQuerier();

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    @Test
    public void testExecuteXpath() throws Exception {
        InputStream is = StringUtil.loadResource(this.getClass(), "fr/kdefombelle/util/xpath.xml");
        List<String> children = xpathQuerier.executeXpathNodeListAsString(is, "/root/children/child");
        assertEquals(2, children.size());
        assertEquals("1", children.get(0));

        children.clear();
        List<String> childrenIds = new ArrayList<String>();
        is = StringUtil.loadResource(this.getClass(), "fr/kdefombelle/util/xpath.xml");
        NodeList childrenNodeList = xpathQuerier.executeXpathNodeList(is, "/root/children/child");
        for (int i = 0; i < childrenNodeList.getLength(); i++) {
            Node childNode = childrenNodeList.item(i);
            childrenIds.add(childNode.getAttributes().getNamedItem("id").getNodeValue());
            children.add(childNode.getTextContent());
        }
        assertEquals(2, childrenIds.size());
        assertEquals(2, children.size());
        assertEquals("Thomas", children.get(0));
        assertEquals("Lia", children.get(1));
    }

}
