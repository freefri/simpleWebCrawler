package es.freefri.crawler.io.writer.db.connectorConfig;

import es.freefri.crawler.io.writer.db.ConnectionConfiguration;

public class MySQLConnectionConfiguration extends ServerConnectionConfiguration implements ConnectionConfiguration {

    private int portNumber = 3306;

    public MySQLConnectionConfiguration(String userName, String password, String serverName) {
        super(userName, password, serverName);
    }

    public MySQLConnectionConfiguration(
            String userName, String password, String serverName, String dbName) {
        super(userName, password, serverName, dbName);
    }

    public MySQLConnectionConfiguration(
            String userName, String password,
            String serverName, String dbName, int portNumber) {

        this(userName, password, serverName, dbName);
        this.portNumber = portNumber;
    }

    @Override
    protected String getServerType() {
        return "mysql";
    }

    @Override
    protected int getPort() {
        return portNumber;
    }
}