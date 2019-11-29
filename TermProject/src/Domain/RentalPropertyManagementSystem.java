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
                    sendString("done");
                    communicateRegularUser();
                } else {
                    ArrayList<User> allUsers = database.loadUsers();
                    for (User u: allUsers) {
                        if (u.username.equals(info[2]) && u.password.equals(info[3])) {
                            user = u;
                            break;
                        }
                    }

                    System.out.println(user.username);
                    user.communicate(socketIn, socketOut, database);

//                    System.out.println(info[1]);
//                    if(info[1].equals("Renter")) {
//                        user.communicate(socketIn, socketOut, database);
//                        //communicateRenter(user);
//                    } else if(info[1].equals("Manager")) {
//                        communicateManager();
//                    } else if(info[1].equals("Landlord")) {
//                        communicateLandlord();
//                    }
                }

                userFound = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO validate function doesnt work
    private boolean verifyLogin(String s) {
        String[] info = s.split("/");
        int access = -1;
        if (info[1].equals("Manager")) {
            access = 1;
        } else if (info[1].equals("Landlord")){
            access = 2;
        } else if (info[1].equals("Renter")) {
            access = 3;
        }
        ArrayList<User> allUsers = database.loadUsers();
        for (User u: allUsers) {
            if (u.username.equals(info[2]) && u.password.equals(info[3]) && u.accessID == access) {
                sendString(info[1]);
                return true;
            }
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

        if (info[1].equals("Landlord")){
            User newUser = new Landlord(new Name(info[2], info[3]), info[4] , info[5], info[6], 2);
            database.addUser(newUser);
            sendString("success");
        } else if (info[1].equals("Renter")){
            User newUser = new Renter(new Name(info[2], info[3]), info[4] , info[5], info[6], 3);
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
//                System.out.println("Read line");
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
                        System.out.println(p);
                        sendString(p);
                    }
                    sendString("END");
                } else if(input.equals("LOGOUT")) {
                    sendString("done");
                    return;
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
    public ArrayList<String> searchCriteria(String s) {
        String[] criteria = s.split("/");
        SearchCriteria sc = new SearchCriteria(criteria[1], Integer.parseInt(criteria[2]), Integer.parseInt(criteria[3]),
                Boolean.parseBoolean(criteria[4]), criteria[5], Double.parseDouble(criteria[6]));
        ArrayList<String> allProperties= new ArrayList<String>();
        for(Property p : properties) {
            if (p.getType().equals(sc.getType().toLowerCase()) && p.getNoBedrooms() == sc.getNoBedrooms() && p.getNoBathrooms() == sc.getNoBathrooms()
                    && p.getIsFurnished() == sc.getIsFurnished() && p.getCityQuadrant().equals(sc.getCityQuadrant()) && p.getRent() <= sc.getPriceRange()) {
                allProperties.add(p.toString());
            }
        }
        return allProperties;
    }

    public ArrayList<String> propertiesToString() {
        ArrayList<String> s = new ArrayList<String>();
        for (Property p: properties) {
            s.add(p.toString());
//            System.out.println(p.toString());
        }

//        for(int i = 0; i < s.size(); i++){
//            System.out.println(s.get(i));
//        }

        return s;
    }

    public void refreshProperties() {
        properties = database.loadProperties();
    }

    public void setDbController(DatabaseController database) {
        this.database = database;
    }

}