package Domain;

import java.util.ArrayList;

public abstract class User implements Observer{
    public String name;
    public String email;
    public String username;
    ArrayList<Property> properties;

    public void updateCriteria(Property p) {
        
    };
}