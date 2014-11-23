package es.freefri.crawler;

import es.freefri.crawler.io.reader.CrawlerReader;
import es.freefri.crawler.io.reader.UrlToCrawl;
import org.htmlcleaner.XPatherException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class CrawlerManager {

    private Crawler crawler;

    public CrawlerManager() throws FileNotFoundException {
        crawler = new Crawler();
    }

    public void crawlAll(CrawlerReader reader) throws IOException, XPatherException {
        for (UrlToCrawl urlToCrawl : reader.readAllUrlsToCrawl()) {
            Map results = crawler.crawl(urlToCrawl.getUrl(), urlToCrawl.getMapTitleXpath());
            System.out.println(results);
        }

    }
}
