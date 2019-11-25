package Controller;

import PresentationLayer.GUI;

import java.io.IOException;

public class ManagerListener {
    private Client client;

    public ManagerListener(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
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
