package es.freefri.crawler.io.reader;

import java.io.IOException;
import java.util.List;

public interface CrawlerReader {
    List<UrlToCrawl> readAllUrlsToCrawl() throws IOException;
}
