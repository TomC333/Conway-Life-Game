import java.util.concurrent.CyclicBarrier;

public class CellThread implements Runnable{

    private CyclicBarrier cyclicBarrier;
    private boolean[][] grid;
    private final int i;
    private final int j;

    public CellThread(boolean[][] grid, int i, int j, CyclicBarrier cyclicBarrier){
        this.grid = grid;
        this.i = i;
        this.j = j;
        this.cyclicBarrier = cyclicBarrier;
    }

    /** function returns next state for cell, according to its neighbours
     */
    private boolean getNextState(){

        int aliveCount = 0;

        // Counting number of alive neighbour cells
        for(int row = i - 1; row <= i + 1; row ++){
            for(int col = j - 1; col <= j + 1; col++){

               if(row == i && col == j) continue;
               if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) continue;
               if(grid[row][col]) aliveCount ++;
            }
        }

        boolean nextState = grid[i][j];

        // Using rules of conways game to determine nextState of cell
        if(nextState){
            if(aliveCount < 2 || aliveCount > 3) nextState = false;
        }else if(aliveCount == 3) nextState = true;

        return nextState;
    }

    @Override
    public void run() {

        while (true){

            boolean nextState = getNextState();

            try{
                cyclicBarrier.await();
            }catch (Exception e){ break; }

            grid[i][j] = nextState;

            try{
                cyclicBarrier.await();
            }catch (Exception e){ break; }
        }
    }
}
