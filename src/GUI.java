import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private final int WINDOW_WIDTH = 900;
    private final int WINDOW_HEIGHT = 600;

    private DatabaseAdmin database = new DatabaseAdmin();

    private JButton submitTerrain;
    private JComboBox selectTerrain;

    private GUI() {
        buildGUI();
        database.connectDatabase();
    }

    private void buildGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("abc");
        getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(
                dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2
        );

        setControlPanel();

        setVisible(true);
    }

    private void setControlPanel() {
        JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.SOUTH);

        String[] tableNames = {
                "illustrated",
                "large",
                "medium",
                "small",
                "tinyA",
                "tinyB"
        };
        selectTerrain = new JComboBox(tableNames);
        controlPanel.add(selectTerrain);

        submitTerrain = new JButton("Add Terrain From Server");
        submitTerrain.addActionListener(new ActionListeners());
        controlPanel.add(submitTerrain);
    }

    public static void main(String[] args) {
        new GUI();
    }

    private class ActionListeners implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitTerrain) {
                JOptionPane.showMessageDialog(null, (String)selectTerrain.getSelectedItem());
            }
        }
    }
}
