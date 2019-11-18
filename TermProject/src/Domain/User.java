package Domain;

import java.util.ArrayList;

public abstract class User implements Observer{
    public Name name;
    public Address address;
    public String email;
    protected String username;
    protected String password;
    ArrayList<Property> properties;

}