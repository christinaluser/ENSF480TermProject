package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Landlord extends User{
    public ArrayList<Property> ownedProperties;
    public ArrayList<PropertyListing> listings;

    public Landlord(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        super(socketIn, socketOut, database);
    }

    @Override
    public void communicate() {
        String input = "";
        try {
            while(true) {
                input = socketIn.readLine();
                if(input.startsWith("REGISTER/")) { //IT CURRENTLY DOES NOT START WITH REGISTER ADD THAT LATER
                    String criteria = input.replace("REGISTER/", "");
                    this.addProperty(criteria);
                    sendString("Done");
                } else if(input.equals("DISPLAY")) {
                    refreshProperties();
                    String allProperties = propertiesToString();
                    String[] response = allProperties.split(";");
                    for(String p : response) {
                        sendString(p);
                    }
                    sendString("END");

                } else if(input.startsWith("SEARCHADDRESS/")) {
                    refreshProperties();
                    String address = input.replace("SEARCHADDRESS/", "");
                    String[] response = searchProperties(address).split(";");
                    for(String p : response) {
                        sendString(p);
                    }
                    sendString("END");
                }
                //READ STRINGS HERE
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String propertiesToString() {
        String str = "";
        for (Property p: ownedProperties) {
            str += p.toString();
        }
        return str;
    }

    public void sendString(String s) {
        socketOut.println(s);
        socketOut.flush();
    }

    public String searchProperties(String address) {
        String str = "";
        for(Property p : ownedProperties) {
            if(address.equals(p.getCityQuadrant())) { //todo SHOULD BE ADDRESS BUT THAT DOESNT EXIST
                str += p.toString();
                str += ";";
            }
        }
        if(str.equals("")) {
            return null;
        } else {
            return str;
        }
    }

    //todo
    private void updateDatabase() {
    }

    public void removeProperty(Property p) {

    }

    public void refreshProperties() {
        //Refresh arraylist properties to match database
    }

    public void addProperty(String s) {
        String[] criteria = s.split("/");
        //construct the property
//        Property p = new Property(Integer.parseInt(criteria[0]), criteria[1], Integer.parseInt(criteria[2]), Integer.parseInt(criteria[3]), Boolean.parseBoolean(criteria[4]),
//                    criteria[5], criteria[6], Double.parseDouble(criteria[7]));
        //database.addProperty(p);
        //ownedProperties.add(p);
        updateDatabase();
    }

    public void changeListingState(Property p, int state) {

    }

//    @Override
//    public void update(ArrayList<Property> p) {
//        properties = p;
//        System.out.println("Notification to Landlord properties: Changed");
//    }
}