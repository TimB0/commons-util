package fr.kdefombelle.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class XpathQuerier {


    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    List<String> executeXpathNodeListAsString(InputStream sourceXml, String xpathQuery) throws XPathExpressionException {
        List<String> ids = new ArrayList<>();
        NodeList nodes = executeXpathNodeList(sourceXml, xpathQuery);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            ids.add(node.getAttributes().getNamedItem("id").getNodeValue());
        }
        return ids;
    }

    NodeList executeXpathNodeList(InputStream sourceXml, String xPathQuery) throws XPathExpressionException {
        InputSource source = new InputSource(sourceXml);

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        XPathExpression xPathExpression = xpath.compile(xPathQuery);

        return (NodeList) xPathExpression.evaluate(source, XPathConstants.NODESET);
    }

}
