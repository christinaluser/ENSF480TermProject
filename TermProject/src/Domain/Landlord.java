package Domain;

import java.util.ArrayList;

public class Landlord extends User{
    public ArrayList<Property> ownedProperties;
    public ArrayList<PropertyListing> listings;

    public Landlord(Name name, Address address, String email, String username, String password, int accessID) {
        super(name, address, email, username, password, accessID);
    }


    public void removeProperty(Property p) {

    }

    public void addProperty(Property p) {

    }

    public void changeListingState(Property p, int state) {

    }

//    @Override
//    public void update(ArrayList<Property> p) {
//        properties = p;
//        System.out.println("Notification to Landlord properties: Changed");
//    }
}