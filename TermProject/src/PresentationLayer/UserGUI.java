package PresentationLayer;

import Controller.Listener;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserGUI {
    private JFrame frame;
    private JButton searchButton;
    private JTable table1;
    private JPanel panel;
    private JComboBox type;
    private JComboBox isFurnished;
    private JComboBox cityQuadrant;
    private JComboBox priceRange;
    private JSpinner noBed;
    private JSpinner noBath;
    private JButton showAllButton;
    private Listener listener;

    public String getType() {
        return (String)type.getSelectedItem();
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
                String criteria = getType() + "/" + getNoBed() + "/" + getNoBath() + "/" + getIsFurnished() + "/"
                        + getCityQuadrant() + "/" + getPriceRange();
                String response = null;
                try {
                    response = listener.actionPerformed("SEARCH" + "/" + criteria);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (response.equals("null")) {
//                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
//                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
//                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
//                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(null, response.replaceAll(";", "\n"), "Item", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    public void updateView(UserGUI gui) {
        ((SpinnerNumberModel) noBed.getModel()).setMinimum(0);
        ((SpinnerNumberModel) noBath.getModel()).setMinimum(0);
        frame = new JFrame("ProperTee");
        frame.setContentPane(gui.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
