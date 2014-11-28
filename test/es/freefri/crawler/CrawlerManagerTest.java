package es.freefri.crawler;

import es.freefri.crawler.io.reader.CrawlerReader;
import es.freefri.crawler.io.reader.xml.XmlReader;
import es.freefri.crawler.io.writer.CrawlerWriter;
import es.freefri.crawler.io.writer.SoutWriter;
import org.junit.Test;

public class CrawlerManagerTest {

    @Test
    public void crawlAll() throws Exception {
        CrawlerReader reader = new XmlReader("./test/es/freefri/crawler/io/toCrawl.xml");
        CrawlerWriter writer = new SoutWriter();
        CrawlerManager manager = new CrawlerManager(reader, writer);
        manager.crawlAll();

    }
}