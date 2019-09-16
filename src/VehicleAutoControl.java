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

    private class Node {
        private Node parentNode;
        private Node leftChild;
        private Node frontChild;
        private Node rightChild;

        private int row;
        private int column;
        private int difficulty;

        public Node(Node parentNode, int row, int column, int difficulty) {
            this.parentNode = parentNode;
            this.row = row;
            this.column = column;
            this.difficulty = difficulty;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public void setFrontChild(Node frontChild) {
            this.frontChild = frontChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        public int getDifficulty() {
            return difficulty;
        }
    }
}
