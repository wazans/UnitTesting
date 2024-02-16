import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.example.DatabaseConnectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class YourDbUnitTest {

    private IDatabaseConnection connection;

    @Before
    public void setUp() throws Exception {
        // Create a database connection
        connection = new DatabaseConnection(DatabaseConnectionUtil.getConnection());
        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());

        // Create the "UT1" table
        try (Connection jdbcConnection = connection.getConnection();
             Statement createStatement = jdbcConnection.createStatement()) {
            //Execute the query to retrieve information about tables
            try (ResultSet resultSet = createStatement.executeQuery("SELECT * FROM  INFORMATION_SCHEMA.TABLES")) {
                System.out.println("Table information from INFORMATION_SCHEMA.TABLES:");
                while (resultSet.next()) {
                    String tableName = resultSet.getString("TABLE_NAME");
                    String tableType = resultSet.getString("TABLE_TYPE");
                    System.out.println("Table Name: " + tableName + ", Table Type: " + tableType);
                    // You can print other information as needed
                }
            } catch (Exception e) {
                e.printStackTrace(); // Log any exception during data retrieval
            }
        }

        // You can insert initial data into the "UT1" table here if needed
    }


    @Test
    public void yourTest() {
        // Your test logic here
        // DbUnit will manage the database state based on the initial dataset
    }

    @After
    public void tearDown() throws Exception {
        // Close the database connection after the test
        if (connection != null) {
            connection.close();
        }
    }
}
