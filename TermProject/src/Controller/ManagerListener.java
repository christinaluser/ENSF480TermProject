package Controller;

import Domain.Address;
import Domain.Manager;
import PresentationLayer.GUI;

import java.io.IOException;

public class ManagerListener {
    private Client client;

    public ManagerListener(Client client) {
        this.client = client;
    }

    public String actionPerformed(String action) throws IOException {
        String [] split = action.split("/");
        if (split[0].equals("SEARCH")) {
            Address address = new Address(Integer.parseInt(split[1]), split[2],  split[3]);
//            return client.search(address);
            return "1/house/200/ne;2/apt/300/se";
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
