package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class User {        // why arent we implementing observer?
    public Name name;
    public Address address;
    public String email;
    protected String username;
    protected String password;
    protected int accessID;
    ArrayList<Property> properties;

    public BufferedReader socketIn;
    PrintWriter socketOut;
    DatabaseController database;

    public User(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        this.socketIn = socketIn;
        this.socketOut = socketOut;
        this.database = database;
    }

    public abstract void communicate();

    public User (Name name, Address address, String email, String username, String password, int accessID)
    {

    }

}

