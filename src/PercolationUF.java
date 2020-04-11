import java.util.Arrays;

public class PercolationUF implements IPercolate {

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    /**
     * Initialize a grid so that all cells are blocked and are each part of their own set
     *
     * @param finder is a IUnionFind object that determines the sets of the cells and unions them when cells are opened
     * @param size is the size of the simulated (square) grid
     */
    public PercolationUF(IUnionFind finder, int size){
        myGrid = new boolean[size][size];
        for (boolean[] row : myGrid)
            Arrays.fill(row, false);
        finder.initialize(size*size + 2);
        myFinder = finder;
        myOpenCount = 0;
        VTOP = size*size;
        VBOTTOM = size*size + 1;
    }

    /**
     * Open a cell (row,col) in the grid if it is in bounds and combine the sets of cells
     * @param row specifies row
     * @param col specifies column
     */
    @Override
    public void open(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        if(myGrid[row][col]){
            return;
        }
        myGrid[row][col] = true;
        if(row == 0) myFinder.union(row*myGrid.length + col, VTOP);
        if(row == myGrid.length - 1) myFinder.union(row*myGrid.length + col, VBOTTOM);
        if(inBounds(row - 1, col)) if(isOpen(row-1, col)) myFinder.union(row*myGrid.length + col, (row-1)*myGrid.length + col);
        if(inBounds(row + 1, col)) if(isOpen(row+1, col)) myFinder.union(row*myGrid.length + col, (row+1)*myGrid.length + col);
        if(inBounds(row, col - 1)) if(isOpen(row, col - 1)) myFinder.union(row*myGrid.length + col, row*myGrid.length + (col - 1));
        if(inBounds(row, col + 1)) if(isOpen(row, col + 1)) myFinder.union(row*myGrid.length + col, row*myGrid.length + (col + 1));
        myOpenCount++;
    }

    /**
     * Determine if a cell (row,col) of the grid is open
     * @param row specifies row
     * @param col specifies column
     * @return true if the cell has an Open status
     */
    @Override
    public boolean isOpen(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myGrid[row][col];
    }

    /**
     * Determine if a cell (row,col) of the grid is full
     * @param row specifies row
     * @param col specifies column
     * @return true if the cell has an Full status
     */
    @Override
    public boolean isFull(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myFinder.connected(row*myGrid.length + col, VTOP);
    }

    /**
     * Determine if a there is a path from the top to the bottom of the grid
     * @return true if the grid has the top and bottom set connected
     */
    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    /**
     * Determine number of open sites within the entire grid
     * @return number of open cells in grid
     */
    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    /**
     * Determine if (row,col) is valid for given grid
     * @param row specifies row
     * @param col specifies column
     * @return true if (row,col) on grid, false otherwise
     */
    protected boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }
}
