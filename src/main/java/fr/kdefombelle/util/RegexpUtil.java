package fr.kdefombelle.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;


public class RegexpUtil {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private List<Pattern> patterns;
    private Map<String, String> regexpBySubstituteValue;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public RegexpUtil(Map<String, String> regexpBySubstituteValue) {
        this.regexpBySubstituteValue = regexpBySubstituteValue;
        this.patterns = compileXpathExpressions(regexpBySubstituteValue.keySet());
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public String replace(String input) {
        String newString = input;
        Optional<Pattern> pattern = patterns.stream().filter(p -> p.matcher(input).find()).findFirst();
        if (pattern.isPresent()) {
			String replacement = regexpBySubstituteValue.get(pattern.get().pattern());
			newString = input.replaceAll(input, replacement);
		}
        return newString;	
    }

    private List<Pattern> compileXpathExpressions(final Set<String> xpathsRegex) {
        List<Pattern> compiledXpaths = new ArrayList<Pattern>();
        xpathsRegex.stream().forEach(x -> compiledXpaths.add(Pattern.compile(x)));
        return compiledXpaths;
    }
}
