package es.freefri.crawler;

import es.freefri.crawler.io.reader.CrawlerReader;
import es.freefri.crawler.io.reader.xml.XmlReader;
import org.junit.Test;

public class CrawlerManagerTest {

    @Test
    public void crawlAll() throws Exception {
        CrawlerReader reader = new XmlReader("./test/es/freefri/crawler/io/toCrawl.xml");
        CrawlerManager manager = new CrawlerManager();
        manager.crawlAll(reader);

    }
}