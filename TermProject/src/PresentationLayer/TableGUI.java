package PresentationLayer;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public abstract class TableGUI implements GUI {
    private JTable properties;
    private JScrollPane scroll;
    String[] headers;
    String[][] data;
    DefaultTableModel model;

    public abstract void updateView();
    public abstract void close();
    public abstract void tableButtonClicked(String propertyId, String colName);

    TableGUI(){
        model = new DefaultTableModel();
        properties = new JTable();

    }

    public JTable getProperties() {
        return properties;
    }

    void showTable(String [] headers, String response, JPanel panel) {
        
        String[] temp = response.split(";");
        data = null;
        data = new String[temp.length][headers.length];
        for (int i = 0; i < temp.length; i++) {
            String[] temp2 = temp[i].split("/");
            for (int j = 0; j < temp2.length; j++) {
                data[i][j] = temp2[j];
            }
            if (headers[headers.length-1].equals("Edit")){
                data[i][headers.length - 1] = "Edit";
                if (headers[headers.length - 2].equals("Pay Fee")){
                    data[i][headers.length - 2] = "Pay Fee";
                }
            } else if (headers[headers.length-1].equals("Contact Landlord")){
                data[i][headers.length - 1] = "Contact Landlord";
            }

        }

//        model.getDataVector().removeAllElements();
//        model.fireTableDataChanged();

        model.setColumnIdentifiers(headers);

        for (int i = model.getRowCount() - 1; i > 0; i--){
            model.removeRow(i);
        }

        model.setRowCount(0);
        for (int i = 0; i < data.length; i++){
            model.addRow(data[i]);
        }

//        model.setDataVector(data, headers);
        properties.setModel(model);
//        properties.revalidate();

//        JTable newData = new JTable(data, headers);
        properties.revalidate();


        TableButtonRenderer buttonRenderer = new TableButtonRenderer();
        if (headers[headers.length-1].equals("Edit")){
            properties.getColumn("Edit").setCellRenderer(buttonRenderer);
            properties.addMouseListener(new TableButtonMouseListener(this));
            if (headers[headers.length-2].equals("Pay Fee")){
                properties.getColumn("Pay Fee").setCellRenderer(buttonRenderer);
                properties.addMouseListener(new TableButtonMouseListener(this));
            }
        } else {
            properties.getColumn("Contact Landlord").setCellRenderer(buttonRenderer);
            properties.addMouseListener(new TableButtonMouseListener(this));
        }

        properties.setEnabled(false);
        if (scroll != null)
            panel.remove(scroll);
        scroll = new JScrollPane(properties);
        panel.add(scroll);
        panel.validate();

    }
}
