package Controller;

import PresentationLayer.UserGUI;
import Domain.SearchCriteria;
import java.io.IOException;

public class Listener {
    private Client client;
    public Listener(Client client) {
        this.client = client;
    }

    public String actionPerformed(String action, UserGUI gui) {
        if (action.equals("SEARCH")) {
            SearchCriteria criteria = new SearchCriteria(gui.getType(), gui.getNoBed(), gui.getNoBath(),
                    gui.getIsFurnished(), gui.getCityQuadrant(), gui.getPriceRange());
            return client.search(criteria);
        } else if (action.equals("SHOW")){

        }
        return null;
    }
}