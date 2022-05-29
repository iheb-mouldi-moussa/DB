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

    public void init() throws Exception {

        tablesDAO = new TablesDAO();
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(200, 200));
        table.setFillsViewportHeight(true);
        defaultTableModel.addColumn("Tables_in_r17DB");
        List<AllTables> tables = tablesDAO.getAllTables();
        for (AllTables table: tables) {
            defaultTableModel.addRow(new Object[] {table.getName()});
            
        }

        JTextField choice = new JTextField("Choice");
        choice.setPreferredSize(new Dimension(10, 10));
        JLabel choiceLabel = new JLabel("Select a table from this list : Authors, Articles, Comments",
         SwingConstants.RIGHT);
        JPanel choicePannel = new JPanel();
        choicePannel.add(choiceLabel);
        choicePannel.add(choice);
        choicePannel.setLayout(new GridLayout(1, 0));

        JButton chooseButton = new JButton("Choose");
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new GridLayout(1,0));
        panelButton.add(chooseButton);
        
        chooseButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String table = choice.getText();
                switch (table)
                {
                    case "Authors":
                        AuthorsFrame authorsFrame = new AuthorsFrame();
                        try {
                            authorsFrame.init();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dispose();
                        break;
                /*    case "Articles":
                        ArticlesFrame articlesFrame = new ArticlesFrame();
                        articlesFrame.init();
                        dispose();
                        break;
                    case "Comments":
                        CommentsFrame commentsFrame = new CommentsFrame();
                        commentsFrame.init();
                        dispose();
                        break; */
                    default:
                        JOptionPane.showMessageDialog(MainFrame.this, "Check Table Name", 
                        "Try Again", JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
        });

        setLayout(new GridBagLayout());
        add(new JScrollPane(table));
        add(choicePannel);
        add(panelButton);
        setTitle("R17 DATABASE");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1150, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        
        MainFrame mainFrame = new MainFrame();
        mainFrame.init();
    }

}
