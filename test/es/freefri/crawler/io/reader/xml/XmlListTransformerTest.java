package es.freefri.crawler.io.reader.xml;

import es.freefri.crawler.io.reader.InvalidFileFormat;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class XmlListTransformerTest {

    @Test
    public void testGetUrlLists() throws Exception {
        List<List<String>> urlLists = getUrlLists();
        String expected = "[[http://x.com/?1], [http://x.com/?2, http://x.com/?3]]";
        assertEquals(expected, urlLists.toString());
    }

    private List<List<String>> getUrlLists() throws InvalidFileFormat {
        XmlFile xmlUrlFile = getXmlUrlFile(new String[]{"1", "2"});
        return new XmlListTransformer(xmlUrlFile).getUrlLists();
    }

    private XmlFile getXmlUrlFile(String[] urlAmounts) {
        return createXmlFileWithUrls(
                getStringList("http://x.com/?"),
                new LinkedList<String>(Arrays.asList(urlAmounts))
        );
    }

    @Test
    public void getUrlListsWithInverseUrlAmountList() throws Exception {
        XmlFile xmlFile = getXmlUrlFile(new String[]{"2", "1"});
        List<List<String>> urlLists = new XmlListTransformer(xmlFile).getUrlLists();
        String expected = "[[http://x.com/?1, http://x.com/?2], [http://x.com/?3]]";
        assertEquals(expected, urlLists.toString());
    }

    @Test(expected = InvalidFileFormat.class)
    public void getUrlListsWithInvalidUrlAmountThrowsException() throws Exception {
        XmlFile xmlFile = getXmlUrlFile(new String[]{"1", "1"});
        new XmlListTransformer(xmlFile).getUrlLists();
    }

    private XmlFile createXmlFileWithUrls(List<String> urls, List<String> urlAmounts) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("urls", urls);
        map.put("urlAmounts", urlAmounts);

        return new XmlFile(map);
    }

    private List<String> getStringList(String txt) {
        List<String> urls = new LinkedList<String>();
        urls.add(txt + "1");
        urls.add(txt + "2");
        urls.add(txt + "3");
        return urls;
    }

    @Test(expected = InvalidFileFormat.class)
    public void getXpathMapListThrowsExceptionWhenFileDoesNotHaveElements() throws Exception {
        XmlFile xmlFile = getXmlUrlFile(new String[]{"1", "2"});
        new XmlListTransformer(xmlFile).getXpathMapList();
    }

    @Test
    public void testGetXpathMapList() throws Exception {

        String expected = "[{title1=//x/1}, {title2=//x/2, title3=//x/3}]";
        assertEquals(expected, getXpathMapList().toString());
    }

    private List<Map<String, String>> getXpathMapList() throws InvalidFileFormat {
        XmlFile xmlXpathFile = getXmlXpathFile(new String[]{"1", "2"});
        return new XmlListTransformer(xmlXpathFile).getXpathMapList();
    }

    private XmlFile getXmlXpathFile(String[] elementAmount) {
        return createXmlFileWithXpath(
                getStringList("//x/"), getStringList("title"),
                new LinkedList<String>(Arrays.asList(elementAmount)));
    }

    private XmlFile createXmlFileWithXpath(List<String> xpaths, List<String> titles, List<String> elementAmount) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("xpaths", xpaths);
        map.put("titles", titles);
        map.put("elementAmount", elementAmount);

        return new XmlFile(map);
    }

    @Test
    public void mixList() throws Exception {
        XmlListTransformer.mixLists(getUrlLists(), getXpathMapList());
    }

    @Test(expected = InvalidFileFormat.class)
    public void mixListWithInvalidUrlsThrowsException() throws Exception {
        XmlListTransformer.mixLists(getInvalidUrlList(), getXpathMapList());
    }

    private List<List<String>> getInvalidUrlList() throws InvalidFileFormat {
        String[] urlAmounts = new String[]{"1", "2"};
        XmlFile xmlUrlFile = createXmlFileWithUrls(
                getStringList("invalidUrl"),
                new LinkedList<String>(Arrays.asList(urlAmounts))
        );
        return new XmlListTransformer(xmlUrlFile).getUrlLists();
    }
}