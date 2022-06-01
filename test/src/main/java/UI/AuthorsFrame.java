package UI;

import java.awt.Dimension;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.AuthorsDAO;
import Helpers.HelperFrame;
import Tables.Authors;
import javafx.event.Event;
import javafx.scene.control.ButtonType;

public class AuthorsFrame extends JFrame {

    private DefaultTableModel defaultTableModel;
    private JTable table;
    private AuthorsDAO authorsDAO;
    private LoginFrame loginFrame;
    public void init() throws Exception {
        authorsDAO = new AuthorsDAO();
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setAutoCreateRowSorter(true);
        // table.setFillsViewportHeight(true);
        // table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

        HelperFrame.updateTable(table, defaultTableModel, authorsDAO);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel consolPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        JLabel id = new JLabel("id");
        HelperFrame.addToPanel(id, gbc, consolPanel, 0, 0);
        JTextField idText = new JTextField(11);
        HelperFrame.addToPanel(idText, gbc, consolPanel, 1, 0);

        JLabel firstNameLabel = new JLabel("Firstname");
        HelperFrame.addToPanel(firstNameLabel, gbc, consolPanel, 0, 1);
        JTextField firstNameText = new JTextField(11);
        HelperFrame.addToPanel(firstNameText, gbc, consolPanel, 1, 1);
            

        JLabel lastNameLabel = new JLabel("Lastname");
        HelperFrame.addToPanel(lastNameLabel, gbc, consolPanel, 0, 2);

        JTextField lastNameText = new JTextField(11);
        HelperFrame.addToPanel(lastNameText, gbc, consolPanel, 1, 2);

        JLabel emailLabel = new JLabel("Email");
        HelperFrame.addToPanel(emailLabel, gbc, consolPanel, 0, 3);
        JTextField emailText = new JTextField(11);
        HelperFrame.addToPanel(emailText, gbc, consolPanel, 1, 3);

        JLabel searchLabel = new JLabel("Search");
        HelperFrame.addToPanel(searchLabel, gbc, consolPanel, 0, 4);
        
        JTextField searchTextField = new JTextField(30);
        HelperFrame.addToPanel(searchTextField, gbc, consolPanel, 1, 4);

        searchTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {

            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {

                HelperFrame.addDynamicSearch(table, searchTextField);

            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                HelperFrame.addDynamicSearch(table, searchTextField);
            }

        });

        JButton addButton = new JButton("Add");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 12, 0, 0);
        HelperFrame.addToPanel(addButton, gbc, consolPanel, 2, 0);
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String temp = idText.getText();
                String email = emailText.getText();
                int id = -1;

                try {
                    id = Integer.parseInt(temp);
                    authorsDAO.addAuthor(id, firstName, lastName, email);
                    DefaultTableModel model = new DefaultTableModel();
                    HelperFrame.updateTable(table, model, authorsDAO);
                    table.setModel(model);
 
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        JButton delButton = new JButton("Delete");
        HelperFrame.addToPanel(delButton, gbc, consolPanel, 2, 1);
        delButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String temp = idText.getText();
                String email = emailText.getText();
                int id = 0;

                try {
                    id = Integer.parseInt(temp);
                    authorsDAO.delAuthor(id, firstName, lastName, email);
                    DefaultTableModel model = new DefaultTableModel();
                    HelperFrame.updateTable(table, model, authorsDAO);
                    table.setModel(model);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        JButton backButton = new JButton("Back");
        HelperFrame.addToPanel(backButton, gbc, consolPanel, 2, 2);
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                MainFrame mainFrame = new MainFrame();
                try {
                    mainFrame.init();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        JButton exitButton = new JButton("Exit");
        HelperFrame.addToPanel(exitButton, gbc, consolPanel, 2, 3);

        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                loginFrame = new LoginFrame();
                loginFrame.init();
            }
        });

        setLayout(new GridLayout(0, 2, 10, 40));
        add(tablePanel);
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
        try {
            authorsFrame.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
