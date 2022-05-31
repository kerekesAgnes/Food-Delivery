package presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginButton;

    public LoginView(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 250);
        getContentPane().setLayout(null);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(40, 30, 100, 20);
        getContentPane().add(usernameLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 60, 100, 20);
        getContentPane().add(passwordLabel);

        usernameTextField = new JTextField("");
        usernameTextField.setBounds(150, 30, 100, 18);
        getContentPane().add(usernameTextField);

        passwordTextField = new JTextField("");
        passwordTextField.setBounds(150, 60, 100, 18);
        getContentPane().add(passwordTextField);

        loginButton = new JButton("Login");
        loginButton.setBounds(70, 100, 100, 20);
        getContentPane().add(loginButton);
    }

    public void addLoginButtonListener(ActionListener actionListener){
        this.loginButton.addActionListener(actionListener);
    }

    public String getUsername(){
        return this.usernameTextField.getText();
    }

    public String getPassword(){
        return this.passwordTextField.getText();
    }
}

