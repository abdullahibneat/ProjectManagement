import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;

public class Projects extends JFrame {
    private static JFrame frame;
    protected JPanel BasePanel;
    DefaultMutableTreeNode root;
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
    private JComboBox WorkingTeamMemberComboBox;
    private final JScrollPane BaseScrollPane;
    private final ArrayList<DefaultMutableTreeNode> treeNodes = new ArrayList();
    private Map<Task, CriticalCalculations> criticalCalculations;
    private final Project project;
    private CriticalPath criticalPath = CriticalPathKotlin.INSTANCE;


    public Projects(JFrame mainFrame, Project currentProject) {
        System.out.println("projects constr");
        frame = mainFrame;
        project = currentProject;

        ProjectProgressLabel.setText(project.getName());
        DueLabel.setText("");


        // Use Kotlin critical path by default
        criticalCalculations = CriticalPathKotlin.INSTANCE.forwardBackwardPass(project.getTasks());
        int maxTime = 0;
        for (Map.Entry<Task, CriticalCalculations> c : criticalCalculations.entrySet()) {
            int earlyFinish = c.getValue().getEarlyFinish();
            if (earlyFinish > maxTime) maxTime = earlyFinish;
        }
        if (maxTime > 0) TimeLeftLabel.setText("Project duration: " + maxTime + " days");

        // Add tasks to JTree
        populateTree();

        //ADD TASK JOPTION
        GridLayout layout0x2 = new GridLayout(0, 2);

        JPanel AddTaskPanel = new JPanel();
        JTextField TaskNameField = new JTextField(5);
        JTextField TaskDurationField = new JTextField(5);
        JCheckBox DependentCheckBox = new JCheckBox("Dependent:");
        System.out.println("Checked? " + DependentCheckBox.isSelected());
        JComboBox<String> ProjectDependentComboBox = new JComboBox<>(new DefaultComboBoxModel<>());
        ProjectDependentComboBox.addItem("Select a task");

        JTextField OptionLagField = new JTextField("0", 5);

        ProjectProgressLabel.setText("Project Name: " + currentProject.getName());

        for (Task t : currentProject.getTasks()) {
            ProjectDependentComboBox.addItem(t.getName());
        }

        JPanel dependentTasks = new JPanel();

        AddTaskPanel.setLayout(layout0x2);

        AddTaskPanel.add(new JLabel("Task Name:"));
        AddTaskPanel.add(TaskNameField);
        AddTaskPanel.add(new JLabel("Task Duration:"));
        AddTaskPanel.add(TaskDurationField);
        AddTaskPanel.add(new JLabel("Lag"));
        AddTaskPanel.add(OptionLagField);
        AddTaskPanel.add(DependentCheckBox);
        AddTaskPanel.add(ProjectDependentComboBox);
        AddTaskPanel.add(dependentTasks);
        ProjectDependentComboBox.setEnabled(false);

        BaseScrollPane = new JScrollPane(AddTaskPanel);
        BaseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        BaseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        BaseScrollPane.setPreferredSize(new Dimension(400, 110));

        DependentCheckBox.addActionListener(e -> {
            if (DependentCheckBox.isSelected()) {
                ProjectDependentComboBox.setEnabled(true);
                System.out.println("Project is a Dependent");
            } else {
                ProjectDependentComboBox.setEnabled(false);
                System.out.println("Project is not a Dependent");
            }

        });

        final boolean[] deleting = {false};

        ProjectDependentComboBox.addItemListener(e -> {
            if (DependentCheckBox.isSelected() && !deleting[0] && e.getStateChange() == ItemEvent.SELECTED) {
                String selected = e.getItem().toString();
                System.out.println("selected " + selected);
                if (!selected.equals("Select a task")) {
                    deleting[0] = true;
                    ProjectDependentComboBox.removeItemAt(ProjectDependentComboBox.getSelectedIndex());
                    ProjectDependentComboBox.setSelectedIndex(0);
                    deleting[0] = false;
                    JLabel l = new JLabel(selected);
                    l.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            ProjectDependentComboBox.addItem(l.getText());
                            dependentTasks.remove(l);
                            dependentTasks.repaint();
                        }
                    });
                    dependentTasks.add(l);
                    dependentTasks.repaint();
                }
            }
        });

        AddTask.addActionListener(e -> {
            System.out.println(project);
            // Reset ProjectDependentComboBox
            dependentTasks.removeAll();
            ProjectDependentComboBox.removeAllItems();
            ProjectDependentComboBox.addItem("Select a task");
            for (Task t : currentProject.getTasks()) {
                ProjectDependentComboBox.addItem(t.getName());
            }
            int result = JOptionPane.showConfirmDialog(null, BaseScrollPane,
                    "New Task", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (TaskDurationField.getText().trim().isEmpty() || TaskNameField.getText().trim().isEmpty() || OptionLagField.getText().trim().isEmpty()) {
                    System.out.println("Please Fill All Fields");
                    TaskNameField.setText("");
                    TaskDurationField.setText("");
                    OptionLagField.setText("");
                    ProjectDependentComboBox.setSelectedIndex(0);
                    ProjectDependentComboBox.setEnabled(false);
                    DependentCheckBox.setSelected(false);
                } else {
                    if (DependentCheckBox.isSelected() && dependentTasks.getComponents().length > 0) {
                        System.out.println("Task Name: " + TaskNameField.getText());
                        System.out.println("Task Duration: " + TaskDurationField.getText());
                        System.out.println("Task Lag: " + OptionLagField.getText());
                        ArrayList<String> dependencies = new ArrayList<>();
                        for (Component component : dependentTasks.getComponents()) {
                            if (component.getClass() == JLabel.class) {
                                JLabel l = (JLabel) component;
                                dependencies.add(l.getText());
                            }
                        }
                        System.out.println("Project Dependent of:" + dependencies);
                        project.addTask(TaskNameField.getText().trim(), Integer.parseInt(TaskDurationField.getText().trim()), Integer.parseInt(OptionLagField.getText().trim()), dependencies.toArray(new String[0]));
                        populateTree();
                        TaskNameField.setText("");
                        TaskDurationField.setText("");
                        OptionLagField.setText("");
                        ProjectDependentComboBox.setSelectedIndex(0);
                        ProjectDependentComboBox.setEnabled(false);
                        DependentCheckBox.setSelected(false);

                    } else {
                        System.out.println("Task Name: " + TaskNameField.getText());
                        System.out.println("Task Duration: " + TaskDurationField.getText());
                        System.out.println("Task Lag: " + OptionLagField.getText());
                        project.addTask(TaskNameField.getText().trim(), Integer.parseInt(TaskDurationField.getText().trim()), Integer.parseInt(OptionLagField.getText().trim()));
                        populateTree(); // Update tree
                        TaskNameField.setText("");
                        TaskDurationField.setText("");
                        OptionLagField.setText("0");
                    }

                    dependentTasks.removeAll();

                }
            } else {
                System.out.println("CANCEL");
            }
        });
        HomeButton.addActionListener(e -> new MainMenu(frame));

        CriticalPathCalculateButton.addActionListener(e -> {
            if (CriticalPathComboBox.getSelectedIndex() == 1) {
                //CALCULATE KOTLIN
                System.out.println("Kotlin Selected");
                criticalPath = CriticalPathKotlin.INSTANCE;
            } else {
                //CALCULATE SCALA
                System.out.println("Scala Selected");
                criticalPath = CriticalPathScala$.MODULE$;
            }
        });

        frame.setContentPane(BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        System.out.println("PROJECT.JAVA PROJECT: " + project);


    }

    private void runCriticalPath() {
        criticalCalculations = criticalPath.forwardBackwardPass(project.getTasks());
        for (Task t : criticalPath.findCriticalPath(project.getTasks())) setNodeAsCritical(t.getName());
        DefaultTreeModel model = (DefaultTreeModel) TasksTree.getModel();
        model.reload(root); // Reload JTree so tasks are updated
    }

    private void setNodeAsCritical(String name) {
        for (DefaultMutableTreeNode n : treeNodes) {
            if (n.getUserObject().getClass() == Node.class) {
                Node node = (Node) n.getUserObject();
                if (node.getTask().getName().equals(name)) {
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
        runCriticalPath();
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
                        if (criticalCalculations != null) {
                            CriticalCalculations c = criticalCalculations.get(t);
                            if (c != null) {
                                criticalDetails = "\n\n" +
                                        "The earliest this task can start is day " + c.getEarlyStart() + ", and will finish on day " + c.getEarlyFinish() + "\n" +
                                        "The latest this task can start is day " + c.getLateStart() + ", and will finish on day " + c.getLateFinish();

                                if (c.getFloat() != null && c.getFloat() > 0)
                                    criticalDetails += "\nThis task can be started " + c.getFloat() + " day(s) late without affecting the overall duration of this project.";
                            }
                        }
                        for (Task prevt : t.getPreviousTasks()) prevTasks = prevTasks + prevt.getName() + ", ";
                        for (Task nextt : t.getNextTasks()) nextTasks = nextTasks + nextt.getName() + ", ";
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