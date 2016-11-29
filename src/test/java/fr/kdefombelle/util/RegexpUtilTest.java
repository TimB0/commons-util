package fr.kdefombelle.util;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class RegexpUtilTest {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("a?c", "SUBSTITUTE");
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private RegexpUtil regexpUtil = new RegexpUtil(map);

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    @Test
    public void testRegexpUtil() throws Exception {
        assertEquals("SUBSTITUTE", regexpUtil.replace("abc"));
    }

}
