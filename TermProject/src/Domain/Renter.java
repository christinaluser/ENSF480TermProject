package Domain;

import java.util.ArrayList;

//Does this implement Observer??
public class Renter extends User implements Observer{
    private ArrayList<Property> matchedProperties;

    public Renter(Name name, Address address, String email, String username, String password, int accessID) {
        super(name, address, email, username, password, accessID);
    }

    public void sendListingEmail() {
        
    }

    @Override
    public void update(ArrayList<Property> p) {
        matchedProperties = p;
        System.out.println("Notification to Renter properties: Changed");
    }

}