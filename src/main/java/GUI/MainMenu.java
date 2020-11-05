package GUI;

import javax.swing.*;
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


    public MainMenu() {
        ProjectsAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("testing");
                AddProject ap = new AddProject();
//                ap.setVisible(true);

            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("MainMenu");
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


