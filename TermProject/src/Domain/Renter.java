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
        String input = "";
        try {
            while(true) {
                if(input.equals("DISPLAY")) {
                    String allProperties = propertiesToString();
                    String[] response = allProperties.split(";");
                    for(String p : response) {
                        sendString(p);
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

    @Override
    public void update(ArrayList<Property> p) {
        matchedProperties = p;
        System.out.println("Notification to Renter properties: Changed");
    }

}