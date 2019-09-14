public interface Vehicle {
    public void setCurrentRow(int row);
    public void setCurrentColumn(int column);

    public int getCurrentRow();
    public int getCurrentColumn();

    // changes currentRow and currentColumn
    public void notifyGUI();
    public void left();
    public void right();
    public void forwards();
}