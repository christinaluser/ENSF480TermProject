package PresentationLayer;

import Controller.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginGUI implements GUI {
    private JFrame frame;
    private JPanel panel1;
    private JButton signupButton;
    private JButton continueWithoutLoginButton;
    private JComboBox loginType;
    private JPasswordField password;
    private JTextField username;
    private JButton loginButton;
    private LoginListener listener;

    public LoginGUI(LoginListener l) {
        listener = l;
        activateButtons();
    }

    public LoginGUI() {
        activateButtons();
    }


    private String getLoginType() {
        return (String) loginType.getSelectedItem();
    }

    private String getLoginInfo() {
        return loginType.getSelectedItem() + "/" + username.getText() + "/" + password.getPassword();
    }

    private void login() throws IOException {
//        String type = getLoginType();
        String result = listener.actionPerformed("LOGIN/" + getLoginInfo());

        if (result.equals(null)) {
            JOptionPane.showMessageDialog(new JFrame(), "Login unsuccessful, please try again.");
        } else {
            if (result.equals("Manager")) {
                listener.changeGUI(new ManagerGUI(new ManagerListener(listener.getClient())));
            } else if (result.equals("Renter")) {
                listener.changeGUI(new RenterGUI(new RenterListener(listener.getClient())));
            } else if (result.equals("Landlord")) {
                listener.changeGUI(new LandlordGUI(new LandlordListener(listener.getClient())));
            }
        }
    }

    public void activateButtons() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.changeGUI(new SignupGUI(new SignupListener(listener.getClient())));
            }
        });

        continueWithoutLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listener.actionPerformed("CONTINUE");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                listener.changeGUI(new UserGUI(new UserListener(listener.getClient())));
            }
        });
    }

    @Override
    public void updateView() {
        frame = new JFrame("ProperTee");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        activateButtons();
    }

    @Override
    public void close() {
        frame.dispose();
    }

    public static void main(String[] args) {
        LoginGUI gui = new LoginGUI();
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
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setMinimumSize(new Dimension(500, 500));
        panel1.setPreferredSize(new Dimension(500, 500));
        panel1.setBorder(BorderFactory.createTitledBorder(null, "ProperTee ", TitledBorder.CENTER, TitledBorder.BELOW_TOP, this.$$$getFont$$$(null, -1, -1, panel1.getFont()), new Color(-4473925)));
        loginButton = new JButton();
        loginButton.setText("Login");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(loginButton, gbc);
        username = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(username, gbc);
        password = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(password, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Username");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label2, gbc);
        signupButton = new JButton();
        signupButton.setText("Signup");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(signupButton, gbc);
        continueWithoutLoginButton = new JButton();
        continueWithoutLoginButton.setText("Continue without Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(continueWithoutLoginButton, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer4, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Login as: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label3, gbc);
        loginType = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Renter");
        defaultComboBoxModel1.addElement("Manager");
        defaultComboBoxModel1.addElement("Landlord");
        loginType.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(loginType, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer5, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
