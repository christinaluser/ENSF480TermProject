package Controller;

import Domain.Renter;
import Domain.SearchCriteria;
import PresentationLayer.GUI;

import java.io.IOException;

public class RenterListener {
    private Client client;
    public RenterListener(Client client) {
        this.client = client;
    }

    public String actionPerformed(String action) throws IOException {
        if(action.startsWith("DISPLAY")){
            return client.display();
        } else {
            return client.communicate(action);
        }
    }

    public void changeGUI(GUI g) {
        client.setGUI(g);
        client.runGUI();
    }
}
