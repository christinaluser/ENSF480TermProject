package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class User implements Observer {
    public Name name;
    public String email;
    public String username;
    public String password;
    public int accessID;
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

    public void refreshProperties() {
        properties = database.loadProperties();
    }

    public User(Name name, String email, String username, String password, int accessID) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.accessID = accessID;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAccessId()
    {
        return accessID;
    }

}

