import java.util.*;

public class PercolationBFS extends PercolationDFSFast {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }

    @Override
    protected void dfs(int row, int col) {
        int size = 0;
        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};

        // out of bounds?
        if (! inBounds(row,col)) return;

        // full or NOT open, don't process
        if (isFull(row, col) || !isOpen(row, col))
            return;

        Queue<Integer> list = new LinkedList<>();
        myGrid[row][col] = FULL;
        size++;
        list.add(row * myGrid.length + col);
        while(list.size() != 0){
            int value = list.remove();
            int newRow = value/myGrid.length;
            int newCol = value % myGrid.length;
            for(int k=0; k < rowDelta.length; k++){
                row = newRow + rowDelta[k];
                col = newCol + colDelta[k];
                if (inBounds(row,col) && myGrid[row][col] == OPEN){
                    list.add(row * myGrid.length + col);
                    myGrid[row][col] = FULL;
                    size++;
                }
            }
        }
    }
}
