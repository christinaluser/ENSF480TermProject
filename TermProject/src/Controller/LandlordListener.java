package Controller;

import PresentationLayer.GUI;

import java.io.IOException;

public class LandlordListener {
    private Client client;
    public LandlordListener(Client client) {
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
