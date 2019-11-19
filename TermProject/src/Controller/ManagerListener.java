package Controller;

import Domain.Manager;
import PresentationLayer.GUI;

import java.io.IOException;

public class ManagerListener {
    private Client client;
    public ManagerListener(Client client) {
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
