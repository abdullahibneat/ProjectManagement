import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton AddTask;
    private JButton HomeButton;
    private JPanel NavigationPanel;

    private static JFrame frame;

    private int count;
    private JComboBox WorkingTeamMemberComboBox;
    private String WorkingTeamMemeber;
    private JScrollPane BaseScrollPane;

    private Project project;

//    public Projects(){
//        //ADD TASK JOPTION
//        GridLayout layout0x2 = new GridLayout(0,2);
//
//        count = 0;
//        WorkingTeamMemeber = "Team Member #";
//
//        JPanel AddTaskPanel = new JPanel();
//        JTextField TaskNameField = new JTextField(5);
//        JTextField TaskDurationField = new JTextField(5);
//        JCheckBox DependentCheckBox = new JCheckBox("Dependent:");
//        JComboBox ProjectDependentComboBox = new JComboBox();
//        JComboBox WorkingTeamComboBox = new JComboBox();
////        JButton AddWorkingTeamembersButton = new JButton("Add Team Members");
//        JTextField OptionLagField = new JTextField(5);
//
//        AddTaskPanel.setLayout(layout0x2);
////        AddTaskPanel.setPreferredSize(new Dimension(300 ,300));
//        AddTaskPanel.add(new JLabel("Task Name:"));
//        AddTaskPanel.add(TaskNameField);
//        AddTaskPanel.add(new JLabel("Task Duration:"));
//        AddTaskPanel.add(TaskDurationField);
//        AddTaskPanel.add(new JLabel("Lag"));
//        AddTaskPanel.add(OptionLagField);
//        AddTaskPanel.add(DependentCheckBox);
//        AddTaskPanel.add(ProjectDependentComboBox);
//        ProjectDependentComboBox.setEnabled(false);
//
////        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer
////        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer
//
//        AddTaskPanel.add(new JLabel("Working Team:"));
//        AddTaskPanel.add(WorkingTeamComboBox);
//
////        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer
////        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer
//
////        AddTaskPanel.add(AddWorkingTeamembersButton);
////        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer
//
//        BaseScrollPane = new JScrollPane(AddTaskPanel);
//        BaseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        BaseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
////        BaseScrollPane.setPreferredSize(new Dimension(400 ,200));
//        BaseScrollPane.setPreferredSize(new Dimension(400 ,110));
//
//        DependentCheckBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (DependentCheckBox.isSelected()) {
//                    ProjectDependentComboBox.setEnabled(true);
//                    System.out.println("Project is a Dependent");
//                }else {
//                    ProjectDependentComboBox.setEnabled(false);
//                    System.out.println("Project is not a Dependent");
//                }
//
//            }
//        });
//
////        AddWorkingTeamembersButton.addActionListener(new ActionListener() { //  FOR ADDING TEAM MEMBERS
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                count++;
////                WorkingTeamMemberComboBox = new JComboBox();
////                WorkingTeamComboBox.setName(WorkingTeamMemeber + count);
////                AddTaskPanel.add(new JLabel(WorkingTeamMemeber + count));
////                AddTaskPanel.add(WorkingTeamMemberComboBox);
////                AddTaskPanel.revalidate();
////                AddTaskPanel.repaint();
////                System.out.println("Team Member added");
////
////            }
////        });
//
//        AddTask.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(project);
//                int result = JOptionPane.showConfirmDialog(null, BaseScrollPane,
//                        "New Task", JOptionPane.OK_CANCEL_OPTION);
//                if (result == JOptionPane.OK_OPTION) {
//                    if(TaskDurationField.getText().trim().isEmpty() || TaskNameField.getText().trim().isEmpty() || ProjectDependentComboBox.getSelectedIndex() == 0){
//                        System.out.println("Please Fill All Fields");
//                        TaskNameField.setText("");
//                        TaskDurationField.setText("");
//                        ProjectDependentComboBox.setSelectedIndex(0);
//                    }else {
//                        if (DependentCheckBox.isSelected()) {
//                            System.out.println("Project Dependent of:" + ProjectDependentComboBox.getSelectedItem().toString());
//                            System.out.println("Working Team: " + WorkingTeamMemberComboBox.getSelectedItem().toString());
//                            System.out.println("Task Name: " + TaskNameField.getText());
//                            System.out.println("Task Duration: " + TaskDurationField.getText());
//                            System.out.println("Task Lag: " + OptionLagField.getText());
//
//                            project.addTask(TaskNameField.getText().trim(), Integer.parseInt(TaskDurationField.getText().trim()),Integer.parseInt(OptionLagField.getText().trim()),ProjectDependentComboBox.getSelectedItem().toString(),WorkingTeamComboBox.getSelectedItem().toString());
//                        }else{
//                            System.out.println("Task Name: " + TaskNameField.getText());
//                            System.out.println("Task Duration: " + TaskDurationField.getText());
//                            System.out.println("Task Lag: " + OptionLagField.getText());
//                            System.out.println("Working Team: " + WorkingTeamMemberComboBox.getSelectedItem().toString());
//                            project.addTask(TaskNameField.getText().trim(), Integer.parseInt(TaskDurationField.getText().trim()),Integer.parseInt(OptionLagField.getText().trim()),WorkingTeamComboBox.getSelectedItem().toString());
//
//                        }
//
//                    }
//                }else{
//                    System.out.println("CANCEL");
//                }
//            }
//        });
//        HomeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new MainMenu(frame);
//            }
//        });
//    }

    public Projects(JFrame mainFrame,Project currentProject){
        frame = mainFrame;
        project = currentProject;

        //ADD TASK JOPTION
        GridLayout layout0x2 = new GridLayout(0,2);

        count = 0;
        WorkingTeamMemeber = "Team Member #";

        JPanel AddTaskPanel = new JPanel();
        JTextField TaskNameField = new JTextField(5);
        JTextField TaskDurationField = new JTextField(5);
        JCheckBox DependentCheckBox = new JCheckBox("Dependent:");
        JComboBox ProjectDependentComboBox = new JComboBox();
        JComboBox WorkingTeamComboBox = new JComboBox();
//        JButton AddWorkingTeamembersButton = new JButton("Add Team Members");
        JTextField OptionLagField = new JTextField(5);

        AddTaskPanel.setLayout(layout0x2);
//        AddTaskPanel.setPreferredSize(new Dimension(300 ,300));
        AddTaskPanel.add(new JLabel("Task Name:"));
        AddTaskPanel.add(TaskNameField);
        AddTaskPanel.add(new JLabel("Task Duration:"));
        AddTaskPanel.add(TaskDurationField);
        AddTaskPanel.add(new JLabel("Lag"));
        AddTaskPanel.add(OptionLagField);
        AddTaskPanel.add(DependentCheckBox);
        AddTaskPanel.add(ProjectDependentComboBox);
        ProjectDependentComboBox.setEnabled(false);

//        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer
//        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer

        AddTaskPanel.add(new JLabel("Working Team:"));
        AddTaskPanel.add(WorkingTeamComboBox);

//        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer
//        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer

//        AddTaskPanel.add(AddWorkingTeamembersButton);
//        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer

        BaseScrollPane = new JScrollPane(AddTaskPanel);
        BaseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        BaseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//        BaseScrollPane.setPreferredSize(new Dimension(400 ,200));
        BaseScrollPane.setPreferredSize(new Dimension(400 ,110));

        DependentCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DependentCheckBox.isSelected()) {
                    ProjectDependentComboBox.setEnabled(true);
                    System.out.println("Project is a Dependent");
                }else {
                    ProjectDependentComboBox.setEnabled(false);
                    System.out.println("Project is not a Dependent");
                }

            }
        });

//        AddWorkingTeamembersButton.addActionListener(new ActionListener() { //  FOR ADDING TEAM MEMBERS
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                count++;
//                WorkingTeamMemberComboBox = new JComboBox();
//                WorkingTeamComboBox.setName(WorkingTeamMemeber + count);
//                AddTaskPanel.add(new JLabel(WorkingTeamMemeber + count));
//                AddTaskPanel.add(WorkingTeamMemberComboBox);
//                AddTaskPanel.revalidate();
//                AddTaskPanel.repaint();
//                System.out.println("Team Member added");
//
//            }
//        });

        AddTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(project);
                int result = JOptionPane.showConfirmDialog(null, BaseScrollPane,
                        "New Task", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    if(TaskDurationField.getText().trim().isEmpty() || TaskNameField.getText().trim().isEmpty() || ProjectDependentComboBox.getSelectedIndex() == 0){
                        System.out.println("Please Fill All Fields");
                        TaskNameField.setText("");
                        TaskDurationField.setText("");
                        ProjectDependentComboBox.setSelectedIndex(0);
                    }else {
                        if (DependentCheckBox.isSelected()) {
                            System.out.println("Project Dependent of:" + ProjectDependentComboBox.getSelectedItem().toString());
                            System.out.println("Working Team: " + WorkingTeamMemberComboBox.getSelectedItem().toString());
                            System.out.println("Task Name: " + TaskNameField.getText());
                            System.out.println("Task Duration: " + TaskDurationField.getText());
                            System.out.println("Task Lag: " + OptionLagField.getText());

                            project.addTask(TaskNameField.getText().trim(), Integer.parseInt(TaskDurationField.getText().trim()),Integer.parseInt(OptionLagField.getText().trim()),ProjectDependentComboBox.getSelectedItem().toString(),WorkingTeamComboBox.getSelectedItem().toString());
                        }else{
                            System.out.println("Task Name: " + TaskNameField.getText());
                            System.out.println("Task Duration: " + TaskDurationField.getText());
                            System.out.println("Task Lag: " + OptionLagField.getText());
                            System.out.println("Working Team: " + WorkingTeamMemberComboBox.getSelectedItem().toString());
                            project.addTask(TaskNameField.getText().trim(), Integer.parseInt(TaskDurationField.getText().trim()),Integer.parseInt(OptionLagField.getText().trim()),WorkingTeamComboBox.getSelectedItem().toString());

                        }

                    }
                }else{
                    System.out.println("CANCEL");
                }
            }
        });
        HomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu(frame);
            }
        });

        frame.setContentPane(BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        System.out.println("PROJECT.JAVA PROJECT: " + project);
    }

//    public static void main(String[] args) {
//        frame = new JFrame("Projects");
//        frame.setContentPane(new Projects().BasePanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//    }

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
