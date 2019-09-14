public interface Vehicle {
    public void setCurrentRow();
    public void setCurrentColumn();

    public void getCurrentRow();
    public void getCurrentColumn();

    // changes currentRow and currentColumn
    public void move();
}