package Controller;

import Domain.SearchCriteria;
import PresentationLayer.GUI;

import java.io.IOException;

public class UserListener {
    private Client client;
    public UserListener(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public String actionPerformed(String action) throws IOException {
        if (action.startsWith("DISPLAY")){
            return client.display();
        } else if (action.startsWith("SEARCH")){
            return client.search(action);
        } else {
            return client.communicate(action);
        }
    }


    public void changeGUI(GUI g) {
        client.setGUI(g);
        client.runGUI();
    }
}
