package UI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class LoginFrame extends JFrame{
    
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);

    JTextField tfEmail;
    JPasswordField pfPassword;

    public void init()
    {
        /************************ TextField *************************/

        JLabel loginLabel = new JLabel("Login Form", SwingConstants.CENTER);
        loginLabel.setFont(mainFont);

        JLabel emailJLabel = new JLabel("Email");
        emailJLabel.setFont(mainFont);
        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(mainFont);
        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));

        formPanel.add(loginLabel);
        formPanel.add(emailJLabel);
        formPanel.add(tfEmail);
        formPanel.add(passLabel);
        formPanel.add(pfPassword);



        /************** Buttons Panel **********************/        

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        
        JButton btncancel = new JButton("Cancel");
        btncancel.setFont(mainFont);
        btncancel.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
            
        });

        JButton btnlogin = new JButton("Login");
        btnlogin.setFont(mainFont);
        btnlogin.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());
                if(email.equals("R17user") && password.equals("R17"))
                {
                    MainFrame mainFrame = new MainFrame();
                    try {
                        mainFrame.init();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Check Creds", 
                    "Try Again", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        buttonsPanel.add(btncancel);
        buttonsPanel.add(btnlogin);


        /**************  Init the Login Frame **********************/        
        add(formPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
        setTitle("R17DB");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.init();
    }
}
