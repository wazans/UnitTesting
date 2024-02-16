import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {

    @Test
    void testDatabaseConnection() throws SQLException {
        DatabaseMetaData metaData = null;
        try {
            // Attempt to establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");

            // Check if the connection is not null
            assertNotNull(connection);
            // Print table information
            metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, "PUBLIC", "%", null);
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);
            }

            // Close the connection
            //connection.close();
        } catch (SQLException e) {
            // Print any exception that occurs during the connection attempt
            e.printStackTrace();
        }
        ResultSet schemas = metaData.getSchemas();
        while (schemas.next()) {
            String schemaName = schemas.getString("TABLE_SCHEM");
            System.out.println("Schema: " + schemaName);
        }


    }
}
