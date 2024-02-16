import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateTableTest {

    @Test
    void testTableCreation() {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");

            // Create a statement
            Statement statement = connection.createStatement();

            // SQL statement to create a table (change as per your requirements)
            String createTableSQL = "CREATE TABLE IF NOT EXISTS sample_table (" +
                    "id INT PRIMARY KEY," +
                    "name VARCHAR(255))";

            // Execute the SQL statement to create the table
            statement.executeUpdate(createTableSQL);

            // Check if the table exists
            assertTrue(tableExists(connection, "sample_table"), "Table should exist after creation");

            // Close the resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            // Print any exception that occurs during table creation
            e.printStackTrace();
        }
    }

    private boolean tableExists(Connection connection, String tableName) throws SQLException {
        // Check if the table exists in the database
        return connection.getMetaData().getTables(null, null, tableName, null).next();
    }
}
