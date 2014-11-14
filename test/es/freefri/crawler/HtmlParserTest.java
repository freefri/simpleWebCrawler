package es.freefri.crawler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HtmlParserTest {

    private String exampleHtml = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title>Ping Test 4 Load Balancer</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <h1>     Load <strong>Balancer</strong> Health  Test</h1>\n" +
            "</body>\n" +
            "</html>";

    @Test
    public void parse() throws Exception {
        Map<String, List<String>> expectedMap = new LinkedHashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("Load Balancer Health Test");
        expectedMap.put("header", list);

        Map result = new HtmlParser().parse(exampleHtml, getRulesMap("//h1/text()"));

        assertEquals(expectedMap, result);
    }

    private Map<String, String> getRulesMap(String xpathExpression) {
        Map<String, String> extracts = new LinkedHashMap<String, String>();
        extracts.put("header", xpathExpression);
        return extracts;
    }

    @Test
    public void parseWithNotFoundNodes() throws Exception {
        Map<String, List<String>> expected = new LinkedHashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        expected.put("header", list);

        Map result = new HtmlParser().parse(exampleHtml, getRulesMap("//xx/text()"));

        assertEquals(expected, result);
    }

    @Test(expected = org.htmlcleaner.XPatherException.class)
    public void parseWithInvalidXpathExpressionThrowsException() throws Exception {
        new HtmlParser().parse(exampleHtml, getRulesMap("//body/*[not(self::span)]"));
    }

}