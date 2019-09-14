import javax.swing.*;
import java.awt.*;

public class TerrainPanel extends JPanel {
    private int rows;
    private int columns;
    private String[][] difficulty;

    private JPanel[][] regions;

    public TerrainPanel(int rows, int columns, String[][] difficulty) {
        super();
        this.rows = rows;
        this.columns = columns;
        this.difficulty = difficulty;
        drawTerrain();
        setVisible(true);
    }

    private void drawTerrain() {
        setLayout(new GridLayout(rows, columns));
        regions = new JPanel[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                regions[i][j] = new JPanel();
                regions[i][j].setBorder(
                        BorderFactory.createLineBorder(Color.BLACK)
                );
                regions[i][j].add(new JLabel(difficulty[i][j]));
                add(regions[i][j]);
            }
        }
    }
}
