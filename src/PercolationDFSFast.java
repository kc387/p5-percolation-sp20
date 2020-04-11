public class PercolationDFSFast extends PercolationDFS{
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    private boolean full = false;
    public PercolationDFSFast(int n) {
        super(n);
    }

    /**
     * Determines if a cell is full of water based on other surrounding cells
     * @param row specifies row
     * @param col specifies column
     */
    @Override
    protected void updateOnOpen(int row, int col) {
        full = false;
        if(row == 0) {
            full = true;
        }
        else if (inBounds(row - 1, col) && isFull(row - 1, col)){
            full = true;
        }
        else if (inBounds(row + 1, col) && isFull (row + 1, col)){
            full = true;
        }
        else if (inBounds(row, col - 1) && isFull(row, col - 1)) {
            full = true;
        }
        else if (inBounds(row, col + 1) && isFull(row, col + 1)){
            full = true;
        }
        if (full == true){
            dfs(row, col);
        }
    }
}