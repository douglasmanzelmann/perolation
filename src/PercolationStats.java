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
                int i = StdRandom.uniform(1, N+1);
                int j = StdRandom.uniform(1, N+1);

                while(perc.isOpen(i, j)) {
                    i = StdRandom.uniform(1, N+1);
                    j = StdRandom.uniform(1, N+1);
                }

                perc.open(i, j);
                openSites[n]++;
            }
        }
    }

    public double mean() {
        double meanSum=0;

        for (int i = 0; i < totalTests; i++) {
            meanSum += ((double)openSites[i] / totalSites);
        }

        return meanSum/totalTests;
    }

    public double stddev() {
        double stdDevSum = 0;
        double mean = mean();

        for (int i = 0; i < totalTests; i++) {
            stdDevSum += Math.pow((((double)openSites[i] / totalSites) - mean), 2);
        }

        return Math.sqrt(stdDevSum / (totalTests - 1));
    }

    public double confidenceLo() {
        return(mean() - (1.96*stddev())/ Math.sqrt(totalTests));
    }

    public double confidenceHi() {
        return(mean() + (1.96*stddev())/ Math.sqrt(totalTests));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]); // NxN grid
        int T = Integer.parseInt(args[1]); // T experiments

        PercolationStats test = new PercolationStats(N, T);
        System.out.println("Mean = " + test.mean());
        System.out.println("stddev = " + test.stddev());
        System.out.println("95% confidence interval = " +
                            test.confidenceLo() + ", " +
                            test.confidenceHi());
    }
}
