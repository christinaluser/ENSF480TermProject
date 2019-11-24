package Domain;

import java.util.ArrayList;

public abstract class User {        // why arent we implementing observer?
    public Name name;
    public Address address;
    public String email;
    protected String username;
    protected String password;
    protected int accessID;
    ArrayList<Property> properties;

    public User (Name name, Address address, String email, String username, String password, int accessID)
    {
        this.name = name;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
        this.accessID = accessID;
    }

}

