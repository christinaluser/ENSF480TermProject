package Domain;

import java.util.ArrayList;

public interface Observer {
    //public PropertySubject subject;
    public void update(ArrayList<Property> p);
}