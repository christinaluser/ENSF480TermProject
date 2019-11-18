package Domain;

import java.util.ArrayList;

public class PropertyListing implements PropertySubject{
    ArrayList<Observer> observers;
    ArrayList<Property> properties;

    PropertyListing() {
        observers = new ArrayList<Observer>();
        properties = new ArrayList<Property>();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
        o.update(properties);
    }

    @Override
    public void removeObserver(Observer o) {

    }

    public void notifyAllObservers() {
        for(int i = 0; i < observers.size(); i++) {
            Observer o = observers.get(i);
            o.update(properties);
        }
    }

    public void addProperty(Property p) {
        properties.add(p);
        notifyAllObservers();
    }

    public void removeProperty(Property p) {
        properties.remove(p);
        notifyAllObservers();
    }

}