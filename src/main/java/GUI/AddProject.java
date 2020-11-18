package GUI;  // LOOKING TO INHERIT POPUPMENU IN THE PROPERTIES (NOT DONE YET)

import com.sun.javaws.util.JfxHelper;
import javafx.stage.PopupWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProject extends JFrame {                                  // NOT BEING USED RIGHT NOW
    protected JPanel BasePanel;
    private JButton newProjectButton;
    private JButton importProjectButton;
    private JLabel SelectionLabel;

    private static JFrame frame;

    public AddProject(){
        newProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New Project");
//                new ProjectFields(frame);
            }
        });
    }

    public AddProject(JFrame frame){
        frame.setContentPane(new AddProject().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        frame = new JFrame("AddProject");
        frame.setContentPane(new AddProject().BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
