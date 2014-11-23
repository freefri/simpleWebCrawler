package es.freefri.crawler.io.reader.xml;

import es.freefri.crawler.HtmlParser;
import es.freefri.crawler.io.reader.InvalidFileFormat;
import es.freefri.crawler.io.reader.UrlToCrawl;
import org.htmlcleaner.XPatherException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlReader extends FileReader implements es.freefri.crawler.io.reader.CrawlerReader {

    public XmlReader(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    @Override
    public List<UrlToCrawl> readAllUrlsToCrawl() throws IOException {
        BufferedReader br = new BufferedReader(this);
        HtmlParser parser = new HtmlParser();

        String file = "";
        String line;
        while ((line = br.readLine()) != null) {
            file += line;
        }
        br.close();

        try {
            XmlFile xmlFile = new XmlFile(parser.parse(file, getConfigMapXml()));
            XmlListTransformer listTransformer = new XmlListTransformer(xmlFile);

            List<List<String>> groupedUrlList = listTransformer.getUrlLists();
            List<Map<String, String>> groupedXpathList = listTransformer.getXpathMapList();

            return XmlListTransformer.mixLists(groupedUrlList, groupedXpathList);

        } catch (XPatherException e) {
            throw new InvalidFileFormat(e);
        }
    }

    private Map<String, String> getConfigMapXml() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("urls", "//url/@uri");
        map.put("isCrawled", "//url/@iscrawled");
        map.put("urlAmounts", "//urls/@amount");
        map.put("titles", "//element/@title");
        map.put("xpaths", "//element/@xpath");
        map.put("elementAmount", "//elements/@amount");


        return map;
    }

}
