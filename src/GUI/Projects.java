package GUI;

import javax.swing.*;

public class Projects {
    private JPanel panel1;
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
        frame.setContentPane(new Projects().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
