public class PercolationDFSFast extends PercolationDFS{
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }

    @Override
    protected void updateOnOpen(int row, int col) {
        if(row == 0) {
            dfs(row, col);
        }
        else if (inBounds(row - 1, col) == true && isFull(row - 1, col) == true){
            dfs(row, col);
        }
        else if (inBounds(row + 1, col) == true && isFull (row + 1, col) == true){
            dfs(row, col);
        }
        else if (inBounds(row, col - 1) == true && isFull(row, col - 1) == true) {
            dfs(row, col);
        }
        else if (inBounds(row, col + 1) == true && isFull(row, col + 1) == true){
            dfs(row, col);
        }
    }
}