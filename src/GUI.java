import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private final int WINDOW_WIDTH = 900;
    private final int WINDOW_HEIGHT = 600;

    private DatabaseAdmin database = new DatabaseAdmin();
    private Vehicle vehicle = null;

    private GUI() {
        buildGUI();
        database.connectDatabase();
        selectTerrain();
        selectVehicle();

        setVisible(true);
    }

    private void buildGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Path Finder");
        getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(
                dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2
        );
    }

    private void selectTerrain() {
        String[] tableNames = {
                "illustrated",
                "large",
                "medium",
                "small",
                "tinyA",
                "tinyB"
        };

        String terrainSelect = (String)JOptionPane.showInputDialog(
                this,
                "Please choose a terrain:",
                "Terrain Select",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tableNames,
                tableNames[0]
        );

        database.setDifficulty(terrainSelect);
        TerrainPanel terrainPanel = new TerrainPanel(
                database.getRows(),
                database.getCols(),
                database.getDifficulty()
        );

        add(terrainPanel, BorderLayout.CENTER);
    }

    private void selectVehicle() {
        String[] vehicleNames = {
            "Manual Control",
            "Automated Control"
        };

        String vehicleSelect = (String)JOptionPane.showInputDialog(
                this,
                "Please choose a vehicle:",
                "Vehicle Select",
                JOptionPane.QUESTION_MESSAGE,
                null,
                vehicleNames,
                vehicleNames[0]
        );

        if (vehicleSelect.equals("Manual Control")) {
            vehicle = new VehicleManualControl();
        } else if (vehicleSelect.equals("Automated Control")) {
            vehicle = null;
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
