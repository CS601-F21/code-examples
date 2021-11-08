package examples.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import util.Config;
import util.Utilities;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Example of using Apache DBCP ConnectionPool.
 * Taken from https://www.baeldung.com/java-connection-pooling
 */
public class DBCPDataSource {

    private static BasicDataSource ds = new BasicDataSource();


    static {
        Config config = Utilities.readConfig();
        if(config == null) {
            System.exit(1);
        }
        ds.setUrl("jdbc:mysql://localhost:3306/" + config.getDatabase());
        ds.setUsername(config.getUsername());
        ds.setPassword(config.getPassword());
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBCPDataSource(){ }
}