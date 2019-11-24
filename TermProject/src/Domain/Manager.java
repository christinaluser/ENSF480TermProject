package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

//Does this implement Observer??
public class Manager extends User{
    ArrayList<Property> properties;

    public Manager(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        super(socketIn, socketOut, database);
        properties = new ArrayList<Property>();
    }

    @Override
    public void communicate() {
        String input = "";
        try {
            while(true) {
                input = socketIn.readLine();
               if(input.equals("DISPLAY")) {
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

                } else if(input.startsWith("EDITFEE/")) {
                    String fee = input.replace("EDITFEE/", "");
                    editFee(fee);
                    sendString("Done");
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editFee(String amt) {
        for(Property p : properties) {
            p.setFee(new Fee(Double.parseDouble(amt)));
        }
        updateDatabase();
    }

    //todo
    private void updateDatabase() {
    }

    private String propertiesToString() {
        String str = "";
        for (Property p: properties) {
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
        for(Property p : properties) {
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

    public void accessInformation() {

    }

    public void refreshProperties() {
        //Refresh arraylist properties to match database
    }

    public void requestReport() {

    }

    public void changeState(PropertyListing pl, Property p, String s) {
        
    }

//    i dont think manager should be an observer ?
//    @Override
//    public void update(ArrayList<Property> p) {
//        properties = p;
//        System.out.println("Notification to Manager properties: Changed");
//    }

}