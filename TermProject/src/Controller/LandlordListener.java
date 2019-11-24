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
        if (split[0].equals("SEARCHADDRESS")) {
            return client.communicate(action); // sends "SEARCHADDRESS/<address>
        } else if (split[0].equals("DISPLAY")){
            return client.display();
        } else if (split[0].equals("REGISTERPROPERTY")) {
            return client.communicate(action);
        } else if (split[0].equals("PAY")) {
            return client.communicate(split[1]);
        } else if (split[0].equals("EDITSTATE")) {
            return client.communicate(action);
        } else if (split[0].equals("LOGOUT")){
            client.communicate(action);
        }
        return null;
    }

    public void changeGUI(GUI g) {
        client.setGUI(g);
        client.runGUI();
    }
}
