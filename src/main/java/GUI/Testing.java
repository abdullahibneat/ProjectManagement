package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Testing {
    public static void main(String[] args) {

         int count;
         JComboBox WorkingTeamMemberComboBox;
         String WorkingTeamMemeber;
         JScrollPane BaseScrollPane;
//////////////////////////////////////////////////////////////////
         WorkingTeamMemeber = "Working Team Member #";
//ADD TASK JOPTION
        GridLayout layout0x2 = new GridLayout(0,2);

        count = 0;
        WorkingTeamMemeber = "Team Member #";

        JPanel AddTeamMemebersPannel = new JPanel();
        JTextField TeamLeaderField = new JTextField(5);
//        JTextField TaskDurationField = new JTextField(5);
//        JCheckBox DependentCheckBox = new JCheckBox("Dependent:");
//        JComboBox ProjectDependentComboBox = new JComboBox();
//        JComboBox WorkingTeamComboBox = new JComboBox();
        JButton AddTeamMembersButton = new JButton("Add Team Members");

        AddTeamMemebersPannel.setLayout(layout0x2);
//        AddTaskPanel.setPreferredSize(new Dimension(300 ,300));
        AddTeamMemebersPannel.add(new JLabel("Team Leader:"));
        AddTeamMemebersPannel.add(TeamLeaderField);
//        AddTeamMemebersPannel.add(new JLabel("Task Duration:"));
//        AddTeamMemebersPannel.add(TaskDurationField);
//        AddTeamMemebersPannel.add(DependentCheckBox);
//        AddTeamMemebersPannel.add(ProjectDependentComboBox);
//        ProjectDependentComboBox.setEnabled(false);

//        AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer
//        AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer

//        AddTeamMemebersPannel.add(new JLabel("Working Team:"));
//        AddTeamMemebersPannel.add(WorkingTeamComboBox);

//        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer
//        AddTaskPanel.add(Box.createHorizontalStrut(5)); // a spacer

        AddTeamMemebersPannel.add(AddTeamMembersButton);
        AddTeamMemebersPannel.add(Box.createHorizontalStrut(5)); // a spacer

        BaseScrollPane = new JScrollPane(AddTeamMemebersPannel);
        BaseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        BaseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//        BaseScrollPane.setPreferredSize(new Dimension(400 ,200));

        AddTeamMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                count++;
//                WorkingTeamMemberComboBox = new JComboBox();
//                WorkingTeamComboBox.setName(WorkingTeamMemeber + count);
//                AddTeamMemebersPannel.add(new JLabel(WorkingTeamMemeber + count));
//                AddTeamMemebersPannel.add(WorkingTeamMemberComboBox);
//                AddTeamMemebersPannel.revalidate();
//                AddTeamMemebersPannel.repaint();
//                System.out.println("pressed");

            }
        });


        int result = JOptionPane.showConfirmDialog(null, BaseScrollPane,
                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Task Name: " + TeamLeaderField.getText());
//            System.out.println("Task Duration: " + TaskDurationField.getText());
        }
    }
}
