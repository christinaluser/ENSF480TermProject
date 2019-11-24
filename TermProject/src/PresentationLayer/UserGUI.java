package PresentationLayer;

import Controller.UserListener;
import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserGUI extends TableGUI {
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
    private UserListener listener;
    private String[] headers;

    public UserGUI() {

    }

    public UserGUI(UserListener l) {
        listener = l;
        searchProperties();
        showAllProperties();
    }

    public String getType() {
        return (String) type.getSelectedItem();
    }

    public boolean getIsFurnished() {
        if (isFurnished.getSelectedIndex() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getCityQuadrant() {
        return (String) cityQuadrant.getSelectedItem();
    }

    public double getPriceRange() {
        return (double) priceRange.getSelectedItem();
    }

    public int getNoBed() {
        return (int) noBed.getValue();
    }

    public int getNoBath() {
        return (int) noBath.getValue();
    }

    public void setListener(UserListener listener) {
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
<<<<<<< HEAD
                    response = listener.actionPerformed("SEARCH" + "/" + getCriteria());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                response = "1/house/$100/44/street1/g3h 4t3/ne/3/2/furnished;2/apt/200/44/street1/g3h 4t3/se/4/3/unfurnished";

=======
                    response = listener.actionPerformed("SEARCH" + "/" + criteria);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
>>>>>>> parent of 6c87455... fixed usergui
                if (response.equals("null")) {
//                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
//                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    JOptionPane.showMessageDialog(new JFrame(), "No properties found!");
                } else if (response.equals("CLOSE")) {
                    //do nothing
                } else {
//                    UIManager.put("OptionPane.background", new ColorUIResource(239, 214, 249));
//                    UIManager.put("Panel.background", new ColorUIResource(239, 214, 249));
                    ;
                }
            }
        });
    }

    @Override
    public void tableButtonClicked(String propertyId, String colName) {
        //TODO figure out how to get info
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

                showTable(headers, response, panel);
            }
        });
    }

    public void updateView() {
        ((SpinnerNumberModel) noBed.getModel()).setMinimum(0);
        ((SpinnerNumberModel) noBath.getModel()).setMinimum(0);
        frame = new JFrame("ProperTee");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

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
