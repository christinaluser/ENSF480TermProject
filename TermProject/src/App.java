import PresentationLayer.*;
import Controller.*;

import javax.swing.UIManager;
import PresentationLayer.*;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}
        Client client = new Client("localhost", 5050);
        client.setGUI(new LoginGUI(new LoginListener(client)));
        client.runGUI();
    }
}