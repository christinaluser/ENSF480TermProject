package BackendLayer;

import Domain.Property;

import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseController {   // Save as "JdbcSelectTest.java"

    private Connection conn;
    private Statement stmt;

    public DatabaseController()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Unable to find and load driver");
        }

        try {
            // Step 1: Allocate a database 'Connection' object
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rpmdatabase?serverTimezone=GMT",
                    "root", "password");   // For MySQL only
            // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

            stmt = conn.createStatement();

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    void searchProperty(Property p)
    {
        ArrayList <Property> properties = loadProperties();
        for(Property psearch: properties)
        {

        }

    }

    public ArrayList<Property> loadProperties()
    {
        ArrayList<Property> properties = new ArrayList<>();
        String strSelect = "SELECT * FROM properties";

        try {
            ResultSet rset = stmt.executeQuery(strSelect);
            while(rset.next())
            {
                Property p = new Property(rset.getString("type"), rset.getInt("noBedrooms"), rset.getInt("noBathrooms"),
                        rset.getBoolean("isFurnished"), rset.getString("cityQuadrant"), rset.getString("listingState"), rset.getDouble("rent"));
                properties.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /*void addPayment()
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

    }*/

}
