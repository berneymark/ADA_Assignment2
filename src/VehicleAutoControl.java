import org.w3c.dom.NodeList;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class VehicleAutoControl implements Vehicle {
    private int currentRow;
    private int currentColumn;

    private GUI gui;
    private TerrainPanel terrain;

    public VehicleAutoControl(GUI gui, TerrainPanel terrain) {
        setCurrentRow(0);
        this.gui = gui;
        this.terrain = terrain;
    }

    public void createTree(int column) {
        LinkedList<LinkedList> rows = new LinkedList<>();
        Node rootNode = new Node(
            null,
            0,
            column,
            Integer.parseInt(terrain.getDifficulty()[0][column])
        );

        currentRow = 0;
        currentColumn = column;

        Node currentNode = rootNode;

        for (int i = 0; i < terrain.getRows(); i++) {
            if (left()) {
                currentNode.leftChild = new Node(
                    currentNode,
                    currentRow,
                    currentColumn,
                    Integer.parseInt(terrain.getDifficulty()[currentRow][currentColumn])
                );

                currentRow = currentNode.getRow();
                currentColumn = currentNode.getColumn();
            }

            if (forwards()) {
                currentNode.frontChild = new Node(
                    currentNode,
                    currentRow,
                    currentColumn,
                    Integer.parseInt(terrain.getDifficulty()[currentRow][currentColumn])
                );

                currentRow = currentNode.getRow();
                currentColumn = currentNode.getColumn();
            }

            if (right()) {
                currentNode.rightChild = new Node(
                    currentNode,
                    currentRow,
                    currentColumn,
                    Integer.parseInt(terrain.getDifficulty()[currentRow][currentColumn])
                );

                currentRow = currentNode.getRow();
                currentColumn = currentNode.getColumn();
            }
        }
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
    public boolean left() {
        if ((currentRow >= 0 && currentRow < terrain.getRows() - 1)
                && (currentColumn > 0 && currentColumn <= terrain.getColumns() - 1)) {
            currentRow++;
            currentColumn--;
            return true;
        }

        return false;
    }

    @Override
    public boolean right() {
        if ((currentRow >= 0 && currentRow < terrain.getRows() - 1)
                && (currentColumn >= 0 && currentColumn < terrain.getColumns() - 1)) {
            currentRow++;
            currentColumn++;
            return true;
        }

        return false;
    }

    @Override
    public boolean forwards() {
        if (currentRow >= 0 && currentRow < terrain.getRows() - 1) {
            currentRow++;
            return true;
        }

        return false;
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
