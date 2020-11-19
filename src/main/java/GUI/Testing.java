package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class Testing {
    public static void main(String[] args) {

        GridLayout layout0x2 = new GridLayout(0,2);

        JPanel TeamsBasePanel = new JPanel();
        JTextField TeamNameField = new JTextField(5);
        JTextField TeamLeaderField = new JTextField(5);
        JComboBox Projectlist = new JComboBox();

        TeamsBasePanel.setLayout(layout0x2);
        TeamsBasePanel.add(new JLabel("Team Name:"));
        TeamsBasePanel.add(TeamNameField);
        TeamsBasePanel.add(new JLabel("Team Leader:"));
        TeamsBasePanel.add(TeamLeaderField);
        TeamsBasePanel.add(new JLabel("Project:"));
        TeamsBasePanel.add(Projectlist);


        int result = JOptionPane.showConfirmDialog(null, TeamsBasePanel,
                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Team Name: " + TeamNameField.getText());
            System.out.println("Team Leader: " + TeamLeaderField.getText());
        }
    }
}
