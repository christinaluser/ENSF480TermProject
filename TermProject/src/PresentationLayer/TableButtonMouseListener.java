package PresentationLayer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableButtonMouseListener extends MouseAdapter {
    private final TableGUI gui;

    TableButtonMouseListener(TableGUI gui) {
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable source = (JTable)e.getSource();
        int row = source.rowAtPoint( e.getPoint() );
        int column = source.columnAtPoint( e.getPoint() );
        String colName = gui.getProperties().getColumnName(column);

        if (colName.equals("Edit") || colName.equals("Pay Fee") || colName.equals("Contact Landlord")){
            gui.tableButtonClicked((String)source.getValueAt(row, 0), colName);
        }
    }
}