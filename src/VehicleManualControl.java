import java.awt.*;

public class VehicleManualControl implements Vehicle {
    private int currentRow;
    private int currentColumn;

    private GUI gui;
    private TerrainPanel terrain;

    public VehicleManualControl(GUI gui, TerrainPanel terrain) {
        setCurrentRow(0);
        this.gui = gui;
        this.terrain = terrain;
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
        gui.updateVehicleLocation();
    }

    @Override
    public void left() {
        System.out.println("Moved front-left");

        if ((currentRow >= 0 && currentRow < terrain.getRows() - 1)
                && (currentColumn > 0 && currentColumn <= terrain.getColumns() - 1)) {
            currentRow++;
            currentColumn--;
            notifyGUI();
        } else System.out.println("can't go left");
    }

    @Override
    public void right() {
        System.out.println("Moved front-right");

        if ((currentRow >= 0 && currentRow < terrain.getRows() - 1)
                && (currentColumn >= 0 && currentColumn < terrain.getColumns() - 1)) {
            currentRow++;
            currentColumn++;
            notifyGUI();
        }
    }

    @Override
    public void forwards() {
        System.out.println("Moved forwards");

        if (currentRow >= 0 && currentRow < terrain.getRows() - 1) {
            currentRow++;
            notifyGUI();
        }
    }
}
