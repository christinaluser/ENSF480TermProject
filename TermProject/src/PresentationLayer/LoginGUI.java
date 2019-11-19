package PresentationLayer;

import Controller.Listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI implements GUI{
    private JFrame frame;
    private JTextField textField1;
    private JPanel panel1;
    private JButton loginButton;
    private JButton signupButton;
    private JButton continueAsGuestButton;
    private JPasswordField passwordField1;
    private Listener listener;

    public LoginGUI(Listener l) {
        listener = l;
        activateButtons();
    }

    @Override
    public void updateView() {
        frame = new JFrame("ProperTee");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void activateButtons() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
