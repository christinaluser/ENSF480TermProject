package PresentationLayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableButtonMouseListener extends MouseAdapter {
    private final TableGUI gui;

    public TableButtonMouseListener(TableGUI gui) {
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable source = (JTable)e.getSource();
        int row = source.rowAtPoint( e.getPoint() );
        int column = source.columnAtPoint( e.getPoint() );
        String colName = gui.getProperties().getColumnName(column);

        if (colName =="Edit" || colName =="Pay Fee" || colName == "Contact Landlord"){
            gui.tableButtonClicked((String)source.getValueAt(row, 0), colName);
        }
    }
}