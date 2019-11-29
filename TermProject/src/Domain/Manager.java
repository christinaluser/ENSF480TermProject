package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Manager extends User{

    public Manager(Name name, String email, String username, String password, int accessID) {
        super(name, email, username, password, accessID);
    }
    
    public Manager(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        super(socketIn, socketOut, database);
    }

    @Override
    public void update(ArrayList<Property> p)
    {
        properties = p;
        System.out.println("Notification to all properties: Changed");
    }

    @Override
    public void communicate(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        this.socketIn = socketIn;
        this.socketOut = socketOut;
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

                } else if(input.startsWith("SEARCHADDRESS/")) {
                   refreshProperties();
                   String address = input.replace("SEARCHADDRESS/", "");
                   String response = searchProperties(address);
                   sendString(response);

                } else if(input.startsWith("EDITFEE/")) {
                    refreshProperties();
                    String fee = input.replace("EDITFEE/", "");
                    editFee(fee);
                    sendString("Done");
               } else if (input.startsWith("EDIT/")) {
                   refreshProperties();
                   sendString(editState(input));
               } else if (input.startsWith("PAY/")) {
                   refreshProperties();
                   sendString(payFees(input));

               } else if (input.equals("LOGOUT")) {
                   sendString("done");
                   return;
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
                database.updateState(split[2], p.getPropertyId());
                p.setState(split[2]);
                refreshProperties();
                return "Done";
            }
        }
        return "null";
    }

    private void editFee(String amt) {
        for(Property p : properties) {
            p.setFee(new Fee(Double.parseDouble(amt)));
        }
    }

    public ArrayList<String> propertiesToString() {
        ArrayList<String> s = new ArrayList<>();
        for (Property p: properties) {
            s.add(p.toStringManager());
        }
        return s;
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