package UI;

import javax.swing.JFrame;

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

import DAO.ArticlesDAO;
import Helpers.HelperFrame;
import Tables.Articles;
import javafx.event.Event;
import javafx.scene.control.ButtonType;
import java.awt.event.MouseEvent;

public class ArticleFrame extends JFrame {

    private DefaultTableModel defaultTableModel;
    private JTable table;
    private ArticlesDAO articleDAO;
    private LoginFrame loginFrame;

    public void init() throws Exception {
        articleDAO = new ArticlesDAO();
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel) {
            public String getToolTipText(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());

                Object value = getValueAt(row, column);
                return value == null ? null : value.toString();
            }
        };
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setAutoCreateRowSorter(true);
        // table.setFillsViewportHeight(true);
        // table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

        HelperFrame.updateTableArticles(defaultTableModel, articleDAO);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel consolPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        JLabel id = new JLabel("id");
        HelperFrame.addToPanel(id, gbc, consolPanel, 0, 0);
        JTextField idText = new JTextField(11);
        HelperFrame.addToPanel(idText, gbc, consolPanel, 1, 0);

        JLabel titleLabel = new JLabel("Title");
        HelperFrame.addToPanel(titleLabel, gbc, consolPanel, 0, 1);
        JTextField titleText = new JTextField(11);
        HelperFrame.addToPanel(titleText, gbc, consolPanel, 1, 1);

        JLabel contentLabel = new JLabel("Content");
        HelperFrame.addToPanel(contentLabel, gbc, consolPanel, 0, 2);

        JTextField contentText = new JTextField(11);
        HelperFrame.addToPanel(contentText, gbc, consolPanel, 1, 2);

        JLabel summaryLabel = new JLabel("Summary");
        HelperFrame.addToPanel(summaryLabel, gbc, consolPanel, 0, 3);
        JTextField summaryText = new JTextField(11);
        HelperFrame.addToPanel(summaryText, gbc, consolPanel, 1, 3);

        JLabel authorIdLabel = new JLabel("AuthorID");
        HelperFrame.addToPanel(authorIdLabel, gbc, consolPanel, 0, 4);
        JTextField authorITextField = new JTextField(11);
        HelperFrame.addToPanel(authorITextField, gbc, consolPanel, 1, 4);

        JLabel searchLabel = new JLabel("Search");
        HelperFrame.addToPanel(searchLabel, gbc, consolPanel, 0, 5);

        JTextField searchTextField = new JTextField(30);
        HelperFrame.addToPanel(searchTextField, gbc, consolPanel, 1, 5);

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

                String title = titleText.getText();
                String content = contentText.getText();
                String summary = summaryText.getText();
                String temp = idText.getText();
                String temp2 = authorITextField.getText();
                int id = -1;
                int author_id = -1;

                try {
                    id = Integer.parseInt(temp);
                    author_id = Integer.parseInt(temp2);
                    articleDAO.addArticle(id, title, content, summary, 0, author_id);
                    DefaultTableModel model = new DefaultTableModel();
                    HelperFrame.updateTableArticles(model, articleDAO);
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
                String temp = idText.getText();
                int id = -1;

                try {
                    id = Integer.parseInt(temp);
                    articleDAO.delArticle(id);
                    DefaultTableModel model = new DefaultTableModel();
                    HelperFrame.updateTableArticles(model, articleDAO);
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
    public static void main(String[] args) throws Exception {
        ArticleFrame articleFrame = new ArticleFrame();
        articleFrame.init();
    }

}
