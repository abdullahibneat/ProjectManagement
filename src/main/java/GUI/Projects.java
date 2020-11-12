package GUI;

import javax.swing.*;

public class Projects {
    private JPanel BasePanel;
    private JTree TasksTree;
    private JPanel TasksPanel;
    private JPanel TasksSubPanel;
    private JPanel TopPanel;
    private JLabel ProjectProgressLabel;
    private JLabel TimeLeftLabel;
    private JLabel DueLabel;
    private JLabel TasksLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Projects");
        frame.setContentPane(new Projects().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
