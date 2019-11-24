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


    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        int column = source.columnAtPoint( evt.getPoint() );
        String s=source.getModel().getValueAt(row, column)+"";

        JOptionPane.showMessageDialog(null, s);


    }

    @Override
    public void mouseClicked(MouseEvent e) {

//        int column = gui.getProperties().getColumnModel().getColumnIndexAtX(e.getX());
//        int row    = e.getY()/gui.getProperties().getRowHeight();
//
//        if (row < gui.getProperties().getRowCount() && row >= 0 && column < gui.getProperties().getColumnCount() && column >= 0) {
//            Object value = gui.getProperties().getValueAt(row, column);
//            if (value instanceof JButton) {
//                ((JButton)value).doClick();
//            }
//        }

        JTable source = (JTable)e.getSource();
        int row = source.rowAtPoint( e.getPoint() );
        int column = source.columnAtPoint( e.getPoint() );
        String colName = gui.getProperties().getColumnName(column);

        if (colName =="Edit" || colName =="Pay Fee" || colName == "Contact Landlord"){
            gui.tableButtonClicked((String)source.getValueAt(row, 0), colName);
        }

    }
//        gui.tableButtonClicked();
}