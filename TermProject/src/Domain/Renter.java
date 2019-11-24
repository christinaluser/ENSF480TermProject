package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

//Does this implement Observer??
public class Renter extends User implements Observer{
    private ArrayList<Property> matchedProperties;

    public Renter(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        super(socketIn, socketOut, database);
    }

    @Override
    public void communicate() {
        try {
            while(true) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendListingEmail() {
        
    }

    @Override
    public void update(ArrayList<Property> p) {
        matchedProperties = p;
        System.out.println("Notification to Renter properties: Changed");
    }

}