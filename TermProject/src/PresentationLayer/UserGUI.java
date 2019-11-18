package PresentationLayer;

import Controller.Listener;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserGUI {
    private JButton searchButton;
    private JTable table1;
    private JPanel panel;
    private JComboBox Type;
    private JComboBox isFurnished;
    private JComboBox cityQuadrant;
    private JComboBox priceRange;
    private JSpinner noBed;
    private JSpinner noBath;
    private JButton showAllButton;
    private Listener listener;

    public String getType() {
        return (String)Type.getSelectedItem();
    }

    public boolean getIsFurnished(){
        if (isFurnished.getSelectedIndex() == 0){
            return true;
        } else {
            return false;
        }
    }

    public String getCityQuadrant (){
        return (String)cityQuadrant.getSelectedItem();
    }

    public double getPriceRange (){
        return (double)priceRange.getSelectedItem();
    }

    public int getNoBed(){
        return (int)noBed.getValue();
    }

    public int getNoBath(){
        return (int)noBath.getValue();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private void searchProperties() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = listener.actionPerformed("SEARCH", this);
                if (response.equals("null")) {
                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
                }
//                else if (response.equals("invalid")) {
//                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
//                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
//                    JOptionPane.showMessageDialog(null, "Please input valid criteria", "Error", JOptionPane.ERROR_MESSAGE);
//                }
                else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(null, response.replaceAll(";", "\n"), "Item", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    public void updateView(UserGUI gui) {
        JFrame frame = new JFrame("ProperTee");
        frame.setContentPane(gui.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
