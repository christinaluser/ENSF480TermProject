package PresentationLayer;

import javax.swing.*;

public class LandlordGUI implements GUI{
    private JFrame frame;
    private JPanel panel;
    private JButton searchButton;
    private JTextField streetName;
    private JButton registerPropertyButton;
    private JTable properties;
    private JSpinner propertyNumber;
    private JTextField postalCode;
    private JButton showAllButton;

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
}
