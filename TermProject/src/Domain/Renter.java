package Domain;

import java.util.ArrayList;

//Does this implement Observer??
public class Renter extends User {

    public void sendListingEmail() {
        
    }

    @Override
    public void update(ArrayList<Property> p) {
        properties = p;
        System.out.println("Notification to Manager properties: Changed");
    }

}