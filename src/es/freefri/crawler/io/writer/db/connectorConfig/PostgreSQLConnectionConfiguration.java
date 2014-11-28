package es.freefri.crawler.io.writer.db.connectorConfig;

import es.freefri.crawler.io.writer.db.ConnectionConfiguration;

public class PostgreSQLConnectionConfiguration extends ServerConnectionConfiguration implements ConnectionConfiguration {

    private int portNumber = 5432;

    public PostgreSQLConnectionConfiguration(String userName, String password, String serverName) {
        super(userName, password, serverName);
    }

    public PostgreSQLConnectionConfiguration(
            String userName, String password, String serverName, String dbName) {
        super(userName, password, serverName, dbName);
    }

    public PostgreSQLConnectionConfiguration(
            String userName, String password,
            String serverName, String dbName, int portNumber) {

        this(userName, password, serverName, dbName);
        this.portNumber = portNumber;
    }

    @Override
    protected String getServerType() {
        return "postgresql";
    }

    @Override
    protected int getPort() {
        return portNumber;
    }
}