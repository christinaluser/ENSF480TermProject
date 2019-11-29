package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Landlord extends User {
    public ArrayList<Property> ownedProperties;
    public ArrayList<PropertyListing> listings;

    public Landlord(Name name, String email, String username, String password, int accessID) {
        super(name, email, username, password, accessID);
        listings = new ArrayList<>();
    }

    private void fillOwnedProperties() {
        ownedProperties = new ArrayList<>();
        ArrayList<Property> allProperties = database.loadProperties();
        for(Property p : allProperties) {
            //todo
        }
    }

    @Override
    public void update(ArrayList<Property> p)
    {
        // do nothing, Landlord doesn't need to have their properties updated
    }

    @Override
    public void communicate(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        this.socketOut = socketOut;
        this.socketIn = socketIn;
        this.database = database;
        fillOwnedProperties();
        String input = "";
        try {
            while (true) {
                input = socketIn.readLine();
                System.out.println(input);
                if (input.startsWith("REGISTERPROPERTY/")) {
                    refreshProperties();
                    this.addProperty(input);
                    sendString("Done");
                } else if (input.equals("DISPLAY")) {
                    refreshProperties();
                    ArrayList<String> response = propertiesToString();
                    for(String p : response) {
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

                } else if(input.equals("LOGOUT")) {
                    sendString("Done");
                    return;
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

    public ArrayList<String> propertiesToString() {
        ArrayList<String> s = new ArrayList<>();
        for (Property p: properties) {
            s.add(p.toString());
        }
        return s;
    }

    public void sendString(String s) {
        socketOut.println(s);
        socketOut.flush();
    }

    public String searchProperties(String address) {
        System.out.println(address);
        String str = "";
        if(!ownedProperties.isEmpty()) {
            for (Property p : ownedProperties) {
                if (address.equals(p.getAddressParts())) {
                    str = p.toString();
                    break;
                }
            }
        }
        return str;
    }

    public void removeProperty(Property p) {

    }


    public void addProperty(String s) {
        String[] criteria = s.split("/");
        //construct the property
        String type = criteria[1];
        int houseNumber = Integer.parseInt(criteria[2]);
        String street = criteria[3];
        String postalCode = criteria[4];
        String quadrant = criteria[5];
        int noBedrooms = Integer.parseInt(criteria[6]);
        int noBathrooms = Integer.parseInt(criteria[7]);
        boolean furnished = (criteria[7].equals("furnished"));
        double rent = Double.parseDouble(criteria[8]);
        Property p = new Property(type, houseNumber, street, postalCode, quadrant, noBedrooms, noBathrooms, furnished, rent);
//        Property p = new Property(criteria[1], Integer.parseInt(criteria[2]), criteria[3], criteria[4], criteria[5],
//                Integer.parseInt(criteria[6]), Integer.parseInt(criteria[7]), furnished, Double.parseDouble(criteria[8]));
        database.addProperty(p);
        ownedProperties.add(p);
        refreshProperties();
    }
}
