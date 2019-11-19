import PresentationLayer.*;
import Controller.*;

import javax.swing.UIManager;
import PresentationLayer.*;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}
        Client client = new Client("10.13.99.23", 5000);
        client.setGUI(new LoginGUI(new Listener(client)));
        client.runGUI();
//        gui.setListener(new Listener(client));
//        gui.updateView(gui);
    }
}