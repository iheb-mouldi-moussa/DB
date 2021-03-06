package UI;

import java.awt.Dimension;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.table.DefaultTableModel;

import DAO.AuthorsDAO;
import Tables.Authors;
import javafx.scene.control.ButtonType;

public class AuthorsFrame extends JFrame{

    private DefaultTableModel defaultTableModel;
    private JTable table;
    private AuthorsDAO authorsDAO;

    public void init() throws Exception
    {
        authorsDAO = new AuthorsDAO();
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        //table.setFillsViewportHeight(true);
        //table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        defaultTableModel.addColumn("id");
        defaultTableModel.addColumn("FirstName");
        defaultTableModel.addColumn("LastName");
        defaultTableModel.addColumn("Email");

        List<Authors> authors = authorsDAO.getAllAuthorss();
        for (Authors author : authors) {
            defaultTableModel.addRow(new Object[]{author.getId(), author.getFirstname(),
            author.getLastname(), author.getMail()});
        }

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel consolPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        JLabel id = new JLabel("id");
        gbc.gridx = 0;
        gbc.gridy = 0;
        consolPanel.add(id, gbc);
        JTextField idText = new JTextField(11);
        gbc.gridx = 1;
        gbc.gridy = 0;
        consolPanel.add(idText, gbc);

        JLabel firstNameLabel = new JLabel("Firstname");
        gbc.gridx = 0;
        gbc.gridy = 1;
        consolPanel.add(firstNameLabel, gbc);
        JTextField firstNameText = new JTextField(11);
        gbc.gridx = 1;
        gbc.gridy = 1;
        consolPanel.add(firstNameText, gbc);

        JLabel lastNameLabel = new JLabel("Lastname");
        gbc.gridx = 0;
        gbc.gridy = 2;
        consolPanel.add(lastNameLabel, gbc);
        JTextField lastNameText = new JTextField(11);
        gbc.gridx = 1;
        gbc.gridy = 2;
        consolPanel.add(lastNameText, gbc);

        JLabel emailLabel = new JLabel("Email");
        gbc.gridx = 0;
        gbc.gridy = 3;
        consolPanel.add(emailLabel, gbc);
        JTextField emailText = new JTextField(11);
        gbc.gridx = 1;
        gbc.gridy = 3;
        consolPanel.add(emailText, gbc);
        

        JButton addButton = new JButton("Add");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 12, 0, 0);
        gbc.gridx = 2;
        gbc.gridy = 0;
        consolPanel.add(addButton, gbc);
       

        JButton delButton = new JButton("Delete");
        gbc.gridx = 2;
        gbc.gridy = 1;
        consolPanel.add(delButton, gbc);
        
        JButton searchButton = new JButton("Search");
        gbc.gridx = 2;
        gbc.gridy = 2;
        consolPanel.add(searchButton, gbc);

        JButton exitButton = new JButton("Exit");
        gbc.gridx = 2;
        gbc.gridy = 3;
        consolPanel.add(exitButton, gbc);
        
        
        /*
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setLayout(new GridLayout(4, 1));
        textPanel.add(idText);
        textPanel.add(firstNameText);
        textPanel.add(lastNameText);
        textPanel.add(emailText);
        textPanel.setPreferredSize(new Dimension(10, 10));  
        */


        /*JPanel buttoPanel = new JPanel(new BorderLayout());
        buttoPanel.setLayout(new GridLayout(3, 0));
        buttoPanel.add(addButton);
        buttoPanel.add(delButton);
        buttoPanel.add(exitButton);
        buttoPanel.setPreferredSize(new Dimension(10, 10));
        */

        setLayout(new GridLayout(0, 2, 10, 10));
        add(tablePanel);
        //add(textPanel);
        //add(buttoPanel);
        add(consolPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(1000, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        validate();
        pack();
    }

    public static void main(String[] args) {
        AuthorsFrame authorsFrame = new AuthorsFrame();
        try
        {
        authorsFrame.init();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
