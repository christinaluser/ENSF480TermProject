package BackendLayer;

import Domain.*;

import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseController {   // Save as "JdbcSelectTest.java"

    private Connection conn;
    private Statement stmt;

    private ArrayList<Property> properties = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public DatabaseController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Unable to find and load driver");
        }

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rpmdatabase?serverTimezone=GMT",
                    "root", "password");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Property> searchProperty(SearchCriteria sc) {
        ArrayList<Property> searchProperties = loadProperties();
        ArrayList<Property> matchedProperties = new ArrayList<>();

        for (Property psearch : searchProperties) {
            if (psearch.getType().equals(sc.getType().toLowerCase()) && psearch.getNoBedrooms() == sc.getNoBedrooms() && psearch.getNoBathrooms() == sc.getNoBathrooms()
                    && psearch.getIsFurnished() == sc.getIsFurnished() && psearch.getCityQuadrant().equals(sc.getCityQuadrant()) && psearch.getRent() <= sc.getPriceRange())
                matchedProperties.add(psearch);
        }

        if(matchedProperties != null)
            return matchedProperties;
        else
            return null;
    }

    public Property searchPropertyLM(SearchCriteria sc) {
        ArrayList<Property> searchProperties = loadProperties();

        for (Property psearch : searchProperties) {
            if (psearch.getType().equals(sc.getType()) && psearch.getNoBedrooms() == sc.getNoBedrooms() && psearch.getNoBathrooms() == sc.getNoBathrooms()
                    && psearch.getIsFurnished() == sc.getIsFurnished() && psearch.getCityQuadrant().equals(sc.getCityQuadrant()) && psearch.getRent() <= sc.getPriceRange())
                return psearch;
        }
        return null;
    }

    public ArrayList<Property> loadProperties() {
        String strSelect = "SELECT * FROM properties";
        ArrayList<Property> properties = new ArrayList<>();
        try {
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                Property p = new Property(rset.getInt("propertyID"), rset.getString("type"), new Address(rset.getInt("propertyNumber"), rset.getString("streetName"), rset.getString("postalCode")), rset.getInt("noBedrooms"), rset.getInt("noBathrooms"),
                        rset.getBoolean("isFurnished"), rset.getString("cityQuadrant"), rset.getString("listingState"), rset.getDouble("rent"), rset.getDate("datePosted"));
                properties.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public User validateLogin(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password))
                return users.get(i);
        }
        return null;
    }

    public ArrayList<User> loadUsers() {
        String strSelect = "SELECT * FROM users";

        try {
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                int accessLevel = rset.getInt("accessID");
                if (accessLevel == 1) {
                    Manager m = new Manager(new Name(rset.getString("firstName"), rset.getString("lastName")),
                            rset.getString("email"), rset.getString("username"), rset.getString("password"), rset.getInt("accessID"));
                    users.add(m);
                } else if (accessLevel == 2) {
                    Landlord l = new Landlord(new Name(rset.getString("firstName"), rset.getString("lastName")),
                            rset.getString("email"), rset.getString("username"), rset.getString("password"), rset.getInt("accessID"));
                    users.add(l);
                } else if (accessLevel == 3) {
                    Renter r = new Renter(new Name(rset.getString("firstName"), rset.getString("lastName")),
                            rset.getString("email"), rset.getString("username"), rset.getString("password"), rset.getInt("accessID"));
                    users.add(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addProperty(Property p, String email) {
        String strInsert = "INSERT INTO properties VALUES (" + p.getPropertyId() + ", '" + p.getType() + "', "
                + p.getAddress().getPropertyNumber() + ", '" + p.getAddress().getStreetName() + "', '"
                + p.getAddress().getPostalCode() + "', " +p.getNoBedrooms() + ", " + p.getNoBathrooms() + ", "
                + p.getIsFurnished() + ", '" + p.getCityQuadrant() + "', '" + p.getListingState() + "', " + p.getRent()
                + ", " + p.getDatePosted() + ", '" + email + "')";
        try {
            stmt.executeUpdate(strInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User u) {
        String strInsert = "INSERT INTO users VALUES ('" + u.getUsername() + "', '" + u.getPassword() + "', " + u.getAccessId() + ", '"
                + u.getName().getFirstName() + "', '" + u.getName().getLastName() + "' , '" + u.getEmail() + "')";
        System.out.println("statement is" + strInsert);
        try {
            stmt.executeUpdate(strInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*

    void updateProperty()
    {

    }

    void getProperty()
    {

    }

    void updateFee()
    {

    }

}*/
