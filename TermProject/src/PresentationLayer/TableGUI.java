package PresentationLayer;

import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public abstract class TableGUI implements GUI {
    private JTable properties;
    private JScrollPane scroll;
    private JPanel panel;
    protected String[] headers;

    public abstract void updateView();
    public abstract void close();
    public abstract void tableButtonClicked(int row, String colName, String dialogTitle);


    public JTable getProperties() {
        return properties;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    protected void showTable(String [] headers, String response, JPanel panel) {
        setPanel(panel);
        String[] temp = response.split(";");
        String[][] data = new String[temp.length][headers.length];
        for (int i = 0; i < temp.length; i++) {
            String[] temp2 = temp[i].split("/");
            for (int j = 0; j < temp2.length; j++) {
                data[i][j] = temp2[j];
            }
            if (headers[headers.length-1] == "Edit"){
                data[i][headers.length - 1] = "Edit";
                if (headers[headers.length - 2] == "Pay Fee"){
                    data[i][headers.length - 1] = "Pay Fee";
                }
            } else if (headers[headers.length-1] == "Contact Landlord"){
                data[i][headers.length - 1] = "Contact Landlord";
            }

        }

        TableModel model = new DefaultTableModel(data, headers) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        properties = new JTable(model);
        properties = new JTable(data, headers);
        TableButtonRenderer buttonRenderer = new TableButtonRenderer();
        if (headers[headers.length-1] == "Edit"){
            properties.getColumn("Edit").setCellRenderer(buttonRenderer);
            properties.addMouseListener(new TableButtonMouseListener(this));
        } else {
            properties.getColumn("More Info").setCellRenderer(buttonRenderer);
            properties.addMouseListener(new TableButtonMouseListener(this));
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
