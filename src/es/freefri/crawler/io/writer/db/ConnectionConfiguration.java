package es.freefri.crawler.io.writer.db;

import java.util.Properties;

public interface ConnectionConfiguration {

    public String getConnectionString();

    public Properties getConnectionProperties();

}