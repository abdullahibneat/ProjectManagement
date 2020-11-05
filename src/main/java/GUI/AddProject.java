package GUI;  // LOOKING TO INHERIT POPUPMENU IN THE PROPERTIES (NOT DONE YET)

import javax.swing.*;

public class AddProject {
    private JPanel BasePanel;
    private JButton newProjectButton;
    private JButton importProjectButton;
    private JLabel SelectionLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddProject");
        frame.setContentPane(new AddProject().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
