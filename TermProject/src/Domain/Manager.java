package Domain;

import BackendLayer.DatabaseController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

//Does this implement Observer??
public class Manager extends User{

    public Manager(BufferedReader socketIn, PrintWriter socketOut, DatabaseController database) {
        super(socketIn, socketOut, database);
    }

    @Override
    public void communicate() {
        try {
            while(true) {
                //READ STRINGS HERE
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accessInformation() {

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