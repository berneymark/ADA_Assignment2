public class VehicleAutoControl implements Vehicle {
    private int currentRow;
    private int currentColumn;

    private GUI gui;
    private TerrainPanel terrain;

    public VehicleAutoControl(GUI gui, TerrainPanel terrain) {
        setCurrentRow(0);
        this.gui = gui;
        this.terrain = terrain;

        int[] colPaths = new int[terrain.getColumns()];
        for (int i = 0; i < terrain.getColumns() - 1; i++) {
            colPaths[i] = searchPaths(i);
            System.out.println("\nPATHED ADDED" + i);
        }

        int easiestColumn = 0;

        for (int i = 0; i < terrain.getColumns() - 1; i++) {
            if (colPaths[i] < easiestColumn) {
                easiestColumn = i;
            }
        }

        runPath(easiestColumn);
    }

    public int searchPaths(int column) {
        Node start = new Node(
                0, column,
                Integer.parseInt(terrain.getDifficulty()[0][column])
        );

        Node end = new Node(
                terrain.getRows(), column,
                Integer.parseInt(terrain.getDifficulty()[0][column])
        );
        
        Node current = start;
        int difficultyCount = start.difficulty;

        System.out.println("TERRAIN: " + terrain.getRows());

        while (current.getRow() != terrain.getRows() - 1) {
            Node left = null;
            System.out.println("COLUMN: " + current.getColumn() + " ROW: " + current.getRow());
            if (current.getColumn() > 0) {
                left = new Node(
                    current.getRow() + 1,
                    current.getColumn() - 1,
                    Integer.parseInt(terrain.getDifficulty()[current.getRow() + 1][current.getColumn() - 1])
                );
                left.setParent(current);
                left.setDistance(end);
                left.setTotalCost();
            }
            
            Node front = new Node(
                current.getRow() + 1,
                current.getColumn(),
                Integer.parseInt(terrain.getDifficulty()[current.getRow() + 1][current.getColumn()])
            );
            front.setParent(current);
            front.setDistance(end);
            front.setTotalCost();

            Node right = null;
            if (current.getColumn() < terrain.getColumns()) {
                right = new Node(
                    current.getRow() + 1,
                    current.getColumn() + 1,
                    Integer.parseInt(terrain.getDifficulty()[current.getRow() + 1][current.getColumn() + 1])
                );
                right.setParent(current);
                right.setDistance(end);
                right.setTotalCost();
            }

            if (left == null && right == null) {
                current = front;
                System.out.println("FRONT");
            } else if (left == null && right != null) {
                if (right.compareTo(front) < 0) {
                    current = right;
                    System.out.println("RIGHT");
                } else {
                    current = front;
                    System.out.println("FRONT");
                }
            } else if (left != null && right == null) {
                if (left.compareTo(front) < 0) {
                    current = left;
                    System.out.println("LEFT");
                } else {
                    current = front;
                    System.out.println("FRONT");
                }
            } else {
                if (left.compareTo(front) < 0) {
                    if (left.compareTo(right) < 0) {
                        current = left;
                        System.out.println("LEFT");
                    } else {
                        current = right;
                        System.out.println("RIGHT");
                    }
                } else {
                    current = front;
                    System.out.println("FRONT");
                }
            }

            difficultyCount += current.difficulty;
        }

        return difficultyCount;
    }

    private void runPath(int column) {
        currentRow = 0;
        currentColumn = column;
        notifyGUI();
        Node start = new Node(
                0, column,
                Integer.parseInt(terrain.getDifficulty()[0][column])
        );

        Node end = new Node(
                terrain.getRows(), column,
                Integer.parseInt(terrain.getDifficulty()[0][column])
        );

        Node current = start;

        while (current.getRow() != terrain.getRows() - 1) {
            Node left = null;
            if (current.getColumn() > 0) {
                left = new Node(
                        current.getRow() + 1,
                        current.getColumn() - 1,
                        Integer.parseInt(terrain.getDifficulty()[current.getRow() + 1][current.getColumn() - 1])
                );
                left.setParent(current);
                left.setDistance(end);
                left.setTotalCost();
            }

            Node front = new Node(
                    current.getRow() + 1,
                    current.getColumn(),
                    Integer.parseInt(terrain.getDifficulty()[current.getRow() + 1][current.getColumn()])
            );
            front.setParent(current);
            front.setDistance(end);
            front.setTotalCost();

            Node right = null;
            if (current.getColumn() < terrain.getColumns()) {
                right = new Node(
                        current.getRow() + 1,
                        current.getColumn() + 1,
                        Integer.parseInt(terrain.getDifficulty()[current.getRow() + 1][current.getColumn() + 1])
                );
                right.setParent(current);
                right.setDistance(end);
                right.setTotalCost();
            }

            if (left == null && right == null) {
                current = front;
            } else if (left == null && right != null) {
                if (right.compareTo(front) < 0) {
                    current = right;
                } else current = front;
            } else if (left != null && right == null) {
                if (left.compareTo(front) < 0) {
                    current = left;
                } else current = front;
            } else {
                if (left.compareTo(front) < 0) {
                    if (left.compareTo(right) < 0) {
                        current = left;
                    } else current = right;
                } else current = front;
            }

            currentRow = current.getRow();
            currentColumn = current.getColumn();
            if (currentRow <= terrain.getRows())
                notifyGUI();
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
        gui.updateVehicleLocation(currentRow, currentColumn);
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

    private class Node<E> implements Comparable<Node> {
        private Node parent;

        private int row, column;

        private int distance;
        private int difficulty;
        private int totalCost;

        public Node(int row, int column, int difficulty) {
            this.row = row;
            this.column = column;
            this.difficulty = difficulty;
        }

        public void setDistance(Node target) {
            int distRow = target.getRow() - row;
            int distCol = target.getColumn() - column;
            
            distance = distRow + distCol;
        }

        public void setTotalCost() {
            totalCost = distance + difficulty;
        }

        public void setParent(Node parent) {
            this.parent = parent;
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

        public int getTotalCost() {
            return totalCost;
        }

        public boolean isEqual(Node s, Node e) {
            if (s.getRow() == e.getRow() && s.getColumn() == e.getColumn()) {
                return true;
            } else return false;
        }

        @Override
        public int compareTo(Node o) {
            //negative means I am less
            return this.totalCost - o.getTotalCost();
        }
    }
}
