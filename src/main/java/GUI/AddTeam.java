package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTeam {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField TeamLeaderTextField;
    private JButton AddTeamMemeberButton;
    private JLabel ProjectLabel;
    private JLabel TaskLabel;
    private JLabel TeamLeaderLabel;
    private JButton AddMemberButton;
    private JPanel TestPanel;

    private static JFrame frame;

    private int count;
    private JTextField tfield;
    private String nameTField;

    public AddTeam() {

        AddMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfield = new JTextField();
                tfield.setName(nameTField + count);
                count++;
                frame.add(tfield);
                frame.revalidate();
                frame.repaint();
                System.out.println("pressed");

            }
        });
    }

    public AddTeam (JFrame mainFrame){
        frame = mainFrame;
        frame.setContentPane(new AddTeam().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddTeam");
        frame.setContentPane(new AddTeam().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
