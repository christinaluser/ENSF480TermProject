package PresentationLayer;

import Controller.Listener;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserGUI implements GUI{
    private JFrame frame;
    private JButton searchButton;
    private JTable properties;
    private JPanel panel;
    private JComboBox type;
    private JComboBox isFurnished;
    private JComboBox cityQuadrant;
    private JComboBox priceRange;
    private JSpinner noBed;
    private JSpinner noBath;
    private JButton showAllButton;
    private JScrollPane scroll;
    private Listener listener;

    public UserGUI(Listener l) {
        listener = l;
    }

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

    private void showAllProperties() {
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = null;
                try {
                    response = listener.actionPerformed("DISPLAY");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                String[] headers = {"Type", "Rent", "Location", " "};
                String[] temp = response.split(";");

                String[][] data = new String[temp.length][];

                for (int i = 0; i < temp.length; i++) {
                    data[i] = temp[i].split("/");
                }

                TableModel model = new DefaultTableModel(data, headers) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                properties = new JTable(model);
//                items = new JTable(data, headers);
//                items.setEnabled(false);

                if (scroll != null)
                    panel.remove(scroll);
                scroll = new JScrollPane(properties);
                panel.add(scroll);

                panel.validate();
            }
        });
    }

    @Override
    public void updateView() {
        ((SpinnerNumberModel) noBed.getModel()).setMinimum(0);
        ((SpinnerNumberModel) noBath.getModel()).setMinimum(0);
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

}
