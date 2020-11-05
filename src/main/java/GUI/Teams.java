package GUI;

import javax.swing.*;

public class Teams {
    private JPanel BasePanel;
    private JList TeamsList;
    private JPanel TeamsPanel;
    private JPanel TeamsSubPanel;
    private JPanel TopPanel;
    private JLabel TeamLabel;
    private JLabel TeamLeaderLabel;
    private JLabel TaskLabel;
    private JLabel MemebersLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Teams");
        frame.setContentPane(new Teams().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}