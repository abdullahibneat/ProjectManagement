package GUI;

import javax.swing.*;

public class MainMenu extends JFrame {

    private JPanel Base;
    private JPanel Panel_Greeting;
    private JPanel Panel_Project_Title;
    private JPanel Panel_Projects;
    private JPanel Panel_Teams_Title;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main_Menu");
        frame.setContentPane(new MainMenu().Base);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel Panel_Teams;
}
