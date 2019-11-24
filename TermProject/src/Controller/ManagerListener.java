package Controller;

import Domain.Address;
import Domain.Manager;
import PresentationLayer.EditFee;
import PresentationLayer.GUI;

import java.io.IOException;

public class ManagerListener {
    private Client client;

    public ManagerListener(Client client) {
        this.client = client;
    }

    public String actionPerformed(String action) throws IOException {
        String [] split = action.split("/");
        if (split[0].equals("SEARCHADDRESS")) {
            return client.communicate(action);
        } else if (split[0].equals("DISPLAY")){
            try {
                return client.display();
            } catch (IOException e) {
                return "Error getting properties";
            }
        } else if (split[0].equals("EDITFEE")) {
            EditFee dialog = new EditFee();
            dialog.pack();
            dialog.setVisible(true);
            String toSend = action + "/" + dialog.getValue();
            return client.communicate(toSend);
        } else if (split[0].equals("REPORT")) {
            return client.communicate(action);
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
