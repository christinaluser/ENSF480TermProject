package PresentationLayer;

import Controller.RenterListener;
import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private void showAllProperties() {
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = null;
//                try {
//                    response = listener.actionPerformed("DISPLAY");
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
                response = "1/apt/$400/44/street1/g3h 4t3/ne/3/2/furnished;2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished";
                if (response.equals(null)) {
                    JOptionPane.showMessageDialog(new JFrame(), "No properties posted at the moment!");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    showTable(headers, response, panel);
                }
            }
        });
    }

<<<<<<< HEAD
    public RenterGUI(RenterListener l) {
        listener = l;
        searchProperties();
    }

=======
<<<<<<< Updated upstream
=======
    public RenterGUI(RenterListener l) {
        listener = l;
        $$$setupUI$$$();
        searchProperties();
    }

>>>>>>> Stashed changes
>>>>>>> parent of 1582a52... .
    private void searchProperties() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = null;
//                try {
//                    response = listener.actionPerformed("SEARCH" + "/" + getCriteria());
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }

                response = "1/house/$100/44/street1/g3h 4t3/ne/3/2/furnished;2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished";

                if (response.equals("null")) {
                    JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    showTable(headers, response, panel);
                }
            }
        });
    }

    private void showNotifiedProperties() {
        notificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = null;
//                try {
//                    response = listener.actionPerformed("DISPLAYNOTIFIED");
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
                response = "2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished";
                if (response.equals(null)) {
                    JOptionPane.showMessageDialog(new JFrame(), "No properties match your criteria at the moment");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    showTable(headers, response, panel);
                }
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
        showAllProperties();
        searchProperties();
        showNotifiedProperties();
    }

    public static void main(String[] args) {
        RenterGUI gui = new RenterGUI();
        gui.updateView();
    }

    @Override
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
        createUIComponents();
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.setPreferredSize(new Dimension(1100, 900));
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        panel.add(toolBar1, BorderLayout.NORTH);
        final JLabel label1 = new JLabel();
        label1.setText("Type: ");
        toolBar1.add(label1);
        type = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("House");
        defaultComboBoxModel1.addElement("Apartment");
        defaultComboBoxModel1.addElement("Basement");
        defaultComboBoxModel1.addElement("Condo");
        type.setModel(defaultComboBoxModel1);
        toolBar1.add(type);
        final JLabel label2 = new JLabel();
        label2.setText("# Bedrooms: ");
        toolBar1.add(label2);
        toolBar1.add(noBed);
        final JLabel label3 = new JLabel();
        label3.setText("# Bathooms: ");
        toolBar1.add(label3);
        toolBar1.add(noBath);
        isFurnished = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Furnished");
        defaultComboBoxModel2.addElement("Unfurnished");
        isFurnished.setModel(defaultComboBoxModel2);
        toolBar1.add(isFurnished);
        final JLabel label4 = new JLabel();
        label4.setText("Location: ");
        toolBar1.add(label4);
        cityQuadrant = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("NE");
        defaultComboBoxModel3.addElement("NW");
        defaultComboBoxModel3.addElement("SE");
        defaultComboBoxModel3.addElement("SW");
        cityQuadrant.setModel(defaultComboBoxModel3);
        toolBar1.add(cityQuadrant);
        final JLabel label5 = new JLabel();
        label5.setText("Price Range: ");
        toolBar1.add(label5);
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
        final JToolBar toolBar2 = new JToolBar();
        toolBar2.setFloatable(false);
        panel.add(toolBar2, BorderLayout.SOUTH);
        showAllButton = new JButton();
        showAllButton.setText("Show All");
        toolBar2.add(showAllButton);
        notificationsButton = new JButton();
        notificationsButton.setText("Notifications");
        toolBar2.add(notificationsButton);
        logoutButton = new JButton();
        logoutButton.setText("Logout");
        toolBar2.add(logoutButton);
        label2.setLabelFor(noBed);
        label3.setLabelFor(noBath);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

    private void createUIComponents() {
        noBed = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        noBath = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    }
}
