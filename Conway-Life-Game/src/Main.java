import javax.swing.*;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.random.RandomGenerator;

public class Main {

    // Number Of Rows And Columns
    private static final int ROW = 70;
    private static final int COL = 70;
    private static final int CELL_SIZE = 10;

    public static void main(String[] args) {

        boolean[][] grid = new boolean[ROW][COL];
        Thread[][] cells = new Thread[ROW][COL];

        new ApplicationFrame(grid, cells, ROW, COL, CELL_SIZE);
    }
}
