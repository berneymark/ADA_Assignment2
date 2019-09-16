import java.util.HashSet;
import java.util.Set;

public class VehicleAutoControl implements Vehicle {
    private int currentRow;
    private int currentColumn;

    private int[] startCoords = new int[2];
    private Set<Integer> targetColumns = new HashSet<>();

    private GUI gui;
    private TerrainPanel terrain;

    public VehicleAutoControl(GUI gui, TerrainPanel terrain) {
        setCurrentRow(0);
        this.gui = gui;
        this.terrain = terrain;

        for (int i = 0; i < terrain.getColumns(); i++) {
            targetColumns.add(i);
        }
    }

    public void setStartCoords(int col) {
        startCoords[0] = 0;
        startCoords[1] = col;

        currentColumn = col;
    }

    public void navigatePath() {

    }

    @Override
    public void setCurrentRow(int row) {
        this.currentRow = row;
    }

    @Override
    public void setCurrentColumn(int column) {
        this.currentColumn = column;
    }

    @Override
    public int getCurrentRow() {
        return currentRow;
    }

    @Override
    public int getCurrentColumn() {
        return currentColumn;
    }

    @Override
    public void notifyGUI() {

    }

    @Override
    public void left() {
        if ((currentRow >= 0 && currentRow < terrain.getRows() - 1)
                && (currentColumn > 0 && currentColumn <= terrain.getColumns() - 1)) {
            currentRow++;
            currentColumn--;
            notifyGUI();
        }
    }

    @Override
    public void right() {
        if ((currentRow >= 0 && currentRow < terrain.getRows() - 1)
                && (currentColumn >= 0 && currentColumn < terrain.getColumns() - 1)) {
            currentRow++;
            currentColumn++;
            notifyGUI();
        }
    }

    @Override
    public void forwards() {
        if (currentRow >= 0 && currentRow < terrain.getRows() - 1) {
            currentRow++;
            notifyGUI();
        }
    }
}
