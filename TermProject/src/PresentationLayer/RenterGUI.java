package PresentationLayer;

import Controller.RenterListener;
import com.sun.javaws.util.JfxHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RenterGUI extends UserGUI implements GUI {
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
    private RenterListener listener;

    private void searchProperties() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String criteria = getType() + "/" + getNoBed() + "/" + getNoBath() + "/" + getIsFurnished() + "/"
                        + getCityQuadrant() + "/" + getPriceRange();
                String response = null;
                try {
                    response = listener.actionPerformed("SEARCHRENTER/" + criteria);
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

    @Override
    public void updateView() {
        frame = new JFrame("ProperTee");
        frame.setContentPane(panel);
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
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        final JToolBar toolBar1 = new JToolBar();
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
        toolBar1.add(noBed);
        final JLabel label2 = new JLabel();
        label2.setText("#Bathooms");
        toolBar1.add(label2);
        noBath = new JSpinner();
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
        notificationsButton = new JButton();
        notificationsButton.setText("Notifications");
        toolBar1.add(notificationsButton);
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
