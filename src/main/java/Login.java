//import GUI.Testing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel BasePanel;
    private JTextField UsernameTextField;
    private JPasswordField PasswordField;
    private JButton LoginButton;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JLabel ApplicationTitleLabel;
    private JPanel CedentialsPanel;

    private static JFrame frame;

//    private Testing t = new Testing(); // *TESTING*

    public Login() {
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu(frame); // *NEEDS TO OPEN UNDER THE CONDITIONS THAT THE USERNAME & PASSWORD ARE CORRECT*

//                t.setContentPane(new Testing().panel1); //--------------------|
//                t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //          |
//                t.pack(); //                                                  | *TESTING FOR NEW WINDOW POPUP*  EXTENDS JFRAME
//                t.setVisible(true); //----------------------------------------|



            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("LoginButton");
        frame.setContentPane(new Login().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

}
