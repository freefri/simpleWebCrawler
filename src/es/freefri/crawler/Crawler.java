package es.freefri.crawler;

import org.htmlcleaner.XPatherException;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class Crawler {

    private Spider spider;
    private HtmlParser htmlParser;

    public Crawler() {
        spider = new Spider();
        htmlParser = new HtmlParser();
    }

    public Map crawl(URL url, Map<String, String> extracts) throws IOException, XPatherException {
        String html = spider.getWeb(url);
        return htmlParser.parse(html, extracts);
    }
}
