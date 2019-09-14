public class VehicleManualControl implements Vehicle {
    private int currentRow;
    private int currentColumn;

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
    public void move() {

    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void forwards() {

    }
}
