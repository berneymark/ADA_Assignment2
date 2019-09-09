import java.util.Random;

public class Terrain {
    private final int ROW_COUNT = 5;
    private final int COL_COUNT = 5;

    private int difficulty[][] = new int[ROW_COUNT][COL_COUNT];

    public Terrain() {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                difficulty[i][j] = new Random().nextInt(15);
            }
        }

        getDifficultyValues();
    }

    public String getDifficultyValues() {
        String diffPrintout = "";

        for (int[] row : difficulty) {
            diffPrintout += "[";
            for (int level : row) {
                diffPrintout += " [";
                diffPrintout += level;
                diffPrintout += "] ";
            }
            diffPrintout += "]\n";
        }

        return diffPrintout;
    }

    public static void main(String[] args) {
        new Terrain();
    }
}
