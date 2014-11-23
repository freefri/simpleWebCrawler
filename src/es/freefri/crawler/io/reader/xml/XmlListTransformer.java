package es.freefri.crawler.io.reader.xml;

import es.freefri.crawler.io.reader.InvalidFileFormat;
import es.freefri.crawler.io.reader.UrlToCrawl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class XmlListTransformer {
    private XmlFile xmlFile;

    public XmlListTransformer(XmlFile xmlFile) {
        this.xmlFile = xmlFile;
    }

    public static List<UrlToCrawl> mixLists(List<List<String>> urlList, List<Map<String, String>> xpathList) throws InvalidFileFormat {
        List<UrlToCrawl> finalList = new LinkedList<UrlToCrawl>();
        int listSize = urlList.size();
        if (listSize != xpathList.size()) {
            throw new InvalidFileFormat("Both lists must have same amount of elements");
        }
        try {
            for (int i = 0; i < listSize; i++) {
                for (String url : urlList.get(i)) {
                    finalList.add(new UrlToCrawl(new URL(url), xpathList.get(i)));
                }
            }
        } catch (MalformedURLException e) {
            throw new InvalidFileFormat(e);
        }
        return finalList;
    }

    public List<List<String>> getUrlLists() throws InvalidFileFormat {
        List<String> urlAmounts = xmlFile.get("urlAmounts");
        int urlsAmount = xmlFile.getSize("urls");
        int sumUrlAmount = 0;
        int incrAmount = 0;
        List<List<String>> finalList = new LinkedList<List<String>>();
        List<String> groupedStr;
        for (String a : urlAmounts) {
            Integer amount = Integer.parseInt(a);
            sumUrlAmount += amount;
            groupedStr = new LinkedList<String>();
            for (int i = incrAmount; i < amount + incrAmount; i++) {
                groupedStr.add(xmlFile.getElement("urls", i));
            }
            incrAmount += amount;
            finalList.add(groupedStr);
        }
        if (sumUrlAmount != urlsAmount) {
            throw new InvalidFileFormat("Urls@amount does not match with real url amount");
        }
        return finalList;
    }

    public List<Map<String, String>> getXpathMapList() throws InvalidFileFormat {
        List<String> xpathList = xmlFile.get("elementAmount");
        int xpathsAmount = xmlFile.getSize("xpaths");
        int titlesAmount = xmlFile.getSize("titles");
        if (xpathsAmount != titlesAmount) {
            throw new InvalidFileFormat("Title and xpath attributes amount must be equals");
        }
        int sumXpathAmount = 0;
        int incrAmount = 0;
        List<Map<String, String>> finalList = new LinkedList<Map<String, String>>();
        Map<String, String> groupedElement;
        for (String a : xpathList) {
            Integer amount = Integer.parseInt(a);
            sumXpathAmount += amount;
            groupedElement = new HashMap<String, String>();
            for (int i = incrAmount; i < amount + incrAmount; i++) {
                groupedElement.put(xmlFile.getElement("titles", i), xmlFile.getElement("xpaths", i));
            }
            incrAmount += amount;
            finalList.add(groupedElement);
        }
        if (sumXpathAmount != xpathsAmount) {
            throw new InvalidFileFormat("Urls@amount does not match with real url amount");
        }
        return finalList;
    }
}