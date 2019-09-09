import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private final int WINDOW_WIDTH = 900;
    private final int WINDOW_HEIGHT = 600;

    public GUI() {
        buildGUI();
    }

    private void buildGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("abc");
        getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(
                dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2
        );

        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
