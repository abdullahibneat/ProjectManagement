package GUI;

import javax.swing.*;

public class Teams {
    private JPanel panel1;
    private JTree tree1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Teams");
        frame.setContentPane(new Teams().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
