import Persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainMenu {
    private static JFrame frame;
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
    private JLabel GreetingLabel;
    private JLabel TimeLabel;
    private JLabel DateLabel;
    private JPanel projectjpanel;
    private JPanel ProjectsScrollPaneBasePanel;
    private JPanel TeamsScrollPaneBasePanel;

    public MainMenu() {
        GridLayout layout0x2 = new GridLayout(0, 2);

        //JPanel for Project Fields
        JPanel projectFieldsPanel = new JPanel();
        JTextField projectTitleField = new JTextField(5);
        JComboBox<Object> teamNames = new JComboBox<>();

        teamNames.addItem("N/A");

        projectFieldsPanel.setLayout(layout0x2);
        projectFieldsPanel.add(new JLabel("Project Title:"));
        projectFieldsPanel.add(projectTitleField);
        projectFieldsPanel.add(new JLabel(("Team:")));
        projectFieldsPanel.add(teamNames);

        for (Team t : Persistence.INSTANCE.getTeams()) {
            teamNames.addItem(t);
        }

        //JPanel for Teams Fields
        JPanel TeamsBasePanel = new JPanel();
        JTextField TeamNameField = new JTextField(5);
        JTextField TeamLeaderField = new JTextField(5);

        TeamsBasePanel.setLayout(layout0x2);
        TeamsBasePanel.add(new JLabel("Team Name:"));
        TeamsBasePanel.add(TeamNameField);
        TeamsBasePanel.add(new JLabel("Team Leader:"));
        TeamsBasePanel.add(TeamLeaderField);


        ProjectsAddButton.addActionListener(e -> {

            int result = JOptionPane.showConfirmDialog(null, projectFieldsPanel,
                    "New Project", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (projectTitleField.getText().trim().isEmpty()) {
                    System.out.println("Please Fill all Fields");
                } else {
                    System.out.println("Project Title: " + projectTitleField.getText());

                    // Try to check if team is selected, otherwise set to null
                    Team team = null;
                    try {
                        team = (Team) teamNames.getSelectedItem();
                    } catch (Exception ignored) {
                    }

                    Project p = new Project(projectTitleField.getText().trim(), team);
                    System.out.println(Persistence.INSTANCE);

                    new Projects(frame, p);
                }
            }

        });
        TeamsAddButton.addActionListener(e -> {

            int result = JOptionPane.showConfirmDialog(null, TeamsBasePanel,
                    "New Team", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {

                if (TeamNameField.getText().trim().isEmpty() || TeamLeaderField.getText().trim().isEmpty()) {
                    System.out.println("Please Fill all Fields");
                    TeamNameField.setText("");
                    TeamLeaderField.setText("");
                } else {
                    System.out.println("Team Name: " + TeamNameField.getText().trim());
                    System.out.println("Team Leader: " + TeamLeaderField.getText().trim());

                    System.out.println(Persistence.INSTANCE);
                    Team t = new Team(TeamNameField.getText().trim());

                    t.addMember(new Member(TeamLeaderField.getText().trim()));
                    System.out.println(Persistence.INSTANCE);


                    new Teams(frame, t);
                }
            } else {
                System.out.println("Cancel");
            }
        });
    }

    public MainMenu(JFrame mainFrame) {
        frame = mainFrame;
        frame.setContentPane(new MainMenu().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        frame = new JFrame("Project Management");
        frame.setContentPane(new MainMenu().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        TimeLabel = new JLabel("");
        DateLabel = new JLabel("");

        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        Timer timer = new Timer(1000, e -> {
            Date date = new Date();
            TimeLabel.setText("Time: " + timeFormat.format(date));
            DateLabel.setText("Date: " + dateFormat.format(date));
        });
        timer.setInitialDelay(0);
        timer.start();


//       //PROJECTS SCROLL PANEL
        ProjectsScrollPaneBasePanel = new JPanel();
        ProjectsScrollPaneBasePanel.setLayout(new GridLayout(1, 0));


        for (Project p : Persistence.INSTANCE.getProjects()) {
            JButton b = new JButton("View");
            b.addActionListener(e -> new Projects(frame, p));

            JPanel projectTile = new JPanel();
            projectTile.setLayout(new GridLayout(0, 1));
            projectTile.setBorder(BorderFactory.createLineBorder(Color.black));

            ProjectsScrollPaneBasePanel.add(projectTile);
            projectTile.add(new JLabel( p.getName()));

            if (p.getTeam() == null) {
                projectTile.add(new JLabel("Team Assigned: " + "N/A"));
            } else {
                projectTile.add(new JLabel("Team Assigned: " + p.getTeam().toString()));
            }

            projectTile.add(b);
        }
        ProjectsScrollPane = new JScrollPane(ProjectsScrollPaneBasePanel);

//      TEAMS SCROLL PANE
        TeamsScrollPaneBasePanel = new JPanel();
        TeamsScrollPaneBasePanel.setLayout(new GridLayout(1, 0));

        for (Team t : Persistence.INSTANCE.getTeams()) {
            JButton b = new JButton("View");
            b.addActionListener(e -> new Teams(frame, t));

            JPanel teamTile = new JPanel();
            teamTile.setLayout(new GridLayout(0, 1));
            teamTile.setBorder(BorderFactory.createLineBorder(Color.black));

            TeamsScrollPaneBasePanel.add(teamTile);
            teamTile.add(new JLabel(t.getName()));
            teamTile.add(new JLabel("Members: " + t.getMembers().size()));
            teamTile.add(b);
        }
        TeamsScrollPane = new JScrollPane(TeamsScrollPaneBasePanel);


    }
}


