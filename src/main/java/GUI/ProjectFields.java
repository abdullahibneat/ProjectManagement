package GUI;

import com.sun.javaws.util.JfxHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectFields {
    protected JPanel basepanel;
    private JTextField ProjectTitleTextField;
    private JTextField ProjectDurationTextField;
    private JLabel ProjectTitleLabel;
    private JLabel ProjectDurationLabel;
    private JButton CreateButton;
    private JButton CancelButton;

    private static JFrame frame;
    protected String t;
    protected String d;

//    private Projects projects = new Projects();

    public ProjectFields() {
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel");
                frame.dispose();
            }
        });
        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create");
                 t = ProjectTitleTextField.getText();
                 d = ProjectDurationTextField.getText();
                System.out.println("Project Title is:" + t);
                System.out.println("Project Duration is:" + d);

//                projects.setContentPane(new Projects().BasePanel);
//                projects.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                projects.pack();
//                projects.setVisible(true);
//                projects.setLocationRelativeTo(null); // projects work in linking from menu
//                frame.dispose();

                new Projects(frame); // NULL POINTER IF EXECUTED FROM MAINMENU




            }
        });
    }

    public ProjectFields(JFrame frame){
        frame.setContentPane(new ProjectFields().basepanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        frame = new JFrame("New Project");
        frame.setContentPane(new ProjectFields().basepanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
