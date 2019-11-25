package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Landlord extends User {
    public ArrayList<Property> ownedProperties;
    public ArrayList<PropertyListing> listings;

    public Landlord(Name name, Address address, String email, String username, String password, int accessID) {
        super(name, address, email, username, password, accessID);
    }

    public Landlord(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        super(socketIn, socketOut, database);
    }


    @Override
    public void update(ArrayList<Property> p)
    {
        // do nothing, Landlord doesn't need to have their properties updated
    }

    @Override
    public void communicate() {
        String input = "";
        try {
            while (true) {
                input = socketIn.readLine();
                if (input.startsWith("REGISTERPROPERTY/")) {
                    refreshProperties();
                    String criteria = input.replace("REGISTER/", "");
                    this.addProperty(criteria);
                    sendString("Done");
                } else if (input.equals("DISPLAY")) {
                    refreshProperties();
                    String allProperties = propertiesToString();
                    String[] response = allProperties.split(";");
                    for (String p : response) {
                        sendString(p);
                    }
                    sendString("END");

                } else if (input.startsWith("SEARCHADDRESS/")) {
                    refreshProperties();
                    String address = input.replace("SEARCHADDRESS/", "");
                    String response = searchProperties(address);
                    sendString(response);
                } else if (input.startsWith("EDITSTATE/")) {
                    refreshProperties();
                    sendString(editState(input));
                } else if (input.startsWith("PAY/")) {
                    refreshProperties();
                    sendString(payFees(input));
                }
                //READ STRINGS HERE
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String payFees(String input) {
        String[] split = input.split("/");
        for(Property p : ownedProperties) {
            if(p.getPropertyId() == Integer.parseInt(split[1])) {
                p.setState("active");
                return "Done";
            }
        }
        return null;
    }

    private String editState(String input) {
        String[] split = input.split("/");
        for (Property p : ownedProperties) {
            if (p.getPropertyId() == Integer.parseInt(split[1])) {
                if(!p.getListingState().equals("suspended")) {
                    p.setState(split[2]);
                    refreshProperties();
                    return "Done";
                }
            }
        }
        return null;
    }

    private String propertiesToString() {
        String str = "";
        for (Property p : ownedProperties) {
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
        for (Property p : ownedProperties) {
            if (address.equals(p.getAddressParts())) {
                str += p.toString();
                str += ";";
            }
        }
        if (str.equals("")) {
            return null;
        } else {
            return str;
        }
    }

    public void removeProperty(Property p) {

    }


    public void addProperty(String s) {
        String[] criteria = s.split("/");
        //construct the property
        Property p = new Property(criteria[0], Integer.parseInt(criteria[1]), criteria[2], criteria[3], criteria[4],
                Integer.parseInt(criteria[5]), Integer.parseInt(criteria[6]), Boolean.parseBoolean(criteria[7]), Double.parseDouble(criteria[8]));
        database.addProperty(p);
        ownedProperties.add(p);
        refreshProperties();
    }
}
