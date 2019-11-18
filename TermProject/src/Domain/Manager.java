package Domain;

import java.util.ArrayList;

//Does this implement Observer??
public class Manager extends User{

    public void accessInformation() {

    }

    public void requestReport() {

    }

    public void changeState(PropertyListing pl, Property p, String s) {
        
    }

    @Override
    public void update(ArrayList<Property> p) {
        properties = p;
        System.out.println("Notification to Manager properties: Changed");
    }

}