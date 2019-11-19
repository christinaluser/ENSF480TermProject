package Controller;

import PresentationLayer.GUI;

import java.io.IOException;

public class LoginListener {
    private Client client;
    public LoginListener(Client client) {
        this.client = client;
    }

    public String actionPerformed(String action) throws IOException {

        return action;
    }

    public void changeGUI(GUI g) {
        client.setGUI(g);
        client.runGUI();
    }

    public Client getClient() {
        return client;
    }
}
