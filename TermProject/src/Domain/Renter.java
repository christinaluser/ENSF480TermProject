package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Renter extends User {
    private ArrayList<Property> matchedProperties;

    public Renter(Name name, String email, String username, String password, int accessID) {
        super(name, email, username, password, accessID);
    }

    @Override
    public void update(ArrayList<Property> p) {
        matchedProperties = p;
        System.out.println("Notification to matched Renter properties: Changed");
    }

    @Override
    public void communicate(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        this.socketOut = socketOut;
        this.socketIn = socketIn;
        this.database = database;
        String input = "";
        try {
            while(true) {
                if(input.equals("DISPLAY")) {
                    refreshProperties();
                    String allProperties = propertiesToString();
                    String[] response = allProperties.split(";");
                    for(String p : response) {
                        sendString(p);
//                        update(matchedProperties);
                    }
                    sendString("END");

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendString(String s) {
        socketOut.println(s);
        socketOut.flush();
    }

    public String propertiesToString() {
        String str = "";
        for (Property p: properties) {
            str += p.toString();
        }
        return str;
    }

    public void sendListingEmail() {
        
    }

}