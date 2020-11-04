package GUI;

import javax.swing.*;

public class MainMenu {
    private JPanel BasePanel;
    private JButton ProjectsAddButton;
    private JButton TeamsAddButton;
    private JPanel GreetingPanel;
    private JPanel ProjectsPanel;
    private JPanel TeamsPanel;
    private JPanel TeamsSubPanel;
    private JPanel ProjectsSubPanel;
    private JLabel ProjectsLabel;
    private JLabel TeamsLabel;
    private JScrollPane ProjectsScrollPane;
    private JScrollPane TeamsScrollPane;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
