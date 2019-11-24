package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Manager extends User{

    public Manager(Name name, Address address, String email, String username, String password, int accessID) {
        super(name, address, email, username, password, accessID);
    }
    
    public Manager(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        super(socketIn, socketOut, database);
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
                    refreshProperties();
                    String fee = input.replace("EDITFEE/", "");
                    editFee(fee);
                    sendString("Done");
               } else if (input.startsWith("EDITSTATE/")) {
                   refreshProperties();
                   sendString(editState(input));
               } else if (input.startsWith("PAY/")) {
                   refreshProperties();
                   sendString(payFees(input));
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String payFees(String input) {
        String[] split = input.split("/");
        for(Property p : properties) {
            if(p.getPropertyId() == Integer.parseInt(split[1])) {
                p.setState("active");
                return "Done";
            }
        }
        return null;
    }

    private String editState(String input) {
        String[] split = input.split("/");
        for (Property p : properties) {
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

    private void editFee(String amt) {
        for(Property p : properties) {
            p.setFee(new Fee(Double.parseDouble(amt)));
        }
        updateDatabase();
    }

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
            if(address.equals(p.getAddressParts())) { //todo SHOULD BE ADDRESS BUT THAT DOESNT EXIST
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

    public void requestReport() {

    }

    public void changeState(PropertyListing pl, Property p, String s) {
        
    }

}