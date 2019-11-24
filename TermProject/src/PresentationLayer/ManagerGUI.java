package PresentationLayer;

import Controller.ManagerListener;
import Controller.UserListener;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class ManagerGUI extends TableGUI {
    private JFrame frame;
    private JPanel panel;
    private JButton getReportButton;
    private JButton editFeeButton;
    private JButton showAllButton;
    private JButton logoutButton;
    private JButton searchButton;
    private JTextField streetName;
    private JSpinner propertyNumber;
    private JFormattedTextField postalCode;
    private ManagerListener listener;


    public ManagerGUI(ManagerListener l) {
        headers = new String[]{"ID", "Type", "Rent", "Property #", "Street", "Postal Code", "City Quadrant", "Bedrooms",
                "Bathrooms", "Furnished", "Listing State", "Edit"};
        listener = l;
    }

    public ManagerGUI() {
        headers = new String[]{"ID", "Type", "Rent", "Property #", "Street", "Postal Code", "City Quadrant", "Bedrooms",
                "Bathrooms", "Furnished", "Listing State", "Edit"};
    }

    private String getAddress() {
        return propertyNumber.getValue() + "/" + streetName.getText() + "/" + postalCode.getText();
    }

    private void searchProperties() throws IOException {
        String result = listener.actionPerformed("SEARCHADDRESS" + "/" + getAddress());
//        test
//        result = "1/house/$100/44/street1/g3h 4t3/ne/3/2/furnished/suspended;2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished/active";
        if (result.equals("null")) {
            JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
        } else {
            showTable(headers, result, panel);
        }

    }

    private void showAllProperties() throws IOException {
        String result = listener.actionPerformed("DISPLAY");
        if (result.equals("null")) {
            JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
        } else {
            showTable(headers, result, panel);
        }
    }

    private void editFee() throws IOException {
        String result = listener.actionPerformed("EDITFEE/" + displayEditDialog());
        if (result.equals("null")) {
            JOptionPane.showMessageDialog(new JFrame(), "Failed to edit listing state");
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Successfully edited listing state!");
        }
    }

    private String displayEditDialog() {
        EditFee dialog = new EditFee();
        dialog.pack();
        dialog.setVisible(true);
        return dialog.getValue();
    }

    private void getReport() throws IOException {
        String result = listener.actionPerformed("REPORT/" + displayReportRequestDialog());
        if (result.equals("null")) {
            JOptionPane.showMessageDialog(new JFrame(), "Could not retrieve report");
        } else {
            displayReport(result);
        }
    }

    private String displayReportRequestDialog() {
        RequestReport dialog = new RequestReport();
        dialog.pack();
        dialog.setVisible(true);
        return dialog.getDates();
    }

    private void displayReport(String response) {
        Report dialog = new Report();
        dialog.pack();
        dialog.setVisible(true);
        //TODO: need to figure this out
        dialog.setReportText(response);
    }

    private void logout() throws IOException {
        String result = listener.actionPerformed("LOGOUT");
        if (result.equals("null")) {
            JOptionPane.showMessageDialog(new JFrame(), "Logout unsuccessful.");
        } else {
            listener.changeGUI(new UserGUI(new UserListener(listener.getClient())));
            JOptionPane.showMessageDialog(new JFrame(), "Logged out!");
        }
    }

    private void activateButtons() {
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

        editFeeButton.addActionListener(e -> {
            try {
                editFee();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        getReportButton.addActionListener(e -> {
            try {
                getReport();
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
        EditPropertyState dialog = new EditPropertyState(false);
        dialog.setTitle(colName + " Property ID: " + propertyId);
        dialog.pack();
        dialog.setVisible(true);
        try {
            listener.actionPerformed("EDITSTATE/" + propertyId + "/" + dialog.getNewState());
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

    @Override
    public void close() {
        frame.dispose();
    }

    private void createUIComponents() {
        propertyNumber = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        postalCode = new JFormattedTextField(createFormatter("U#U #U#"));
    }

    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
        }
        return formatter;
    }

//    public static void main(String[] args) {
//        ManagerGUI gui = new ManagerGUI();
//        gui.updateView();
//    }


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
        panel.setPreferredSize(new Dimension(750, 750));
        final JToolBar toolBar1 = new JToolBar();
        panel.add(toolBar1, BorderLayout.NORTH);
        propertyNumber.setPreferredSize(new Dimension(150, 30));
        toolBar1.add(propertyNumber);
        streetName = new JTextField();
        toolBar1.add(streetName);
        toolBar1.add(postalCode);
        searchButton = new JButton();
        searchButton.setText("Search");
        toolBar1.add(searchButton);
        final JToolBar toolBar2 = new JToolBar();
        toolBar2.setFloatable(false);
        panel.add(toolBar2, BorderLayout.SOUTH);
        getReportButton = new JButton();
        getReportButton.setText("Get Report");
        toolBar2.add(getReportButton);
        editFeeButton = new JButton();
        editFeeButton.setText("Edit Fee");
        toolBar2.add(editFeeButton);
        showAllButton = new JButton();
        showAllButton.setText("Show All");
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
