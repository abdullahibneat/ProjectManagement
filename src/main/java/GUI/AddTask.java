package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTask {
    protected JPanel panel1;
    private JTextField TaskNameTextfield;
    private JTextField DurationTextfield;
    private JCheckBox dependentCheckBox;
    private JComboBox TeamMemeber1ComboBox;
    private JComboBox DependentComboBox;
    private JComboBox TeamMemeber2ComboBox;
    private JComboBox TeamMemeber3ComboBox;
    private JComboBox TeamMemeber4ComboBox;
    private JComboBox TeamMemeber5ComboBox;
    private JLabel TaskNameLabel;
    private JLabel DurationLabel;
    private JLabel TeamMemeber1Label;
    private JLabel TeamMemeber2Label;
    private JLabel TeamMemeber3Label;
    private JLabel TeamMemeber4Label;
    private JLabel TeamMemeber5Label;
    private JButton addButton;
    private JButton Cancel;

    private static JFrame frame;

    public AddTask() {
        DependentComboBox.setVisible(false); // Starts off Not visible

        dependentCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dependentCheckBox.isSelected()) {
                    DependentComboBox.setVisible(true);
//                    frame.revalidate();
//                    frame.repaint();
                } else{
                    DependentComboBox.setVisible(false);
//                    frame.revalidate();
//                    frame.repaint();
                }
            }
        });
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel");
                frame.dispose(); // Nullpointerexception
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Task");
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("AddTask");
        frame.setContentPane(new AddTask().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
