package Controller;

import PresentationLayer.GUI;

import java.io.IOException;

public class LoginListener {
    private Client client;
    public LoginListener(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public String actionPerformed(String action) throws IOException {
        return client.communicate(action);
    }

    public void changeGUI(GUI g) {
        client.setGUI(g);
        client.runGUI();
    }
}
