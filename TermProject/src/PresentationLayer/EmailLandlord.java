package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmailLandlord extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    protected JTextArea emailBody;

    public EmailLandlord() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
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
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setPreferredSize(new Dimension(700, 500));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        contentPane.add(panel1, BorderLayout.CENTER);
        emailBody = new JTextArea();
        emailBody.setText("");
        panel1.add(emailBody, BorderLayout.CENTER);
        final JLabel label1 = new JLabel();
        label1.setText("Email Body: ");
        panel1.add(label1, BorderLayout.NORTH);
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        contentPane.add(toolBar1, BorderLayout.SOUTH);
        buttonOK = new JButton();
        buttonOK.setText("Send");
        toolBar1.add(buttonOK);
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        toolBar1.add(buttonCancel);
        final JLabel label2 = new JLabel();
        label2.setText("");
        contentPane.add(label2, BorderLayout.NORTH);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
