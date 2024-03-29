import Persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Teams {
    private static JFrame frame;
    private JPanel BasePanel;
    private JTextArea TeamsList;
    private JPanel TeamsPanel;
    private JPanel TeamsSubPanel;
    private JPanel TopPanel;
    private JLabel TeamLabel;
    private JLabel TeamLeaderLabel;
    private JLabel TaskLabel;
    private JLabel MembersLabel;
    private JButton button1;
    private JButton HomeButton;
    private JPanel NavigationPanel;
    private int count;
    private JTextField TeamMemberField;
    private final String TeamMember;
    private final JScrollPane BaseScrollPane;
    private final ArrayList<JTextField> newmembers = new ArrayList<>();

    private final Team team;


    public Teams(JFrame mainFrame, Team currentTeam) {
        frame = mainFrame;
        team = currentTeam;

        for (Member m : currentTeam.getMembers()) {
            TeamsList.append(m.getName() + "\n");
        }

        //ADD TEAM MEMBERS JOPTION
        GridLayout layout0x2 = new GridLayout(0, 2);

        count = currentTeam.getMembers().size();
        TeamMember = "Team Member #";

        JPanel AddTeamMemebersPannel = new JPanel();
        JButton AddTeamMembersButton = new JButton("Add Team Members");

        AddTeamMemebersPannel.setLayout(layout0x2);

        MembersLabel.setText("Members: " + currentTeam.getMembers().size());

        TeamLabel.setText("Team Name: " + currentTeam.getName());

        TeamLeaderLabel.setText("Team Leader: " + currentTeam.getMembers().get(0).getName());

        System.out.println("Team Leader Set" + "\n" + "count:" + count);

        TaskLabel.setText("");

        AddTeamMemebersPannel.add(AddTeamMembersButton);
        AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer

        TeamsList.setEditable(false);


        BaseScrollPane = new JScrollPane(AddTeamMemebersPannel);
        BaseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        BaseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        BaseScrollPane.setPreferredSize(new Dimension(400, 80));

        AddTeamMembersButton.addActionListener(e -> {
            count++;
            TeamMemberField = new JTextField();
            TeamMemberField.setName(TeamMember + count);
            AddTeamMemebersPannel.add(new JLabel(TeamMember + count));
            AddTeamMemebersPannel.add(TeamMemberField);
            AddTeamMemebersPannel.revalidate();
            AddTeamMemebersPannel.repaint();
            System.out.println("New Member Field Added");
            newmembers.add(TeamMemberField);

        });

        button1.addActionListener(e -> {
            System.out.println(team);
            int result = JOptionPane.showConfirmDialog(frame, BaseScrollPane, "Edit Team", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    for (JTextField field: newmembers) {
                        System.out.println(field.getText());
                        team.addMember(new Member(field.getText().trim()));
                        TeamsList.append(field.getText() + "\n");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } finally {
                    // Update number of members
                    MembersLabel.setText("Members: " + team.getMembers().size());

                    // Reset input fields
                    newmembers.clear();
                    for (Component c : AddTeamMemebersPannel.getComponents()) {
                        if (c.getClass() == JLabel.class || c.getClass() == JTextField.class)
                            AddTeamMemebersPannel.remove(c);
                    }
                    count = 0;
                }
            }
        });
        HomeButton.addActionListener(e -> new MainMenu(frame));

        frame.setContentPane(BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        System.out.println("TEAMS.JAVA: " + team);
    }
}
