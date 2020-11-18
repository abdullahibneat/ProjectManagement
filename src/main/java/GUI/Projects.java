package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Projects extends JFrame{
    protected JPanel BasePanel;
    private JTree TasksTree;
    private JPanel TasksPanel;
    private JPanel TasksSubPanel;
    private JPanel TopPanel;
    private JLabel ProjectProgressLabel;
    private JLabel TimeLeftLabel;
    private JLabel DueLabel;
    private JLabel TasksLabel;

    private static JFrame frame;

    ProjectFields pf = new ProjectFields();

    public Projects(){
        String a = pf.t;
        String b = pf.d;
        ProjectProgressLabel.setText(pf.t);
        TimeLeftLabel.setText(pf.d);

    }

    public Projects(JFrame frame){
        frame.setContentPane(new Projects().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        frame = new JFrame("Projects");
        frame.setContentPane(new Projects().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tasks");
        DefaultMutableTreeNode testing1 = new DefaultMutableTreeNode("Critical Path");
        DefaultMutableTreeNode testing2 = new DefaultMutableTreeNode("Non-Critical Path");
        DefaultMutableTreeNode testing3 = new DefaultMutableTreeNode("CR TASK");
        DefaultMutableTreeNode testing4 = new DefaultMutableTreeNode("NON-CR TASK");

        root.add(testing1);
        root.add(testing2);
        testing2.add(testing3);
        testing2.add(new DefaultMutableTreeNode("CR TASK 2"));
        testing1.add(testing4);


        TasksTree = new JTree(root);

//        ProjectProgressLabel = new JLabel("");
//        TimeLeftLabel = new JLabel("");
//        DueLabel = new JLabel("");
//
//        DueLabel.setText("Due: " + "02/08/1892");
//        TimeLeftLabel.setText("Hello cdsfsfdsg"); // no change
//        ProjectProgressLabel.setText("Hidgffdsgdsg");


    }
}
