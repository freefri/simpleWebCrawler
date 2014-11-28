package es.freefri.crawler.io.writer.db.connectorConfig;

import es.freefri.crawler.io.writer.db.ConnectionConfiguration;

import java.util.Properties;

public abstract class AbstractConnectionConfiguration implements ConnectionConfiguration {

    private String userName;
    private String password;

    public AbstractConnectionConfiguration() {
        super();
    }

    public AbstractConnectionConfiguration(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Properties getConnectionProperties() {
        final Properties properties = new Properties();

        if (this.userName != null) {
            properties.put("user", this.userName);
        }

        if (this.password != null) {
            properties.put("password", this.password);
        }

        return properties;
    }
}