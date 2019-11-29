package PresentationLayer;

import Controller.LandlordListener;
import Controller.LoginListener;
import Controller.UserListener;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class LandlordGUI extends TableGUI {
    private JFrame frame;
    private JPanel panel;
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

    public LandlordGUI(LandlordListener l) {
        headers = new String[]{"ID", "Type", "Rent", "Property #", "Street", "Postal Code", "City Quadrant", "Bedrooms",
                "Bathrooms", "Furnished", "Listing State", "Pay Fee", "Edit"};
        listener = l;
    }

    private String getAddress() {
        return propertyNumber.getValue() + "/" + streetName.getText() + "/" + postalCode.getText();
    }

    private void registerProperty() throws IOException {
        String dialogInfo = displayRegisterPropertyDialog();
        if (!dialogInfo.equals("null")) {
            String result = listener.actionPerformed("REGISTERPROPERTY/" + dialogInfo);
            if (result.equals("null")) {
                JOptionPane.showMessageDialog(new JFrame(), "Failed to register property");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Successfully registered property");
//            showTable(headers, result, panel);
            }
        }
    }

    private String displayRegisterPropertyDialog() {
        RegisterProperty dialog = new RegisterProperty();
        dialog.pack();
        dialog.setVisible(true);
        if (!dialog.checkValid()) {
            return "null";
        } else {
            return dialog.getPropertyInfo();
        }
    }

    private void searchProperties() throws IOException {
        String result = listener.actionPerformed("SEARCHADDRESS" + "/" + getAddress());
//        test
//        String result = "1/house/$100/44/street1/g3h 4t3/ne/3/2/furnished/suspended;2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished/active";
        if (result.equals("null")) {
            JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
        } else {
            showTable(headers, result, panel);
        }
    }

    private void showAllProperties() throws IOException {
        String result = listener.actionPerformed("DISPLAY");
//        test
//        String result = "1/house/100/ne/3/2/furnished/suspended;2/apt/200/se/4/3/unfurnished/active";
        if (result.equals("null")) {
            JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
        } else {
            showTable(headers, result, panel);
        }
    }

    private void logout() throws IOException {
        String result = listener.actionPerformed("LOGOUT");
        if (result.equals("null")) {
            JOptionPane.showMessageDialog(new JFrame(), "Logout unsuccessful.");
        } else {
            listener.changeGUI(new LoginGUI(new LoginListener(listener.getClient())));
            JOptionPane.showMessageDialog(new JFrame(), "Logged out!");
        }

    }

    private void activateButtons() {
        registerPropertyButton.addActionListener(e -> {
            try {
                registerProperty();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        searchButton.addActionListener(e -> {
            try {
                searchProperties();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        showAllButton.addActionListener(e -> {
            try {
                showAllProperties();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        logoutButton.addActionListener(e -> {
            try {
                logout();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
    }

    @Override
    public void tableButtonClicked(String propertyId, String colName) {
        if (colName.equals("Edit")) {
            EditPropertyState dialog = new EditPropertyState(true);
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
        activateButtons();
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
        final JLabel label1 = new JLabel();
        label1.setText("Property Number  ");
        toolBar1.add(label1);
        propertyNumber.setPreferredSize(new Dimension(100, 30));
        toolBar1.add(propertyNumber);
        final JLabel label2 = new JLabel();
        label2.setText("   Street  ");
        toolBar1.add(label2);
        streetName = new JTextField();
        streetName.setPreferredSize(new Dimension(300, 30));
        streetName.setText("");
        toolBar1.add(streetName);
        final JLabel label3 = new JLabel();
        label3.setText("   Postal Code  ");
        toolBar1.add(label3);
        postalCode.setPreferredSize(new Dimension(200, 30));
        postalCode.setText("");
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

