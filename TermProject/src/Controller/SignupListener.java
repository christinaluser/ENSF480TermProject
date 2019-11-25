package Controller;

import PresentationLayer.GUI;

import java.io.IOException;

public class SignupListener {
    private Client client;

    public SignupListener(Client client) {
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
