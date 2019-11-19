package PresentationLayer;

import Controller.Listener;
import Controller.LoginListener;
import Controller.UserListener;

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
    private LoginListener listener;

    public LoginGUI(LoginListener l) {
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

    @Override
    public void close() {
        frame.dispose();
    }

    public void activateButtons() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.changeGUI(new UserGUI(new UserListener(listener.getClient())));
            }
        });
    }

}
