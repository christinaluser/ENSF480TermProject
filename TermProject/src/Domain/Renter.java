package Domain;

import java.util.ArrayList;

//Does this implement Observer??
public class Renter extends User implements Observer{
    private ArrayList<Property> matchedProperties;

    public void sendListingEmail() {
        
    }

    @Override
    public void update(ArrayList<Property> p) {
        matchedProperties = p;
        System.out.println("Notification to Renter properties: Changed");
    }

}