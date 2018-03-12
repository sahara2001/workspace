import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Heming Sun
 *
 */
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod() {
        /*
         * Put your code for myMethod here
         */

    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {

        double squareRoot = x; //calculation start from x

        double xCoord = squareRoot;
        /*
         * error simplified from the calculation of error using squareRoot = x
         */
        double error = x - 1;

        while (error > 0.0001) {
            double y = squareRoot * squareRoot - x; //the value of function y = xCoord^2 - x;

            double slope = 2.0 * squareRoot; // the slope of function at current point

            squareRoot = xCoord = (0 - y) / slope + xCoord; //update x-coordinate using Newton's method

            y = squareRoot * squareRoot - x; //updating y

            error = Math.abs(y - 0) / x; //calculate error

        }

        return xCoord;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */

        boolean userInput = false;
        double sqrtParam; //the number to get square root of
        double sqrtResult; //stores the result of sqrt() method

        /*
         * get user input
         */
        out.print("Do you want to compute square root? : ");
        userInput = in.nextLine().equalsIgnoreCase("y");

        /*
         * compute result
         */
        while (userInput) {
            out.print("Enter a number to do square root operation: ");
            sqrtParam = in.nextDouble();

            /*
             * do calculation
             */
            sqrtResult = sqrt(sqrtParam);

            /*
             * print result
             */
            out.println("The square root (positive) of " + sqrtParam + " is "
                    + sqrtResult);

            out.print("Do you want to compute square root? : ");
            userInput = in.nextLine().equalsIgnoreCase("y");
        }

        out.println("Goodbye!");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
