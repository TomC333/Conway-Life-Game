public class Main {

    // Number Of Rows And Columns
    private static final int ROW = 40;
    private static final int COL = 40;
    private static final int CELL_SIZE = 15;

    public static void main(String[] args) {

        boolean[][] grid = new boolean[ROW][COL];
        Thread[][] cells = new Thread[ROW][COL];

        new ApplicationFrame(grid, cells, ROW, COL, CELL_SIZE);
    }
}
