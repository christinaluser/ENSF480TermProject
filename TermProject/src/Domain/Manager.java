package Domain;

import java.util.ArrayList;

//Does this implement Observer??
public class Manager extends User{

    public Manager(Name name, Address address, String email, String username, String password, int accessID) {
        super(name, address, email, username, password, accessID);
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