import Persistence.Persistence;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private JTextPane TaskDetails;
    private JComboBox CriticalPathComboBox;
    private JLabel Criticalpathlabel;
    private JButton CriticalPathCalculateButton;

    private static JFrame frame;

    private int count;
    private JComboBox WorkingTeamMemberComboBox;
    private String WorkingTeamMemeber;
    private JScrollPane BaseScrollPane;

    private ArrayList<DefaultMutableTreeNode> treeNodes = new ArrayList();
    private Map<Task, CriticalCalculations> criticalCalculations;
    private Project project;
    DefaultMutableTreeNode root;

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
        System.out.println("projects constr");
        frame = mainFrame;
        project = currentProject;

        ProjectProgressLabel.setText(project.getName());

        // Use Kotlin critical path by default
        criticalCalculations = CriticalPathKotlin.INSTANCE.forwardBackwardPass(project.getTasks());
        int maxTime = 0;
        for (Map.Entry<Task, CriticalCalculations> c: criticalCalculations.entrySet()) {
            int earlyFinish = c.getValue().getEarlyFinish();
            if(earlyFinish > maxTime) maxTime = earlyFinish;
        }
        if(maxTime > 0) TimeLeftLabel.setText("Project duration: " + maxTime + " days");

        // Add tasks to JTree
        populateTree();

        //ADD TASK JOPTION
        GridLayout layout0x2 = new GridLayout(0,2);

        count = 0;
        WorkingTeamMemeber = "Team Member #";

        JPanel AddTaskPanel = new JPanel();
        JTextField TaskNameField = new JTextField(5);
        JTextField TaskDurationField = new JTextField(5);
        JCheckBox DependentCheckBox = new JCheckBox("Dependent:");
        System.out.println("Checked? "+  DependentCheckBox.isSelected());
        JComboBox<String> ProjectDependentComboBox = new JComboBox<>();
//        JComboBox WorkingTeamComboBox = new JComboBox();
//        JButton AddWorkingTeamembersButton = new JButton("Add Team Members");
        JTextField OptionLagField = new JTextField(5);

        ProjectProgressLabel.setText("Project Name: " + currentProject.getName());

        for (Task t: currentProject.getTasks()){
            ProjectDependentComboBox.addItem(t.getName());
        }



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

//        AddTaskPanel.add(new JLabel("Working Team:"));
//        AddTaskPanel.add(WorkingTeamComboBox);

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
                    if(TaskDurationField.getText().trim().isEmpty() || TaskNameField.getText().trim().isEmpty() || OptionLagField.getText().trim().isEmpty() ){
                        System.out.println("Please Fill All Fields");
                        TaskNameField.setText("");
                        TaskDurationField.setText("");
                        OptionLagField.setText("");
                        ProjectDependentComboBox.setSelectedIndex(0);
                        ProjectDependentComboBox.setEnabled(false);
                        DependentCheckBox.setSelected(false);
                    }else {
                        if (DependentCheckBox.isSelected() && ProjectDependentComboBox.getSelectedItem() != null) {
                            System.out.println("Task Name: " + TaskNameField.getText());
                            System.out.println("Task Duration: " + TaskDurationField.getText());
                            System.out.println("Task Lag: " + OptionLagField.getText());
                            System.out.println("Project Dependent of:" + ProjectDependentComboBox.getSelectedItem());
                            project.addTask(TaskNameField.getText().trim(), Integer.parseInt(TaskDurationField.getText().trim()),Integer.parseInt(OptionLagField.getText().trim()),ProjectDependentComboBox.getSelectedItem().toString());
                            populateTree();
                            TaskNameField.setText("");
                            TaskDurationField.setText("");
                            OptionLagField.setText("");
                            ProjectDependentComboBox.setSelectedIndex(0);
                            ProjectDependentComboBox.setEnabled(false);
                            DependentCheckBox.setSelected(false);

                        }else{
                            System.out.println("Task Name: " + TaskNameField.getText());
                            System.out.println("Task Duration: " + TaskDurationField.getText());
                            System.out.println("Task Lag: " + OptionLagField.getText());
                            project.addTask(TaskNameField.getText().trim(), Integer.parseInt(TaskDurationField.getText().trim()),Integer.parseInt(OptionLagField.getText().trim()));
                            populateTree(); // Update tree
                            TaskNameField.setText("");
                            TaskDurationField.setText("");
                            OptionLagField.setText("");
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

        CriticalPathCalculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CriticalPathComboBox.getSelectedIndex() == 1){
                    //CALCULATE KOTLIN
                    System.out.println("Kotlin Selected");
                    criticalCalculations = CriticalPathKotlin.INSTANCE.forwardBackwardPass(project.getTasks());
                    for (Task t : CriticalPathKotlin.INSTANCE.findCriticalPath(project.getTasks())) {
                        System.out.println(t.getName() + " is critical");
                        setNodeAsCritical(t.getName());
                    }
                }else{
                    //CALCULATE SCALA
                    System.out.println("Scala Selected");
                    criticalCalculations = CriticalPathScala.forwardBackwardPass(project.getTasks());
                    for (Task t : CriticalPathScala.findCriticalPath(project.getTasks())) {
                        System.out.println(t.getName() + " is critical");
                        setNodeAsCritical(t.getName());
                    }
                }
                DefaultTreeModel model = (DefaultTreeModel) TasksTree.getModel();
                model.reload(root); // Reload JTree so tasks are updated
            }
        });

        frame.setContentPane(BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        System.out.println("PROJECT.JAVA PROJECT: " + project);


    }

    private void setNodeAsCritical(String name) {
        for(DefaultMutableTreeNode n: treeNodes) {
            if(n.getUserObject().getClass() == Node.class) {
                Node node = (Node) n.getUserObject();
                if(node.getTask().getName().equals(name)) {
                    node.setCritical(true);
                }
            }
        }
    }

    private DefaultMutableTreeNode populateTree(Task currentTask, DefaultMutableTreeNode currentNode) {
        currentTask.getNextTasks().forEach(t -> currentNode.add(populateTree(t, new DefaultMutableTreeNode(new Node(t, false)))));
        treeNodes.add(currentNode);
        return currentNode;
    }

    private void populateTree() {
        root.removeAllChildren();
        // Add nodes to JTree
        // Project can have multiple starting nodes, so add each of them to JTree
        project.getTasks().stream().filter(t -> t.getPreviousTasks().isEmpty()).forEach(t -> root.add(populateTree(t, new DefaultMutableTreeNode(new Node(t, false)))));
        DefaultTreeModel model = (DefaultTreeModel) TasksTree.getModel();
        model.reload(root); // Reload JTree so tasks are displayed
    }

    private void createUIComponents() {
        root = new DefaultMutableTreeNode("Tasks");
        TasksTree = new JTree(root);
        TasksTree.setCellRenderer(new CustomCellRenderer());
        TasksTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) TasksTree.getLastSelectedPathComponent();
                    if (node != null && node.getUserObject().getClass() == Node.class) {
                        Node n = (Node) node.getUserObject();
                        Task t = n.getTask();
                        System.out.println("Double-clicked on task \"" + t.getName() + "\" with duration " + t.getDuration());
                        String prevTasks = "[";
                        String nextTasks = "[";
                        String criticalDetails = "";
                        if(criticalCalculations != null) {
                            CriticalCalculations c = criticalCalculations.get(t);
                            if(c != null) {
                                criticalDetails = "\n\n" +
                                        "The earliest this task can start is day " + c.getEarlyStart() + ", and will finish on day " + c.getEarlyFinish() + "\n" +
                                        "The latest this task can start is day " + c.getLateStart() + ", and will finish on day " + c.getLateFinish();

                                if(c.getFloat() != null && c.getFloat() > 0) criticalDetails += "\nThis task can be started " + c.getFloat() + " day(s) late without affecting the overall duration of this project.";
                            }
                        }
                        for(Task prevt: t.getPreviousTasks()) prevTasks = prevTasks + prevt.getName() + ", ";
                        for(Task nextt: t.getNextTasks()) nextTasks = nextTasks + nextt.getName()  + ", ";
                        prevTasks = prevTasks + "]";
                        nextTasks = nextTasks + "]";
                        TaskDetails.setText("Name: " + t.getName() + "\n" +
                                "Duration: " + t.getDuration() + "\n" +
                                "Lag: " + t.getLag() + "\n" +
                                "Previous Tasks: " + prevTasks + "\n" +
                                "Next Tasks: " + nextTasks +
                                criticalDetails);
                    }
                }
            }
        });
    }
}