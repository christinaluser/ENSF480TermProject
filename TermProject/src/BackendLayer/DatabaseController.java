package BackendLayer;

import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {   // Save as "JdbcSelectTest.java"

    public DatabaseController()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Unable to find and load driver");
        }

        try (
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/rpmdatabase?serverTimezone=GMT",
                        "root", "password");   // For MySQL only
                // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
        ) {

        } catch(SQLException ex) {
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
    }

    void searchProperty()
    {

    }

    void addPayment()
    {

    }

    void addProperty()
    {

    }

    void updateProperty()
    {

    }

    void getProperty()
    {

    }

    void updateFee()
    {

    }

    void validateLogin()
    {

    }

}
