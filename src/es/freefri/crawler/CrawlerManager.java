package es.freefri.crawler;

import es.freefri.crawler.io.reader.CrawlerReader;
import es.freefri.crawler.io.reader.UrlToCrawl;
import es.freefri.crawler.io.writer.CrawlerWriter;
import org.htmlcleaner.XPatherException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class CrawlerManager {

    private Crawler crawler;
    private CrawlerReader reader;
    private CrawlerWriter writer;

    public CrawlerManager(CrawlerReader reader, CrawlerWriter writer) throws FileNotFoundException {
        crawler = new Crawler();
        this.reader = reader;
        this.writer = writer;
    }

    public void crawlAll() throws IOException, XPatherException {
        for (UrlToCrawl urlToCrawl : reader.readAllUrlsToCrawl()) {
            Map results = crawler.crawl(urlToCrawl.getUrl(), urlToCrawl.getMapTitleXpath());
            writer.writeLine(results);
        }

    }
}
