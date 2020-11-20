package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Teams {
    private JPanel BasePanel;
    private JList TeamsList;
    private JPanel TeamsPanel;
    private JPanel TeamsSubPanel;
    private JPanel TopPanel;
    private JLabel TeamLabel;
    private JLabel TeamLeaderLabel;
    private JLabel TaskLabel;
    private JLabel MemebersLabel;
    private JButton button1;

    private static JFrame frame;

    private int count;
    private JTextField TeamMemberField;
    private String TeamMember;
    private JScrollPane BaseScrollPane;


public Teams(){
//ADD TEAM MEMBERS JOPTION
    GridLayout layout0x2 = new GridLayout(0,2);

    count = 0;
    TeamMember = "Team Member #";

    JPanel AddTeamMemebersPannel = new JPanel();
    JTextField TeamLeaderField = new JTextField(5);
    JButton AddTeamMembersButton = new JButton("Add Team Members");

    AddTeamMemebersPannel.setLayout(layout0x2);
//        AddTaskPanel.setPreferredSize(new Dimension(300 ,300));
    AddTeamMemebersPannel.add(new JLabel("Team Leader:"));
    AddTeamMemebersPannel.add(TeamLeaderField);

    AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer
    AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer


    AddTeamMemebersPannel.add(AddTeamMembersButton);
    AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer



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

        }
    });

    button1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(null, BaseScrollPane,
                    "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                System.out.println("Task Name: " + TeamLeaderField.getText());
            }
        }
    });
}

public Teams(JFrame mainFrame){
    frame = mainFrame;
    frame.setContentPane(new Teams().BasePanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Teams");
        frame.setContentPane(new Teams().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
