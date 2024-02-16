package org.example;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionUtil {

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");

    }
}
