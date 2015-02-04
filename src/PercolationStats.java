/**
 * Created by test on 2/2/15.
 */
public class PercolationStats {
    private int[] openSites;
    private int totalSites;
    private int totalTests;

    public PercolationStats(int N, int T) {
        totalTests = T;
        openSites = new int[T];
        totalSites = N*N;

        for (int n = 0; n < T; n++) {
            Percolation perc = new Percolation(N);

            while(!perc.percolates()) {
                int i=0;
                int j=0;

                while(perc.isOpen(i, j)) {
                    i = StdRandom.uniform(1, N+1);
                    j = StdRandom.uniform(1, N+1);
                }

                perc.open(StdRandom.uniform(1, N+1), StdRandom.uniform(1, N+1));
                openSites[n]++;
            }
        }
    }

    public double mean() {
        double meanSum=0;

        for (int i = 0; i < totalTests; i++) {
            meanSum += (double)openSites[i] / totalSites;
            //System.out.println((double)openSites[i] / totalSites);
            System.out.println(openSites[i]);
        }

        return meanSum/totalTests;
    }

    //public double stddev() {}

    //public double confidenceLo() {}

    //public double confidenceHi() {}

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]); // NxN grid
        int T = Integer.parseInt(args[1]); // T experiments

        PercolationStats test = new PercolationStats(N, T);
        System.out.println("Mean = " + test.mean());
    }
}
