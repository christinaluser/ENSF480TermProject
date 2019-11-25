package PresentationLayer;

import Controller.RenterListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RenterGUI extends UserGUI {
    private JFrame frame;
    private JPanel panel;
    private JButton searchButton;
    private JButton notificationsButton;
    private JComboBox type;
    private JSpinner noBed;
    private JSpinner noBath;
    private JComboBox isFurnished;
    private JComboBox cityQuadrant;
    private JComboBox priceRange;
    private JButton showAllButton;
    private JButton logoutButton;
    private RenterListener listener;
    private String[] headers;

    public RenterGUI() {
        headers = new String[]{"ID", "Type", "Rent", "Property #", "Street", "Postal Code", "City Quadrant", "Bedrooms",
                "Bathrooms", "Furnished", "Contact Landlord"};
    }

    public RenterGUI(RenterListener l) {
        headers = new String[]{"ID", "Type", "Rent", "Property #", "Street", "Postal Code", "City Quadrant", "Bedrooms",
                "Bathrooms", "Furnished", "Contact Landlord"};
        listener = l;
        $$$setupUI$$$();
    }

    private void showAllProperties() throws IOException {
        String result = listener.actionPerformed("DISPLAY");
//        test
//        result = "1/apt/$400/44/street1/g3h 4t3/ne/3/2/furnished;2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished";
        if (result.equals(null)) {
            JOptionPane.showMessageDialog(new JFrame(), "No properties posted at the moment!");
        } else {
            showTable(headers, result, panel);
        }
    }

    private void searchProperties() throws IOException {
        String result = listener.actionPerformed("SEARCH" + "/" + getCriteria());
//        test
//        result = "1/house/$100/44/street1/g3h 4t3/ne/3/2/furnished;2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished";
        if (result.equals(null)) {
            JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
        } else {
            showTable(headers, result, panel);
        }
    }

    private void showNotifiedProperties() throws IOException {
        String result = listener.actionPerformed("DISPLAYNOTIFIED");
//        test
//        result = "2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished";
        if (result.equals(null)) {
            JOptionPane.showMessageDialog(new JFrame(), "No properties match your criteria at the moment");
        } else {
            showTable(headers, result, panel);
        }
    }

    public void activateButtons() {
        notificationsButton.addActionListener(e -> {
            try {
                showNotifiedProperties();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        searchButton.addActionListener(e -> {
            try {
                searchProperties();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        showAllButton.addActionListener(e -> {
            try {
                showAllProperties();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void tableButtonClicked(String propertyId, String colName) {
        EmailLandlord dialog = new EmailLandlord();
        dialog.setTitle(colName + " Property ID: " + propertyId);
        dialog.pack();
        dialog.setVisible(true);

        try {
            listener.actionPerformed("EDIT/" + propertyId + "/" + dialog.emailBody.getText());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateView() {
        frame = new JFrame("ProperTee");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        activateButtons();
    }

//    public static void main(String[] args) {
//        RenterGUI gui = new RenterGUI();
//        gui.updateView();
//    }

    @Override
    public void close() {
        frame.dispose();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel = new javax.swing.JPanel();
        panel.setLayout(new java.awt.BorderLayout(0, 0));
        final javax.swing.JToolBar toolBar1 = new javax.swing.JToolBar();
        panel.add(toolBar1, BorderLayout.NORTH);
        final javax.swing.JLabel label1 = new javax.swing.JLabel();
        toolBar1.add(label1);
        type = new javax.swing.JComboBox();
        toolBar1.add(type);
        final javax.swing.JLabel label2 = new javax.swing.JLabel();
        toolBar1.add(label2);
        toolBar1.add(noBed);
        final javax.swing.JLabel label3 = new javax.swing.JLabel();
        toolBar1.add(label3);
        toolBar1.add(noBath);
        isFurnished = new javax.swing.JComboBox();
        toolBar1.add(isFurnished);
        final javax.swing.JLabel label4 = new javax.swing.JLabel();
        toolBar1.add(label4);
        cityQuadrant = new javax.swing.JComboBox();
        toolBar1.add(cityQuadrant);
        final javax.swing.JLabel label5 = new javax.swing.JLabel();
        toolBar1.add(label5);
        priceRange = new javax.swing.JComboBox();
        toolBar1.add(priceRange);
        searchButton = new javax.swing.JButton();
        toolBar1.add(searchButton);
        final javax.swing.JToolBar toolBar2 = new javax.swing.JToolBar();
        panel.add(toolBar2, BorderLayout.SOUTH);
        showAllButton = new javax.swing.JButton();
        toolBar2.add(showAllButton);
        notificationsButton = new javax.swing.JButton();
        toolBar2.add(notificationsButton);
        logoutButton = new javax.swing.JButton();
        toolBar2.add(logoutButton);
    }

    /**
     * @noinspection ALL
     */
    public javax.swing.JComponent $$$getRootComponent$$$() {
        return panel;
    }

    private void createUIComponents() {
        noBed = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        noBath = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    }
}
