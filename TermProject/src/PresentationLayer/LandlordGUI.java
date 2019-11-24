package PresentationLayer;

import Controller.LandlordListener;
import Domain.Landlord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class LandlordGUI extends TableGUI {
    private JFrame frame;
    private JPanel panel;
    private JTable properties;
    private JScrollPane scroll;
    private JButton searchButton;
    private JTextField streetName;
    private JSpinner propertyNumber;
    private JTextField postalCode;
    private JButton registerPropertyButton;
    private JButton showAllButton;
    private JButton logoutButton;
    private LandlordListener listener;

    public LandlordGUI() {
        headers = new String[]{"ID", "Type", "Rent", "Property #", "Street", "Postal Code", "City Quadrant", "Bedrooms",
                "Bathrooms", "Furnished", "Listing State", "Pay Fee", "Edit"};
    }

    private void registerProperty() {
        registerPropertyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterProperty dialog = new RegisterProperty();
                dialog.pack();
                dialog.setVisible(true);
                String propertyInfo = dialog.getPropertyInfo();
                String response = null;
                try {
                    response = listener.actionPerformed("REGISTERPROPERTY/" + propertyInfo);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (response == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "property registration was unsuccessful");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "property has been registered");
                    showTable(headers, response, panel);
                }
            }
        });
    }

    private void searchProperties() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = propertyNumber.getValue() + "/" + streetName.getText() + "/" + postalCode.getText();
                String response;
                try {
                    response = listener.actionPerformed("SEARCHADDRESS" + "/" + address);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

                response = "1/house/$100/44/street1/g3h 4t3/ne/3/2/furnished/suspended;2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished/active";
                if (response == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    showTable(headers, response, panel);

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

//                response = "1/house/100/ne/3/2/furnished/suspended;2/apt/200/se/4/3/unfurnished/active";

                if (response == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    showTable(headers, response, panel);
                }
            }
        });
    }

    //TODO Copy manager logout when its done

    @Override
    public void tableButtonClicked(String propertyId, String colName) {
        if (colName == "Edit") {
            EditPropertyState dialog = new EditPropertyState();
            dialog.setTitle(colName + " Property ID: " + propertyId);
            dialog.pack();
            dialog.setVisible(true);
            try {
                listener.actionPerformed("EDITSTATE/" + propertyId + "/" + dialog.getNewState());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                int confirm = JOptionPane.showConfirmDialog(new JFrame(), "Pay fee for this property?", colName + " Property ID: " + propertyId, JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    listener.actionPerformed("PAY/" + propertyId);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void updateView() {
        ((SpinnerNumberModel) propertyNumber.getModel()).setMinimum(0);
        frame = new JFrame("ProperTee");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        showAllProperties();
        searchProperties();
        registerProperty();
//        logout();
    }

    @Override
    public void close() {
        frame.dispose();
    }

    private void createUIComponents() {
        propertyNumber = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        postalCode = new JFormattedTextField(createFormatter("U#U #U#"));
    }

    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
        }
        return formatter;
    }

    public static void main(String[] args) {
        LandlordGUI gui = new LandlordGUI();
        gui.updateView();
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
        panel.setEnabled(true);
        panel.setPreferredSize(new Dimension(1100, 900));
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        panel.add(toolBar1, BorderLayout.NORTH);
        toolBar1.add(propertyNumber);
        streetName = new JTextField();
        streetName.setText("Street");
        toolBar1.add(streetName);
        postalCode.setText("Postal Code");
        toolBar1.add(postalCode);
        searchButton = new JButton();
        searchButton.setText("Search");
        toolBar1.add(searchButton);
        final JToolBar toolBar2 = new JToolBar();
        toolBar2.setFloatable(false);
        panel.add(toolBar2, BorderLayout.SOUTH);
        registerPropertyButton = new JButton();
        registerPropertyButton.setText("Register Property");
        toolBar2.add(registerPropertyButton);
        showAllButton = new JButton();
        showAllButton.setText("Show All ");
        toolBar2.add(showAllButton);
        logoutButton = new JButton();
        logoutButton.setText("Logout");
        toolBar2.add(logoutButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}

