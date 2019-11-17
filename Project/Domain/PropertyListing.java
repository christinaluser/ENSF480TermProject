package Domain;

import java.util.ArrayList;

public class PropertyListing implements subject{
    ArrayList<Observer> observers;
    ArrayList<Property> properties;

    PropertyListing() {
        observers = new ArrayList<observers>();
        properties = new ArrayList<properties>();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
        o.update(properties);
    }

    public void removeObservers(Observer o) {
        observers.remove(o);
    }

    public void notifyAllObservers() {
        for(int i = 0; i < observers.size(); i++) {
            Observer o = observers.get(i);
            o.update(data);
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