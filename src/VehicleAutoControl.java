public class VehicleAutoControl implements Vehicle {
    private int currentRow;
    private int currectColumn;
    private char direction;

    @Override
    public void setCurrentRow(int row) {
        this.currentRow = row;
    }

    @Override
    public void setCurrentColumn(int column) {
        this.currectColumn = column;
    }

    @Override
    public int getCurrentRow() {
        return currentRow;
    }

    @Override
    public int getCurrentColumn() {
        return currectColumn;
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
