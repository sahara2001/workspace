import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 *
 * @author: Heming Sun
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     *
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        double absError = Math.PI;

        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        /*
         * Declare counters and initialize them
         */
        int ptsInInterval = 0, ptsInSubinterval = 0;
        /*
         * Create pseudo-random number generator
         */
        Random rnd = new Random1L();

        for (int i = 0; i < 10000; i++) {
            rnd.nextDouble();
        }

        /*
         * Generate points and count how many fall in [0.0,0.5) interval
         */

        double estimate = (100.0 * ptsInSubinterval) / ptsInInterval;
        while (absError > 0.01 && ptsInInterval < n) {
            /*
             * Generate pseudo-random number in [0.0,1.0) interval
             */
            double x = 2 * rnd.nextDouble();
            double y = 2 * rnd.nextDouble();
            /*
             * Increment total number of generated points
             */
            ptsInInterval++;

            /*
             * Check if point is in [0.0,0.5) interval and increment counter if
             * it is
             */
            if (Math.pow(x - 1, 2) + Math.pow(y - 1, 2) < 1) {
                ptsInSubinterval++;
            }
            if (ptsInSubinterval > 0) {
                estimate = (4.0 * ptsInSubinterval) / ptsInInterval;
                absError = Math.abs(Math.PI - estimate);
            }
        }
        /*
         * Estimate percentage of points generated in [0.0,1.0) interval that
         * fall in the [0.0,0.5) subinterval
         */

        output.println("Estimate of actual PI: " + estimate + "\nabsError: "
                + absError);
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}
