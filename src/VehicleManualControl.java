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
        } else System.out.println("This key does nothing.");
    }

    @Override
    public void left() {
        System.out.println("Moved front-left");
        currentRow++;
        currentColumn--;
    }

    @Override
    public void right() {
        System.out.println("Moved front-right");
        currentRow++;
        currentColumn++;
    }

    @Override
    public void forwards() {
        System.out.println("Moved forwards");
        currentRow++;
    }
}
