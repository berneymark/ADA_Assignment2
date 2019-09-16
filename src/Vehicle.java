public interface Vehicle {
    public void setCurrentRow(int row);
    public void setCurrentColumn(int column);

    public int getCurrentRow();
    public int getCurrentColumn();

    // changes currentRow and currentColumn
    public void notifyGUI();
    public boolean left();
    public boolean right();
    public boolean forwards();
}