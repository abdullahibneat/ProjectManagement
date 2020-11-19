package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainMenu {
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

    private static JFrame frame;

    public MainMenu() {
        GridLayout layout0x2 = new GridLayout(0,2);

        //JPanel for Project Fields
        JPanel projectFieldsPanel = new JPanel();
        JTextField projectTitleField = new JTextField(5);
        JTextField projectDurationField = new JTextField(5);

        projectFieldsPanel.setLayout(layout0x2);
        projectFieldsPanel.add(new JLabel("Project Title:"));
        projectFieldsPanel.add(projectTitleField);
//        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        projectFieldsPanel.add(new JLabel("Project Duration:"));
        projectFieldsPanel.add(projectDurationField);

        //JPanel for Teams Fields
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


        ProjectsAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"New",
                        "Import",};
                int n = JOptionPane.showOptionDialog(frame,
                        "Please select an option",
                        "",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 0){                                            // NEED TO CONTINUE THE IF STATEMENT
                    System.out.println("NEW");

                    int result = JOptionPane.showConfirmDialog(null, projectFieldsPanel,
                            "New Project", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        System.out.println("Project Title: " + projectTitleField.getText());
                        System.out.println("Project Duration: " + projectDurationField.getText());

                        new Projects(frame);
                    }

                } else{
                    System.out.println("IMPORT");
                }

            }
        });
        TeamsAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null, TeamsBasePanel,
                        "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    System.out.println("Team Name: " + TeamNameField.getText());
                    System.out.println("Team Leader: " + TeamLeaderField.getText());
                }

                new Teams(frame);

            }
        });
    }

    public MainMenu(JFrame mainFrame){
        frame = mainFrame;
        frame.setContentPane(new MainMenu().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        frame = new JFrame("MainMenu");
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

    }
}


