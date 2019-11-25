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
                    if(input.startsWith("SIGNUP/")) {
                        signUpNewUser(input);
                    }
                    if (input.startsWith("LOGIN/")) {
                        userFound = verifyLogin(input);
                    }
                    if(input.equals("CONTINUE")) {
                        userFound = true;
                    }
                }

                input.replace("LOGIN/", "");
                String[] info = input.split("/");

                if(info[0].equals("CONTINUE")) {
                    communicateRegularUser();
                } else {
                    user = database.validateLogin(info[2],info[3]); // create new user
                    user.communicate(socketIn, socketOut, database);
                }

                userFound = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verifyLogin(String s) {
        String[] info = s.split("/");
        if(database.validateLogin(info[2],info[3]) != null) {
            sendString(info[1]);
            return true;
        }
        sendString("null");
        return false;

    }

    private void signUpNewUser(String s) {
        String[] info = s.split("/");
        ArrayList<User> allUsers = database.loadUsers();
        for (User u: allUsers) {
            System.out.println(u.email);
            System.out.println(info[6]);
            if (u.email.equals(info[4])){
                sendString("null");
                return;
            }
        }

        if (info[1].equals("Manager")){
            User newUser = new Manager(new Name(info[2], info[3]), info[4] , info[5], info[6], 1);
            database.addUser(newUser);
            sendString("success");
        } else if (info[1].equals("Landlord")){
            User newUser = new Manager(new Name(info[2], info[3]), info[4] , info[5], info[6], 2);
            database.addUser(newUser);
            sendString("success");
        } else if (info[1].equals("Renter")){
            User newUser = new Manager(new Name(info[2], info[3]), info[4] , info[5], info[6], 1);
            database.addUser(newUser);
            sendString("success");
        } else {
            sendString("null");
        }


    }

    private void communicateRegularUser() {
        String input = "";
        try {
            while(true) {
                input = socketIn.readLine();
                if(input.equals("DISPLAY")) {
                    String allProperties = propertiesToString();
                    String[] response = allProperties.split(";");
                    for(String p : response) {
                        sendString(p);
                    }
                    sendString("END");

                } else if(input.startsWith("SEARCH/")) {
                    refreshProperties();
                    String criteria = input.replace("SEARCH/", "");
                    String[] response = searchCriteria(criteria).split(";");
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

    //FOR UNREGISTERED RENTER
    public String searchCriteria(String s) {
        String str = "";
        String[] criteria = s.split("/");
        for(Property p : properties) {
            if(criteria[0].equals(p.getType()) && criteria[1].equals(p.getNoBedrooms()) && criteria[2].equals(p.getNoBathrooms()) && criteria[3].equals(p.getIsFurnished())
                    && criteria[4].equals(p.getCityQuadrant())) {
                if(Double.parseDouble(criteria[5]) <= p.getRent()) {
                    str += p.toString();
                    str += ";";
                }
            }
        }
        if(str.equals("")) {
            return null;
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

    public void setDbController(DatabaseController database) {
        this.database = database;
    }

}