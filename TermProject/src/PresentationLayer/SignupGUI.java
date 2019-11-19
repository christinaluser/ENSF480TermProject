package PresentationLayer;

import javax.swing.*;

public class SignupGUI implements GUI{
    private JFrame frame;
    private JPanel panel;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField username;
    private JPasswordField password;


    @Override
    public void updateView() {
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
