import Persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Teams {
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

    private static JFrame frame;

    private int count;
    private JTextField TeamMemberField;
    private String TeamMember;
    private JScrollPane BaseScrollPane;
    private ArrayList<JTextField> newmembers = new ArrayList();

    private Team team;

//public Teams(){
////ADD TEAM MEMBERS JOPTION
//    GridLayout layout0x2 = new GridLayout(0,2);
//
//    count = 0;
//    TeamMember = "Team Member #";
//
//    JPanel AddTeamMemebersPannel = new JPanel();
//    JTextField TeamLeaderField = new JTextField(5);
//    JButton AddTeamMembersButton = new JButton("Add Team Members");
//
//    AddTeamMemebersPannel.setLayout(layout0x2);
////        AddTaskPanel.setPreferredSize(new Dimension(300 ,300));
//    AddTeamMemebersPannel.add(new JLabel("Team Leader:"));
//    AddTeamMemebersPannel.add(TeamLeaderField);
//
//    AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer
//    AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer
//
//
//    AddTeamMemebersPannel.add(AddTeamMembersButton);
//    AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer
//
//
//
//    BaseScrollPane = new JScrollPane(AddTeamMemebersPannel);
//    BaseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//    BaseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//    BaseScrollPane.setPreferredSize(new Dimension(400 , 80));
//
//    AddTeamMembersButton.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            count++;
//            TeamMemberField = new JTextField();
//            TeamMemberField.setName(TeamMember + count);
//            AddTeamMemebersPannel.add(new JLabel(TeamMember + count));
//            AddTeamMemebersPannel.add(TeamMemberField);
//            AddTeamMemebersPannel.revalidate();
//            AddTeamMemebersPannel.repaint();
//            System.out.println("New Member Field Added");
//            newmembers.add(TeamMemberField);
//
//        }
//    });
//
//    button1.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println(team);
//            int result = JOptionPane.showConfirmDialog(null, BaseScrollPane,
//                    "Edit Team", JOptionPane.OK_CANCEL_OPTION);
//            if (result == JOptionPane.OK_OPTION) {
//                if (TeamLeaderField.getText().trim().isEmpty()) {
//                    System.out.println("PLEASE FILL ALL FIELDS");
//                    TeamLeaderField.setText("");
//                }else{
//                    System.out.println("Task Name: " + TeamLeaderField.getText());
////                    System.out.println(team.getName());
//                    for (JTextField field: newmembers ) {
//                        System.out.println(field.getText());
//                        team.addMember(new Member(field.getText().trim()));
//
//                    }
//                    System.out.println(Persistence.INSTANCE);
//                }
//            }else{
//                System.out.println("CANCEL");
//            }
//        }
//    });
//    HomeButton.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            new MainMenu(frame);
//        }
//    });
//}

public Teams(JFrame mainFrame, Team currentTeam){
    frame = mainFrame;
//    System.out.println(currentTeam);
    team = currentTeam;

    //ADD TEAM MEMBERS JOPTION
    GridLayout layout0x2 = new GridLayout(0,2);

    count = 0;
    TeamMember = "Team Member #";

    JPanel AddTeamMemebersPannel = new JPanel();
    JTextField TeamLeaderField = new JTextField(5);
    JButton AddTeamMembersButton = new JButton("Add Team Members");

    AddTeamMemebersPannel.setLayout(layout0x2);
//        AddTaskPanel.setPreferredSize(new Dimension(300 ,300));
//    AddTeamMemebersPannel.add(new JLabel("Team Leader:"));
//    AddTeamMemebersPannel.add(TeamLeaderField);

//    AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer
//    AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer

    MembersLabel.setText("Members:"+ currentTeam.getMembers().size());

    TeamLabel.setText("Team Name: " + currentTeam.getName());

    for (Member m:currentTeam.getMembers()){
        if (count >= 1) {
            break;
        }else{
            TeamLeaderLabel.setText("Team Leader:" + m.getName());
            count++;
        }
    }
    count=0;
    System.out.println("Team Leader Set" + "\n" + "count:" + count);

    TaskLabel.setText("Project:");

    AddTeamMemebersPannel.add(AddTeamMembersButton);
    AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer

    TeamsList.setEditable(false);


    BaseScrollPane = new JScrollPane(AddTeamMemebersPannel);
    BaseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    BaseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    BaseScrollPane.setPreferredSize(new Dimension(400 , 80));

    AddTeamMembersButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            count++;
            TeamMemberField = new JTextField();
            TeamMemberField.setName(TeamMember + count);
            AddTeamMemebersPannel.add(new JLabel(TeamMember + count));
            AddTeamMemebersPannel.add(TeamMemberField);
            AddTeamMemebersPannel.revalidate();
            AddTeamMemebersPannel.repaint();
            System.out.println("New Member Field Added");
            newmembers.add(TeamMemberField);

        }
    });

    button1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(team);
            int result = JOptionPane.showConfirmDialog(null, BaseScrollPane,
                    "Edit Team", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
//                if (TeamLeaderField.getText().trim().isEmpty()) {
//                    System.out.println("PLEASE FILL ALL FIELDS");
//                    TeamLeaderField.setText("");
//                }else{
                    System.out.println("Task Name: " + TeamLeaderField.getText());
//                    System.out.println(team.getName());
                    for (JTextField field: newmembers ) {
                        System.out.println(field.getText());
                        team.addMember(new Member(field.getText().trim()));
                        TeamsList.add(new JLabel(field.getText()));
                        TeamsList.append(field.getText() + "\n");
                    }

                    MembersLabel.setText("Members:" + team.getMembers().size());

                    newmembers.clear();
                for (Component c : AddTeamMemebersPannel.getComponents()) {
                    if(c.getClass() == JLabel.class || c.getClass() == JTextField.class) AddTeamMemebersPannel.remove(c);
                }
                count = 0;
                    System.out.println(Persistence.INSTANCE);
//                }
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
    System.out.println("TEAMS.JAVA: " + team);
}

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Teams");
//        frame.setContentPane(new Teams().BasePanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//    }
}
