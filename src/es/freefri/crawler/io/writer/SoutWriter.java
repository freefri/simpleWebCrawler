package es.freefri.crawler.io.writer;

import java.util.Map;

public class SoutWriter implements CrawlerWriter {

    public void writeLine(Map line) {
        System.out.println(line.toString());
    }
}
