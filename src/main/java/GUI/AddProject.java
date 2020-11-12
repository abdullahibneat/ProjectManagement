package GUI;  // LOOKING TO INHERIT POPUPMENU IN THE PROPERTIES (NOT DONE YET)

import com.sun.javaws.util.JfxHelper;
import javafx.stage.PopupWindow;

import javax.swing.*;

public class AddProject {                                  // NOT BEING USED RIGHT NOW
    private JPanel BasePanel;
    private JButton newProjectButton;
    private JButton importProjectButton;
    private JLabel SelectionLabel;

    private static JFrame frame;

    public AddProject(){
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
