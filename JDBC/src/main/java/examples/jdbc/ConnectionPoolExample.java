package examples.jdbc;

import java.sql.Connection;

public class ConnectionPoolExample {

    public static void main(String[] args) throws Exception {

        Connection connection = DBCPDataSource.getConnection();

    }
}
