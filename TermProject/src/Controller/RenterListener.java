package Controller;

import Domain.Renter;
import PresentationLayer.GUI;

import java.io.IOException;

public class RenterListener {
    private Client client;
    public RenterListener(Client client) {
        this.client = client;
    }

    public String actionPerformed(String action) throws IOException {

        return action;
    }

    public void changeGUI(GUI g) {
        client.setGUI(g);
        client.runGUI();
    }
}
