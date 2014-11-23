package es.freefri.crawler;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HtmlParser {

    private HtmlCleaner cleaner;

    public HtmlParser() {
        cleaner = new HtmlCleaner(getCleanerProperties());
    }

    private CleanerProperties getCleanerProperties() {
        CleanerProperties properties = new CleanerProperties();
        properties.setTranslateSpecialEntities(true);
        properties.setTransResCharsToNCR(true);
        properties.setOmitComments(true);
        properties.setOmitUnknownTags(false);
        properties.setUseCdataForScriptAndStyle(true);
        properties.setRecognizeUnicodeChars(true);
        return properties;
    }

    public Map parse(String contents, Map<String, String> extracts) throws XPatherException {
        //final CompactHtmlSerializer serializer = new CompactHtmlSerializer(getCleanerProperties());
        //contents = serializer.getAsString(contents);

        TagNode tagNode = cleaner.clean(contents);


        Map<String, List<String>> toRet = new LinkedHashMap<String, List<String>>();

        for (Map.Entry<String, String> entry : extracts.entrySet()) {
            toRet.put(entry.getKey(), evaluateXPath(entry.getValue(), tagNode));
        }

        return toRet;
    }

    private List<String> evaluateXPath(String rule, TagNode tagNode) throws XPatherException {
        List<String> nodes = new ArrayList<String>();
        try {
            for (Object item : tagNode.evaluateXPath(rule)) {
                nodes.add(item.toString().trim().replaceAll(" +", " "));
            }
        } catch (XPatherException e) {
            throw new XPatherException("Invalid xPath expression \"" + rule + "\"", e);
        }
        return nodes;
    }
}
