public interface Vehicle {
    public void setCurrentRow(int row);
    public void setCurrentColumn(int column);

    public int getCurrentRow();
    public int getCurrentColumn();

    public void setDirection(char direction);
    public char getDirection();

    // changes currentRow and currentColumn
    public void move();
    public void left();
    public void right();
    public void forwards();
}