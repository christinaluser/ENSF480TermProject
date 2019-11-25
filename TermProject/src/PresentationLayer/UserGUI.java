package PresentationLayer;

import Controller.LoginListener;
import Controller.UserListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class UserGUI extends TableGUI {
    private JFrame frame;
    private JButton searchButton;
    private JPanel panel;
    private JComboBox type;
    private JComboBox isFurnished;
    private JComboBox cityQuadrant;
    private JComboBox priceRange;
    private JSpinner noBed;
    private JSpinner noBath;
    private JButton showAllButton;
    private JButton loginButton;
    private UserListener listener;
    private String[] headers;

    public UserGUI() {
        headers = new String[]{"ID", "Type", "Rent", "Property #", "Street", "Postal Code", "City Quadrant", "Bedrooms",
                "Bathrooms", "Furnished", "Contact Landlord"};
    }

    public UserGUI(UserListener l) {
        headers = new String[]{"ID", "Type", "Rent", "Property #", "Street", "Postal Code", "City Quadrant", "Bedrooms",
                "Bathrooms", "Furnished", "Contact Landlord"};
        listener = l;
    }

    public String getCriteria() {
        return type.getSelectedItem() + "/" + noBed.getValue() + "/" + noBath.getValue()
                + "/" + getIsFurnished() + "/" + cityQuadrant.getSelectedItem() + "/" + priceRange.getSelectedItem();
    }

    public boolean getIsFurnished() {
        if (isFurnished.getSelectedIndex() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void searchProperties() throws IOException {
        String result = listener.actionPerformed("SEARCH" + "/" + getCriteria());
//        test
//        result = "1/house/$100/44/street1/g3h 4t3/ne/3/2/furnished;2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished";
        if (result.equals("null")) {
            JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
        } else {
            showTable(headers, result, panel);
        }
    }

    private void showAllProperties() throws IOException {
        String result = listener.actionPerformed("DISPLAY");
        if (result.equals(null)) {
            JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
        } else {
            showTable(headers, result, panel);
        }
    }

    public void activateButtons() {
        showAllButton.addActionListener(e -> {
            try {
                showAllProperties();
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

        loginButton.addActionListener(e -> listener.changeGUI(new LoginGUI(new LoginListener(listener.getClient()))));
    }

    @Override
    public void tableButtonClicked(String propertyId, String colName) {
        EmailLandlordUnregistered dialog = new EmailLandlordUnregistered();
        dialog.pack();
        dialog.setVisible(true);
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
        activateButtons();
    }

//    public static void main(String[] args) {
//        UserGUI gui = new UserGUI();
//        gui.updateView();
//    }


    public void close() {
        frame.dispose();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        panel.add(toolBar1, BorderLayout.CENTER);
        type = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("House");
        defaultComboBoxModel1.addElement("Apartment");
        defaultComboBoxModel1.addElement("Basement");
        defaultComboBoxModel1.addElement("Condo");
        type.setModel(defaultComboBoxModel1);
        toolBar1.add(type);
        final JLabel label1 = new JLabel();
        label1.setText("#Bedrooms");
        toolBar1.add(label1);
        noBed = new JSpinner();
        noBed.setAutoscrolls(false);
        noBed.setEnabled(true);
        toolBar1.add(noBed);
        final JLabel label2 = new JLabel();
        label2.setText("#Bathrooms");
        toolBar1.add(label2);
        noBath = new JSpinner();
        noBath.setVerifyInputWhenFocusTarget(true);
        toolBar1.add(noBath);
        isFurnished = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Furnished");
        defaultComboBoxModel2.addElement("Unfurnished");
        isFurnished.setModel(defaultComboBoxModel2);
        toolBar1.add(isFurnished);
        cityQuadrant = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("NE");
        defaultComboBoxModel3.addElement("NW");
        defaultComboBoxModel3.addElement("SE");
        defaultComboBoxModel3.addElement("SW");
        cityQuadrant.setModel(defaultComboBoxModel3);
        toolBar1.add(cityQuadrant);
        priceRange = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        defaultComboBoxModel4.addElement("$0");
        defaultComboBoxModel4.addElement("$400");
        defaultComboBoxModel4.addElement("$600");
        defaultComboBoxModel4.addElement("$800");
        defaultComboBoxModel4.addElement("$1000");
        defaultComboBoxModel4.addElement("$1200");
        defaultComboBoxModel4.addElement("$1400");
        priceRange.setModel(defaultComboBoxModel4);
        toolBar1.add(priceRange);
        searchButton = new JButton();
        searchButton.setText("Search");
        toolBar1.add(searchButton);
        showAllButton = new JButton();
        showAllButton.setText("Show All");
        toolBar1.add(showAllButton);
        label1.setLabelFor(noBed);
        label2.setLabelFor(noBath);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
