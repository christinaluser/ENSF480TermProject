package Domain;

import java.util.ArrayList;

public interface Observer {

    abstract void update(ArrayList<Property> p);

}