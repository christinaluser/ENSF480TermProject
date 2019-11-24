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
        String [] split = action.split("/");
        if (split[0].equals("SEARCHRENTER")) {
            return client.communicate(action);

        } else if (split[0].equals("DISPLAY")){
            try {
                return client.display();
            } catch (IOException e) {
                return "Error getting properties";
            }
        }
        return null;
    }

    public void changeGUI(GUI g) {
        client.setGUI(g);
        client.runGUI();
    }
}
