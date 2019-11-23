package PresentationLayer;

import Controller.LandlordListener;

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
    private String[] headers = {"ID", "Type", "Rent", "Location", "Bedrooms", "Bathrooms", "Furnished", "Listing State", "Edit"};

    private void registerProperty() {
        registerPropertyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = null;
                try {
                    response = listener.actionPerformed("REGISTERPROPERTY");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (response == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "property registration was unsuccessful ");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {

                }
            }
        });
    }

    private void searchProperties() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = (int) propertyNumber.getValue() + "/" + streetName.getText() + "/" + postalCode.getText();
                String response = null;
                try {
                    response = listener.actionPerformed("SEARCH" + "/" + address);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
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

//    private void showTable(String response) {
//
//
//        String[] temp = response.split(";");
//        String[][] data = new String[temp.length][headers.length];
//        for (int i = 0; i < temp.length; i++) {
//            String[] temp2 = temp[i].split("/");
//            for (int j = 0; j < temp2.length; j++) {
//                data[i][j] = temp2[j];
//            }
//            data[i][headers.length - 1] = "Edit";
//        }
//
//        TableModel model = new DefaultTableModel(data, headers) {
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        properties = new JTable(model);
//        properties = new JTable(data, headers);
//        properties.getColumn("Edit").setCellRenderer(new TableButtonRenderer());
//        properties.addMouseListener(new TableButtonMouseListener(this));
//
//        properties.setEnabled(false);
//        if (scroll != null)
//            panel.remove(scroll);
//        scroll = new JScrollPane(properties);
//        panel.add(scroll);
//        panel.validate();
//    }

    @Override
    public void tableButtonClicked(int row, String title) {
        EditPropertyState dialog = new EditPropertyState();
        dialog.pack();
        dialog.setVisible(true);
    }

    @Override
    public void updateView() {
        ((SpinnerNumberModel) propertyNumber.getModel()).setMinimum(0);
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
            System.exit(-1);
        }
        return formatter;
    }

}
