/**
 * Created by test on 2/2/15.
 */
public class Percolation {
    private int size;
    private boolean[][] grid;
    private WeightedQuickUnionUF percolate;

    public Percolation(int N) {
        if (N <= 0) throw new java.lang.IllegalArgumentException("N has to be greater than or equal to 1.");

        size = N;
        grid = new boolean[N+1][N+1];
        percolate = new WeightedQuickUnionUF(N*N+2);

        //connect virtual top row (i.e., 0) to top row
        for (int i = 1; i <= N; i++)
            percolate.union(0, i);

        //connect virtual bottom row (i.e., N+1) to bottom row
        for (int i = 1; i <= N; i++)
            percolate.union(size*size+1, N*(N-1) + i);
        System.out.println("isFull(1,1): " + isFull(1, 1));

    }

    public void open(int i, int j) {
        if (i <= 0 || i > size) throw new IndexOutOfBoundsException("row index i out of bounds");
        else if (j <= 0 || j > size) throw new IndexOutOfBoundsException("column index j out of bounds");

        if (isOpen(i, j))
            return;

        grid[i][j] = true;

        int currentWQUIndex = weightedQuickUnionIndex(i, j);

        if (i > 1 && isOpen(i-1, j)) //north
            percolate.union(weightedQuickUnionIndex(i-1, j), currentWQUIndex);
        if (j < size && isOpen(i, j+1)) //east
            percolate.union(weightedQuickUnionIndex(i, j+1), currentWQUIndex);
        if (i < size && isOpen(i+1, j)) //south
            percolate.union(weightedQuickUnionIndex(i+1, j), currentWQUIndex);
        if (j > 1 && isOpen(i, j-1)) //west
            percolate.union(weightedQuickUnionIndex(i, j-1), currentWQUIndex);
    }

    public boolean isOpen(int i, int j) {
        if (i <= 0 || i > size) throw new IndexOutOfBoundsException("row index i out of bounds");
        else if (j <= 0 || j > size) throw new IndexOutOfBoundsException("column index j out of bounds");

        return grid[i][j];
    }

    public boolean isFull(int i, int j) {
        if (i <= 0 || i > size) throw new IndexOutOfBoundsException("row index i out of bounds");
        else if (j <= 0 || j > size) throw new IndexOutOfBoundsException("column index j out of bounds");

        if (isOpen(i, j) && percolate.connected(0, weightedQuickUnionIndex(i, j))) {
            return true;
        }

        return false;
    }

    public boolean percolates() {
        return percolate.connected(0, size*size+1);
    }

    private int weightedQuickUnionIndex(int i, int j) {
        i -= 1;
        return(i*size+j);
    }

    public static void main(String[] args) {
    Percolation perc = new Percolation(1);
        System.out.println("percolates: " + perc.percolates());

    }
}
