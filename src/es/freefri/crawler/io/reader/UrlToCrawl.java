package es.freefri.crawler.io.reader;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class UrlToCrawl {

    private URL url;
    private Map<String, String> mapTitleXpath;

    public UrlToCrawl(URL url, Map<String, String> mapTitleXpath) {
        this.url = url;
        this.mapTitleXpath = mapTitleXpath;
    }

    public static Map<String, String> buildMap(String s) {
        Map<String, String> map = new HashMap<String, String>();
        s = s.substring(1, s.length() - 1);
        String[] elements = s.split(",");
        for (String element : elements) {
            String[] keyValue = element.split("=");
            map.put(keyValue[0].trim(), keyValue[1].trim());
        }
        return map;
    }

    public URL getUrl() {
        return url;
    }

    public Map<String, String> getMapTitleXpath() {
        return mapTitleXpath;
    }

    public String toString() {
        return "{" + getUrl().toString() + ", " + getMapTitleXpath().toString() + "}";
    }
}
