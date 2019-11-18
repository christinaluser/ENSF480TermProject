import javax.swing.UIManager;
import PresentationLayer.*;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}
        UserGUI gui = new UserGUI();
    }
}