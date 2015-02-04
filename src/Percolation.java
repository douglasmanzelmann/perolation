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
        percolate = new WeightedQuickUnionUF(N*N+2);

        //connect virtual top row (i.e., 0) to top row
        for (int i = 1; i <= N; i++)
            percolate.union(0, i);

        /*System.out.println("Top row: " + percolate.connected(0, 1));
        System.out.println("Top row: " + percolate.connected(0, 2));
        System.out.println("Top row: " + percolate.connected(0, 3));
        System.out.println("Top row: " + percolate.connected(0, 4));
        System.out.println("Top row: " + percolate.connected(0, 5));   */

        //connect virtual bottom row (i.e., n+1) to bottom row
        for (int i = 1; i <= N; i++)
            percolate.union(size*size+1, N*(N-1) + i);

        /*System.out.println("Size: " + size*size);
        System.out.println("(" + (size*size+1) +
                                    "," + (N*(N-1)+2) + "): " + percolate.connected(size*size+1, N*(N-1)+2));    */

        //probably in another method

    }

    public void open(int i, int j) {
        if (isOpen(i, j))
            return;

        grid[i][j] = true;

        //also do this in the WeightedQuickUnionUF
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
        return grid[i][j];
    }

    public boolean isFull(int i, int j) {
        return grid[i][j];
    }

    public boolean percolates() {
        return percolate.connected(0, size*size+1);
    }

    private int weightedQuickUnionIndex(int i, int j) {
        i -= 1;
        return(i*size+j);
    }

    public static void main(String[] args) {
        /*Percolation test = new Percolation(5);

        System.out.println("Percolates: " + test.percolates());
        test.open(1, 3);
        test.open(2, 3);
        test.open(3, 3);
        System.out.println("Percolates: " + test.percolates());
        test.open(4, 3);
        test.open(5, 3);

        System.out.println("Percolates: " + test.percolates()); */
    }
}
