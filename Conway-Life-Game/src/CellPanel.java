import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellPanel extends JPanel {

    private final boolean[][] grid;
    private static int row;
    private static int col;
    private static int size;
    private boolean gridEnabled;

    /** Constructor determines size of JPanel according to received arguments
     * and adds mouse listener to that panel (click on cell changes it's state)
     */
    public CellPanel(boolean[][] grid, int row, int col, int size){

        this.grid = grid;
        CellPanel.row = row;
        CellPanel.col = col;
        CellPanel.size = size;
        gridEnabled = true;

        setPreferredSize(new Dimension(col * size, row * size));

        // Listener for cells
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // If pressing on grid is not enabled, function returns immediately
                if(!gridEnabled) return;

                // Finding on which cell user pressed
                int row = e.getX() / size;
                int col = e.getY() / size;
                changeCellState(row, col);
                repaint();
            }
        });
    }

    private void changeCellState(int i, int j){
        grid[i][j] = !grid[i][j];
    }

    /** function enables editing of grid
     */
    public void enableGrid(){
        gridEnabled = true;
    }

    /** function disables editing of grid
     */
    public void disableGrid(){
        gridEnabled = false;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){

                int x = i * size;
                int y = j * size;

                // Setting colors of each cell according "grid"
                g.setColor(grid[i][j] ? Color.BLUE : Color.BLACK);
                g.fillRect(x, y, size, size);

                // Creating "outline effect" for each cell
                g.setColor(Color.GRAY);
                g.drawRect(x, y, size, size);
            }
        }
    }
}
