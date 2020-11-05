package GUI;

import javax.swing.*;

public class MainMenu {
    private JPanel BasePanel;
    private JPanel GreetingPanel;
    private JPanel ProjectsPanel;
    private JPanel TeamsPanel;
    private JButton ProjectsAddButton;
    private JButton TeamsAddButton;
    private JScrollPane ProjectsScrollPane;
    private JLabel ProjectsLabel;
    private JPanel ProjectsSubPanel;
    private JPanel TeamsSubPanel;
    private JLabel TeamsLabel;
    private JScrollPane TeamsScrollPane;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
