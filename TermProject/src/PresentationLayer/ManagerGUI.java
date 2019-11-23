package PresentationLayer;

import Controller.Client;
import Controller.ManagerListener;
import Domain.Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;

public class ManagerGUI implements TableGUI {
    private JFrame frame;
    private JPanel panel;
    private JButton getReportButton;
    private JButton editFeeButton;
    private JButton showAllButton;
    private JButton logoutButton;
    private JButton searchButton;
    private JTextField streetName;
    private JTable properties;
    private JSpinner propertyNumber;
    private JFormattedTextField postalCode;
    private JScrollPane scroll;
    private ManagerListener listener;

    public ManagerGUI(ManagerListener l) {
        listener = l;
    }

    public ManagerGUI() {
    }

    public void setListener(ManagerListener listener) {
        this.listener = listener;
    }

    private void searchProperties() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = (int) propertyNumber.getValue() + "/" + streetName.getText() + "/" + postalCode.getText();
                String response = null;
//                try {
//                    response = listener.actionPerformed("SEARCH" + "/" + address);
//                } catch (IOException ex) {
//                    System.err.println(ex.getMessage());
//                }

                response = "1/house/100/ne/3/2/furnished/suspended;2/apt/200/se/4/3/unfurnished/active";
                if (response == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    showTable(response);

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
                    showTable(response);
                }
            }
        });
    }

    private void editFee() {
        editFeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditFee dialog = new EditFee();
                dialog.pack();
                dialog.setVisible(true);

                String toSend = "EDITFEE/" + dialog.getValue();
                String response = null;
                try {
                    response = listener.actionPerformed(toSend);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

                if (response == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Edit unsuccessful");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Edit successful");
                }
            }
        });
    }

    //TODO: i might have interpreted how get report works wrong (should it be that the manager specifies any period of time ?)

    private void getReport() {
        getReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RequestReport dialog = new RequestReport();
                dialog.pack();
                dialog.setVisible(true);

                String toSend = "REPORT/" + dialog.getDates();
                String response = null;

                try {
                    response = listener.actionPerformed(toSend);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

                if (response == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Could not retrieve report");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    displayReport(response);
                }
            }
        });
    }

    private void logout() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = null;
                try {
                    response = listener.actionPerformed("LOGOUT");
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

                if (response == null) {
                    // TODO idk lol
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
                    //TODO idk this either lol probably switch gui
                }
            }
        });

    }

    private void displayReport(String response) {
        Report dialog = new Report();
        dialog.pack();
        dialog.setVisible(true);
        //TODO: need to fix following after implementing db
        //TODO i think it will be the same as display table but in a text area instead
        dialog.reportText.setText(response);
    }

    private void showTable(String response) {
        String[] headers = {"ID", "Type", "Rent", "Location", "Bedrooms", "Bathrooms", "Furnished", "Listing State", "Edit"};

        String[] temp = response.split(";");
        String[][] data = new String[temp.length][headers.length];
        for (int i = 0; i < temp.length; i++) {
            String[] temp2 = temp[i].split("/");
            for (int j = 0; j < temp2.length; j++) {
                data[i][j] = temp2[j];
            }
            data[i][headers.length - 1] = "Edit";
        }

        TableModel model = new DefaultTableModel(data, headers) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        properties = new JTable(model);
        properties = new JTable(data, headers);
        TableButtonRenderer buttonRenderer = new TableButtonRenderer();
        properties.getColumn("Edit").setCellRenderer(buttonRenderer);
        properties.addMouseListener(new TableButtonMouseListener(this));

        properties.setEnabled(false);
        if (scroll != null)
            panel.remove(scroll);
        scroll = new JScrollPane(properties);
        panel.add(scroll);
        panel.validate();
    }

    @Override
    public void tableButtonClicked() {
        EditPropertyState dialog = new EditPropertyState();
        dialog.pack();
        dialog.setVisible(true);
    }

    @Override
    public void updateView() {
        frame = new JFrame("ProperTee");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        searchProperties();
        getReport();
        logout();
        editFee();
        showAllProperties();
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
        ManagerGUI gui = new ManagerGUI();
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
