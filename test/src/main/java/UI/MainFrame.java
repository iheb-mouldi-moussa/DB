package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.util.List;

import DAO.TablesDAO;
import Tables.AllTables;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private TablesDAO tablesDAO;
    private DefaultTableModel defaultTableModel;
    private JTable table;
    private AuthorsFrame authorsFrame;
    private ArticleFrame articleFrame;
    public void init() throws Exception {

        tablesDAO = new TablesDAO();
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(200, 100));
        table.setFillsViewportHeight(true);
        defaultTableModel.addColumn("Tables_in_r17DB");
        List<AllTables> tables = tablesDAO.getAllTables();
        for (AllTables table: tables) {
            defaultTableModel.addRow(new Object[] {table.getName()});
            
        }


        JPanel tablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        tablePanel.add(new JScrollPane(table), gbc);

        JLabel selectJLabel = new JLabel("Select a table");
        gbc.gridx = 0;
        gbc.gridy = 1;
        tablePanel.add(selectJLabel, gbc);

        JButton authorsButton = new JButton("Authors");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 12, 0, 0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        tablePanel.add(authorsButton, gbc);


        authorsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                authorsFrame = new AuthorsFrame();
                try {
                    authorsFrame.init();
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton articlesButton = new JButton("Articles");
        gbc.gridx = 2;
        gbc.gridy = 1;
        tablePanel.add(articlesButton, gbc);
        
        articlesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                articleFrame = new ArticleFrame();
                try {
                    articleFrame.init();
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        
        JButton commentsButton = new JButton("Comments");
        gbc.gridx = 3;
        gbc.gridy = 1;
        tablePanel.add(commentsButton, gbc);

        setLayout(new GridLayout(0, 1, 10, 10));
        add(tablePanel);
        setTitle("R17 DATABASE");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        //pack();
    }

    public static void main(String[] args) throws Exception {
        
        MainFrame mainFrame = new MainFrame();
        mainFrame.init();
    }

}
