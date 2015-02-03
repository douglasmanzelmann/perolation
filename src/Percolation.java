/**
 * Created by test on 2/2/15.
 */
public class Percolation {
    boolean[][] grid;
    WeightedQuickUnionUF percolate;

    public Percolation(int N) {
        grid = new boolean[N+1][N+1];
        percolate = new WeightedQuickUnionUF(N+1);

    }

    public void open(int i, int j) {
        grid[i][j] = true;
    }

    public boolean isOpen(int i, int j) {
        return grid[i][j];
    }

    public boolean isFull(int i, int j) {
        return grid[i][j];
    }

    public boolean percolates() {

    }

    public static void main(String[] args) {

    }
}
