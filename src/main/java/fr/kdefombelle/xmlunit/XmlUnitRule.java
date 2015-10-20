package fr.kdefombelle.xmlunit;

import org.custommonkey.xmlunit.XMLUnit;
import org.junit.rules.ExternalResource;


public class XmlUnitRule extends ExternalResource {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private boolean ignoreSpace;
    private boolean ignoreComments;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    protected void before() throws Throwable {
        ignoreSpace = XMLUnit.getIgnoreWhitespace();
        ignoreComments = XMLUnit.getIgnoreComments();
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
    }

    @Override
    protected void after() {
        XMLUnit.setIgnoreComments(ignoreComments);
        XMLUnit.setIgnoreWhitespace(ignoreSpace);
    }
}
