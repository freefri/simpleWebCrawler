package es.freefri.crawler.io.reader;

import es.freefri.crawler.io.reader.xml.XmlReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XmlReaderTest {

    @Test
    public void readUrlToCrawl() throws Exception {
        XmlReader reader = new XmlReader("./test/es/freefri/crawler/io/toCrawl.xml");
        String expected = "[{http://54.72.118.190/pingtest.html, {header=//h1/text()}}, " +
                "{http://54.72.118.190/pingtest.html?v2, {header3=//h1/text(), header2=//h2/text()}}, " +
                "{http://54.72.118.190/pingtest.html?v3, {header3=//h1/text(), header2=//h2/text()}}]";
        assertEquals(expected, reader.readAllUrlsToCrawl().toString());
    }
}