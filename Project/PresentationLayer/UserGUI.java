package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserGUI extends JFrame {
    public static final long serialVersionUID = 1L;
    // private Listener listener;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JTextArea centerText;

    public UserGUI() {
        super();
        setLayout(new BorderLayout(10, 10));
        initNorthPanel();
        initCenterPanel();
        initSouthPanel();
        add("North", northPanel);
        add("Center", centerPanel);
        add("South", southPanel);
    
        pack();
        setVisible(true);
      }
    
      // public void setListener(Listener listener) {
      //   this.listener = listener;
      // }
    
      public void initSouthPanel() {
        southPanel = new JPanel();
        
      }
    
      public void initCenterPanel() {
        centerPanel = new JPanel();
        centerText = new JTextArea(16, 50);
        centerText.setEditable(false);
        centerText.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");  // placeholder
        JScrollPane scrollPane = new JScrollPane(centerText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        centerPanel.add(scrollPane);
      }
    
      public void initNorthPanel() {
        northPanel = new JPanel();
        JLabel text = new JLabel("Rental Management System");
        northPanel.add(text);
      }
}