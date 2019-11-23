package PresentationLayer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableButtonMouseListener extends MouseAdapter {
    private final TableGUI gui;

    public TableButtonMouseListener(TableGUI gui) {
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gui.tableButtonClicked();

    }
}