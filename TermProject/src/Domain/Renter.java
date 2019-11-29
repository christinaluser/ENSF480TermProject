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
                input = socketIn.readLine();
                System.out.println(input);
                if(input.equals("DISPLAY")) {
                    refreshProperties();
                    ArrayList<String> response = propertiesToString();
                    for(String p : response) {
                        sendString(p);
                    }
                    sendString("END");
                } else if(input.startsWith("SEARCH/")) {
                    refreshProperties();
                    ArrayList<String> criteria = searchCriteria(input);
                    for(String p : criteria) {
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

    public ArrayList<String> propertiesToString() {
        ArrayList<String> s = new ArrayList<String>();
        for (Property p: properties) {
            s.add(p.toString());
        }
        return s;
    }

    public ArrayList<String> searchCriteria(String s) {
        String[] criteria = s.split("/");
        SearchCriteria sc = new SearchCriteria(criteria[1], Integer.parseInt(criteria[2]), Integer.parseInt(criteria[3]),
                Boolean.parseBoolean(criteria[4]), criteria[5], Double.parseDouble(criteria[6]));
        System.out.println(sc.getType() + " " + sc.getNoBedrooms() + " " + sc.getNoBathrooms() + " " + sc.getIsFurnished() + " " +
                sc.getCityQuadrant() + " " + sc.getPriceRange());
        ArrayList<String> allProperties= new ArrayList<String>();
        for(Property p : properties) {
            System.out.println(p.getType() + " " + p.getNoBedrooms() + " " + p.getNoBathrooms() + " " + p.getIsFurnished() + " " +
                    p.getCityQuadrant() + " " + p.getRent());
            if (p.getType().equals(sc.getType().toLowerCase()) && p.getNoBedrooms() == sc.getNoBedrooms() && p.getNoBathrooms() == sc.getNoBathrooms()
                    && p.getIsFurnished() == sc.getIsFurnished() && p.getCityQuadrant().equals(sc.getCityQuadrant()) && p.getRent() <= sc.getPriceRange()) {
                allProperties.add(p.toString());
            }
        }
        return allProperties;
    }

    public void sendListingEmail() {
        
    }

}