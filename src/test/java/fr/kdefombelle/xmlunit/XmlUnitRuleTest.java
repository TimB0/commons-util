package fr.kdefombelle.xmlunit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.ClassRule;
import org.junit.Test;
import org.xml.sax.SAXException;


public class XmlUnitRuleTest {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    @ClassRule
    public static XmlUnitRule xmlUnitRule = new XmlUnitRule();

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    @Test
    public void testAttributes() throws SAXException, IOException {
        assertTrue(XMLUnit.getIgnoreWhitespace());
        assertTrue(XMLUnit.getIgnoreComments());
    }

    @Test
    public void testIgnoreComments() throws SAXException, IOException {
        String control = "<root><child1>value1</child1><!--here is a comment --><child2>value2</child2></root>";
        String test = "<root><child1>value1</child1><child2>value2</child2></root>";
        Diff diff = XMLUnit.compareXML(control, test);
        assertTrue(diff.identical());
    }

    @Test
    public void testIgnoreWhitespace() throws SAXException, IOException {
        String control = "<root><child1>value1</child1> <child2> value2</child2></root>";
        String test = "<root><child1>value1</child1><child2>value2</child2></root>";
        Diff diff = XMLUnit.compareXML(control, test);
        assertTrue(diff.identical());
    }
}
