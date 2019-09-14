import java.awt.*;

public class VehicleManualControl implements Vehicle {
    private int currentRow;
    private int currentColumn;
    private char direction;

    private TerrainPanel terrain;

    public VehicleManualControl(TerrainPanel terrain) {
        setCurrentRow(0);
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
    public void setDirection(char direction) {
        this.direction = direction;
    }

    @Override
    public char getDirection() {
        return direction;
    }

    @Override
    public void move() {
        if (direction == 'a') {
            left();
        } else if (direction == 's') {
            forwards();
        } else if (direction == 'd') {
            right();
        }
    }

    @Override
    public void left() {
        terrain.getRegions()[getCurrentRow()][getCurrentColumn()]
                .setBackground(Color.WHITE);

        setCurrentRow(currentRow++);
        setCurrentColumn(currentColumn--);

        terrain.getRegions()[getCurrentRow()][getCurrentColumn()]
                .setBackground(Color.GREEN);
    }

    @Override
    public void right() {
        terrain.getRegions()[getCurrentRow()][getCurrentColumn()]
                .setBackground(Color.WHITE);

        setCurrentRow(currentRow++);
        setCurrentColumn(currentColumn++);

        terrain.getRegions()[getCurrentRow()][getCurrentColumn()]
                .setBackground(Color.GREEN);
    }

    @Override
    public void forwards() {
        terrain.getRegions()[getCurrentRow()][getCurrentColumn()]
                .setBackground(Color.WHITE);

        setCurrentRow(currentRow++);

        terrain.getRegions()[getCurrentRow()][getCurrentColumn()]
                .setBackground(Color.GREEN);
    }
}
