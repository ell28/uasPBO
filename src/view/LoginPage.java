package view;

import javax.swing.*;
import controller.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // * FRAME
    public LoginPage() {
        // Set the title of the frame
        setTitle("Login");
        // Set the size of the frame
        setSize(320, 200);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Create a new JPanel
        JPanel panel = new JPanel();
        // Add the panel to the frame
        add(panel);
        // Place the components on the panel
        placeComponents(panel);

        // Center the frame
        setLocationRelativeTo(null);

        // Make the frame visible
        setVisible(true);
    }

    // * COMPONENTS
    private void placeComponents(JPanel panel) {
        // Set the layout manager to null (absolute positioning)
        panel.setLayout(null);

        // Create and position the user label
        JLabel userLabel = new JLabel("Email");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // Create and position the email field
        emailField = new JTextField(20);
        emailField.setBounds(100, 20, 165, 25);
        panel.add(emailField);

        // Create and position the password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        // Create and position the password field
        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        // Create and position the login button
        loginButton = new JButton("Login");
        loginButton.setBounds(160, 110, 80, 25);
        panel.add(loginButton);

        // * LISTENERS
        // Add an action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the username and password from the fields
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate the user
                String authenticator = LoginController.authenticateUser(email, password);
                if (authenticator != null) {
                    // If the user is authenticated, show a welcome message
                    JOptionPane.showMessageDialog(LoginPage.this, "Welcome, " + authenticator + "!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    new GameListView().show();
                    dispose();
                } else {
                    // If the user is not authenticated, show an error message
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}