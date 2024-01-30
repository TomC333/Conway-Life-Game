import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class ApplicationFrame extends JFrame {

    private static final int GAP = 4;
    private final CellPanel view;
    private final Random rand = new Random();

    private CyclicBarrier barier;


    public ApplicationFrame(boolean[][] grid, Thread[][] cells, int row, int col, int size){

        // Trying to change look of GUI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        setLayout(new BorderLayout(GAP, GAP));

        // Adding few panels on screen
        view = new CellPanel(grid, row, col, size);
        add(view, BorderLayout.NORTH);
        addButtons(grid, cells);

        // Options for frame, just ignore
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButtons(boolean[][] grid, Thread[][] cells){

        JPanel bottom = new JPanel();
        JButton start = new JButton("Start");
        JButton end = new JButton("End");
        JButton clear = new JButton("Clear");
        JButton randomize = new JButton("Random Cells");

        bottom.add(start);
        bottom.add(end);
        bottom.add(clear);
        bottom.add(randomize);
        add(bottom, BorderLayout.SOUTH);

        // Action Listener for start button
        start.addActionListener(e -> {
            end.setEnabled(true);
            start.setEnabled(false);
            clear.setEnabled(false);
            randomize.setEnabled(false);
            view.disableGrid();

            this.barier = new CyclicBarrier(cells.length * cells[0].length, this::repaint);

            // Creating and start new Threads for each cell
            for(int i = 0; i < cells.length; i++){
                for(int j = 0; j < cells[0].length; j++){
                    cells[i][j] = new Thread(new CellThread(grid, i, j, this.barier));
                    cells[i][j].start();
                }
            }
        });

        // Action listener for end button
        end.addActionListener(e ->{
            // Just interrupting all threads
            for (Thread[] cellArray : cells) {
                for (Thread cell : cellArray) cell.interrupt();
            }

            view.enableGrid();
            end.setEnabled(false);
            start.setEnabled(true);
            clear.setEnabled(true);
            randomize.setEnabled(true);
        });

        // Action listener for clear button
        clear.addActionListener(e ->{
            // Give false value to each position in grid
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    grid[i][j] = false;
                }
            }
            repaint();
        });

        // Action listener for randomize button
        randomize.addActionListener(e ->{
            // Give random value to each position in grid
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    grid[i][j] = rand.nextBoolean();
                }
            }
            repaint();
        });
    }
}
