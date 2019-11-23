package Domain;

import BackendLayer.DatabaseController;
import Domain.Property;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class RentalPropertyManagementSystem implements Runnable{
    private BufferedReader socketIn;
    private PrintWriter socketOut;
    private Socket socket;
    private User user;
    private DatabaseController database;
    private ArrayList<Property> properties;


    public RentalPropertyManagementSystem(Socket s) {
        properties = new ArrayList<Property>();
        socket = s;
        try {
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        this.communicate();
    }

    public void communicate() {
        boolean userFound = false;
        while(true) {
            String input = "";
            try {
                while (!userFound) {
                    input = socketIn.readLine();
                    System.out.println(input);
                    if (input.startsWith("USER/")) {
                        userFound = true;
                    }
                }

                switch (input) {
                    case "USER/REGULAR_USER":
                        communicateRegularUser();
                        break;
                    case "USER/LANDLORD":
                        communicateLandlord();
                        break;
                    case "USER/RENTER":
                        communicateRenter();
                        break;
                    case "USER/MANAGER":
                        communicateManager();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + input);
                }
                userFound = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void communicateRegularUser() {
        RegularUser regUser = new RegularUser();
        String input = "";
        try {
            while(true) {
                input = socketIn.readLine();
                if(input.equals("DISPLAY")) {
                    sendString(propertiesToString());
                } else if(input.startsWith("SEARCH/")) {
                    refreshProperties();
                    String criteria = input.replace("SEARCH/", "");
                    sendString(searchCriteria(criteria));
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

    //FOR RENTER
    public String searchCriteria(String s) {
        String str = "";
        String[] criteria = s.split("/");
        for(Property p : properties) {
            if(criteria[0].equals(p.getType()) && criteria[1].equals(p.getNoBedrooms()) && criteria[2].equals(p.getNoBathrooms()) && criteria[3].equals(p.getIsFurnished())
                    && criteria[4].equals(p.getCityQuadrant())) {
                if(Double.parseDouble(criteria[5]) <= p.getRent()) {
                    str += p.toString();
                }
            }
        }
        if(str.equals("")) {
            return "No Properties with that criteria";
        } else {
            return str;
        }
    }

    public String propertiesToString() {
        String str = "";
        for (Property p: properties) {
            str += p.toString();
        }
        return str;
    }

    public void refreshProperties() {
        //Refresh arraylist properties to match database
    }

    private void communicateLandlord() {
        user = new Landlord();
        try {
            while(true) {
                //READ STRINGS HERE
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void communicateRenter() {
        user = new Renter();
        try {
            while(true) {
                //READ STRINGS HERE
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void communicateManager() {
        user = new Manager();
        try {
            while(true) {
                //READ STRINGS HERE
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDbController(DatabaseController database) {
        this.database = database;
    }

}