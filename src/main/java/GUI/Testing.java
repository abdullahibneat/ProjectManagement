package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Testing extends JFrame {
    protected JPanel panel1;
    private JTree tree1;
    private JButton button1;
    private JLabel testlabel1;

    private static JFrame frame;

    public Testing () {

    }

    public static void main(String[] args) {
        frame = new JFrame("Testing");
        frame.setContentPane(new Testing().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        testlabel1 = new JLabel("PLS JUSTWORK");
        testlabel1.setText("TESTING 12345");
    }
}
