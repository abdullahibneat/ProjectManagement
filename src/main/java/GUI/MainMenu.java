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

    private static JFrame frame;

    private ProjectFields projectfields = new ProjectFields();


    public MainMenu() {
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

                    frame = new JFrame("Project Fields");
                    frame.setContentPane(new ProjectFields().basepanel);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);

                } else{
                    System.out.println("IMPORT");
                }

            }
        });
    }

    public MainMenu(JFrame frame){
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


