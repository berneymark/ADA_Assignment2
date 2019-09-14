import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private final int WINDOW_WIDTH = 900;
    private final int WINDOW_HEIGHT = 600;

    private DatabaseAdmin database = new DatabaseAdmin();
    private TerrainPanel terrainPanel;
    private Vehicle vehicle;

    private GUI() {
        buildGUI();
        database.connectDatabase();
        selectTerrain();
        selectVehicle();
        selectStartingColumn();

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
        terrainPanel = new TerrainPanel(
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
            vehicle = new VehicleManualControl(terrainPanel);
        } else if (vehicleSelect.equals("Automated Control")) {
            vehicle = new VehicleAutoControl();
        }
    }

    private void selectStartingColumn() {
        Integer[] columns = new Integer[database.getCols()];
        for (int i = 0; i < database.getCols(); i++) {
            columns[i] = i;
        }

        int startingColumnSelect = (int)JOptionPane.showInputDialog(
                this,
                "Please select a starting column:",
                "Starting Column Select",
                JOptionPane.QUESTION_MESSAGE,
                null,
                columns,
                columns[0]
        );

        for (int i = 0; i < database.getCols(); i++) {
            if (i == startingColumnSelect) {
                terrainPanel.getRegions()[0][i].setBackground(Color.GREEN);
            }
        }

        vehicle.setCurrentColumn(startingColumnSelect);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
