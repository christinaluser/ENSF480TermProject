package Controller;

import Domain.Address;
import PresentationLayer.EditFee;
import PresentationLayer.GUI;
import PresentationLayer.RegisterProperty;

import java.io.IOException;

public class LandlordListener {
    private Client client;
    public LandlordListener(Client client) {
        this.client = client;
    }

    public String actionPerformed(String action) throws IOException {
        String [] split = action.split("/");
        if (split[0].equals("SEARCH")) {
            Address address = new Address(Integer.parseInt(split[1]), split[2],  split[3]);
            return client.search(address);
        } else if (split[0].equals("DISPLAY")){
            try {
                return client.display();
            } catch (IOException e) {
                return "Error getting properties";
            }
        } else if (split[0].equals("REGISTER")) {
            RegisterProperty dialog = new RegisterProperty();
            dialog.pack();
            dialog.setVisible(true);
            return client.registerProperty(dialog.getPropertyInfo());
        } else if (split[0].equals("PAY")) {
            return client.payFee(split[1]);
        } else if (split[0].equals("LOGOUT")){
            //TODO me dunno
        }
        return null;
    }

    public void changeGUI(GUI g) {
        client.setGUI(g);
        client.runGUI();
    }
}
