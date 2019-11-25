package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditPropertyState extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox newState;
    private boolean isForLandlord;

    String getNewState() {
        return (String) newState.getSelectedItem();
    }

    EditPropertyState(boolean b) {
        isForLandlord = b;
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> dispose());

        buttonCancel.addActionListener(e -> dispose());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setMinimumSize(new Dimension(200, 200));
        contentPane.setPreferredSize(new Dimension(300, 100));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        contentPane.add(panel1, BorderLayout.NORTH);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        contentPane.add(panel2, BorderLayout.CENTER);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel2.add(panel3, BorderLayout.CENTER);
        final JPanel spacer1 = new JPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer1, gbc);
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Suspended");
        defaultComboBoxModel1.addElement("Active");
        defaultComboBoxModel1.addElement("Cancelled");
        defaultComboBoxModel1.addElement("Rented");
        newState.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(newState, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("New State");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label1, gbc);
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        contentPane.add(toolBar1, BorderLayout.SOUTH);
        buttonOK = new JButton();
        buttonOK.setText("OK");
        toolBar1.add(buttonOK);
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        toolBar1.add(buttonCancel);
        label1.setLabelFor(newState);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    private void createUIComponents() {
        if (isForLandlord) {
            final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
            defaultComboBoxModel1.addElement("Suspended");
            defaultComboBoxModel1.addElement("Cancelled");
            defaultComboBoxModel1.addElement("Rented");
            newState.setModel(defaultComboBoxModel1);
        }
    }
}
