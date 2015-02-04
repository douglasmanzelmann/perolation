/**
 * Created by test on 2/2/15.
 */
public class Percolation {
    int size;
    boolean[][] grid;
    WeightedQuickUnionUF percolate;

    public Percolation(int N) {
        size = N;
        grid = new boolean[N+1][N+1];
        percolate = new WeightedQuickUnionUF(N+2);

        //connect virtual top row (i.e., 0) to top row
        //connect virtual bottom row (i.e., n+1) to bottom row
        //probably in another method

    }

    public void open(int i, int j) {
        grid[i][j] = true;
        //also do this in the WeightedQuickUnionUF
    }

    public boolean isOpen(int i, int j) {
        return grid[i][j];
    }

    public boolean isFull(int i, int j) {
        return grid[i][j];
    }

    public boolean percolates() {
        percolate.connected(0, size+1);

        return false;
    }

    public static void main(String[] args) {
        Percolation test = new Percolation(2);

        System.out.println("Is open? " + test.isOpen(1, 1));
        System.out.println("Percolates? " + test.percolates());

        test.open(1, 1);
        test.open(2, 1);
        System.out.println("Percolates? " + test.percolates());
    }
}
