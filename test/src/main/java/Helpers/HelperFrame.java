package Helpers;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.AuthorsDAO;
import Tables.Authors;

import java.awt.GridBagConstraints;
import java.util.List;

public class HelperFrame {
    
    public static void addToPanel(JComponent component, GridBagConstraints gbc, JPanel panel, int x, int y)
    {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
    }

    public static void addDynamicSearch(JTable table, JTextField textField)
    {

                String str = textField.getText();
                TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
                table.setRowSorter(sort);
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);
                } else {
                    // (?i) means case insensitive search
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
    }


    public static void updateTable(JTable table, DefaultTableModel defaultTableModel, AuthorsDAO authorsDAO) throws Exception
    {
        defaultTableModel.addColumn("id");
        defaultTableModel.addColumn("FirstName");
        defaultTableModel.addColumn("LastName");
        defaultTableModel.addColumn("Email");

        List<Authors> authors = authorsDAO.getAllAuthors();
        for (Authors author : authors) {
            defaultTableModel.addRow(new Object[] { author.getId(), author.getFirstname(),
                    author.getLastname(), author.getMail() });
        }
    }
}
