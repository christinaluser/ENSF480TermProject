package Controller;

import PresentationLayer.UserGUI;
import Domain.SearchCriteria;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Listener {
    private Client client;
    public Listener(Client client) {
        this.client = client;
    }

    public String actionPerformed(String action) throws IOException {
        String [] split = action.split("/");
        if (split[0].equals("SEARCH")) {
            SearchCriteria criteria = new SearchCriteria(split[1], Integer.parseInt(split[2]),
                    Integer.parseInt(split[3]), Boolean.parseBoolean(split[4]), split[5], Double.parseDouble(split[6]));
            return client.search(criteria);
        } else if (split[0].equals("SHOW")){

        }
        return null;
    }

}