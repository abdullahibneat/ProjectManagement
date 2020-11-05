package GUI;

import javax.swing.*;

public class Login {
    private JPanel BasePanel;
    private JTextField UsernameTextField;
    private JPasswordField PasswordField;
    private JButton LoginButton;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JLabel ApplicationTitleLabel;
    private JPanel CedentialsPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginButton");
        frame.setContentPane(new Login().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
