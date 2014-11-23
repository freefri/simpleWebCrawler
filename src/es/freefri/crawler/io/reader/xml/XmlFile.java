package es.freefri.crawler.io.reader.xml;

import es.freefri.crawler.io.reader.InvalidFileFormat;

import java.util.List;
import java.util.Map;

public class XmlFile {

    private Map fileContents;

    public XmlFile(Map parsedFile) {
        fileContents = parsedFile;
    }

    public List<String> get(String key) throws InvalidFileFormat {
        Object o = fileContents.get(key);
        List<String> result = (List<String>) o;
        if (result == null) {
            throw new InvalidFileFormat("Undefined map key " + key);
        }
        return result;
    }

    public Integer getSize(String key) throws InvalidFileFormat {
        return get(key).size();
    }

    public String getElement(String key, int index) throws InvalidFileFormat {
        return get(key).get(index);
    }

    public String toString() {
        return fileContents.toString();
    }
}
