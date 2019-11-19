package PresentationLayer;

import Controller.ManagerListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ManagerGUI implements GUI{
    private JFrame frame;
    private JPanel panel;
    private JButton searchButton;
    private JTextField streetName;
    private JButton getReportButton;
    private JButton editFeeButton;
    private JTable properties;
    private JSpinner propertyNumber;
    private JTextField postalCode;
    private JComboBox cityQuadrant;
    private JButton showAllButton;
    private JScrollPane scroll;
    private ManagerListener listener;

    private void searchProperties() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = (int)propertyNumber.getValue() + "/" + streetName.getText() + "/"
                        + postalCode.getText();
                String response = null;
                try {
                    response = listener.actionPerformed("SEARCH" + "/" + address);
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

    @Override
    public void updateView() {
        frame = new JFrame("ProperTee");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void close() {
        frame.dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
