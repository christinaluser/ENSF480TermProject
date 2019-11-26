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

        if (info[1].equals("Manager")){
            User newUser = new Manager(new Name(info[2], info[3]), info[4] , info[5], info[6], 1);
            database.addUser(newUser);
            sendString("success");
        } else if (info[1].equals("Landlord")){
            User newUser = new Landlord(new Name(info[2], info[3]), info[4] , info[5], info[6], 2);
            database.addUser(newUser);
            sendString("success");
        } else if (info[1].equals("Renter")){
            User newUser = new Renter(new Name(info[2], info[3]), info[4] , info[5], info[6], 1);
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
                System.out.println("Read line");
                if(input.equals("DISPLAY")) {
                    refreshProperties();
                    String allProperties = propertiesToString();
                    String[] response = allProperties.split(";");
                    for(String p : response) {
                        sendString(p);
                    }
                    sendString("END");

                } else if(input.startsWith("SEARCH/")) {
                    refreshProperties();
                    String criteria = searchCriteria(input);
                    String[] response = criteria.split(";");
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
        SearchCriteria sc = new SearchCriteria(criteria[1], Integer.parseInt(criteria[2]), Integer.parseInt(criteria[3]),
                Boolean.parseBoolean(criteria[4]), criteria[5], Double.parseDouble(criteria[6]));
        for(Property p : properties) {
            if (p.getType().equals(sc.getType()) && p.getNoBedrooms() == sc.getNoBedrooms() && p.getNoBathrooms() == sc.getNoBathrooms()
                    && p.getIsFurnished() == sc.getIsFurnished() && p.getCityQuadrant().equals(sc.getCityQuadrant()) && p.getRent() <= sc.getPriceRange()) {
                str += p.toString();
                str += ";";
            }
        }
//        for(Property p : properties) {
//            if(criteria[0].equals(p.getType()) && criteria[1].equals(p.getNoBedrooms()) && criteria[2].equals(p.getNoBathrooms()) && criteria[3].equals(p.getIsFurnished())
//                    && criteria[4].equals(p.getCityQuadrant())) {
//                if(Double.parseDouble(criteria[5]) <= p.getRent()) {
//                    str += p.toString();
//                    str += ";";
//                }
//            }
//        }
        return str;
    }

    public String propertiesToString() {
        String str = "";
        for (Property p: properties) {
            System.out.println("in loop");
            System.out.println(p.toString());
            str += p.toString();
            str += ";";
        }
        System.out.println(str);
        return str;
    }

    public void refreshProperties() {
        properties = database.loadProperties();
    }

    public void setDbController(DatabaseController database) {
        this.database = database;
    }

}