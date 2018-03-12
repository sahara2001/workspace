import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Project 3 for CSE 2221
 *
 *
 * @author Heming Sun
 */

public class ABCDGuesser2 {

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String inputString;
        double userInput; //used to store user input
        boolean canParseDouble = false;

        //prompt user for input
        out.print("Enter a positive real number: ");
        inputString = in.nextLine();
        canParseDouble = FormatChecker.canParseDouble(inputString); //store whether the input can be parsed double
        userInput = (canParseDouble) ? Double.parseDouble(inputString) : 0.0;

        //loop while the input in invalid
        while (userInput <= 0.0) {
            out.print("Please enter a positive real number: ");
            userInput = in.nextDouble();
            canParseDouble = FormatChecker.canParseDouble(inputString);
            userInput = (canParseDouble) ? Double.parseDouble(inputString)
                    : 0.0;
        }
        return userInput;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        String inputString;
        double userInput; //used to store user input
        boolean canParseDouble = false;

        //prompt user to input
        out.print("Enter a positive real number: ");
        inputString = in.nextLine();
        canParseDouble = FormatChecker.canParseDouble(inputString);
        userInput = (canParseDouble) ? Double.parseDouble(inputString) : 0.0;

        //add condition that the numeric input should not be one
        while (userInput <= 0.0 || Double.compare(userInput, 1.0) == 0) {
            out.print("Please enter a positive real number: ");
            userInput = in.nextDouble();
            canParseDouble = FormatChecker.canParseDouble(inputString);
            userInput = (canParseDouble) ? Double.parseDouble(inputString)
                    : 0.0;
        }
        return userInput;
    }

    /**
     * print a prompt statement ask for a base
     *
     *
     * @param out
     *            the output stream
     * @return void
     */
    public static void promptForInput(SimpleWriter out) {
        out.println("Enter a base below.");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        double valToEstimate; //target to estimate
        double relativeErr = 1; //relative error

        double base1; //four bases of exponential estimate function
        double base2;
        double base3;
        double base4;

        /*
         * an array of exponents to try
         */
        double[] exponents = { -5, -4, -3, -2, -1, -1.0 / 2.0, -1.0 / 3.0,
                -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1, 2, 3, 4, 5 };

        /*
         * get input values
         */
        out.println("Enter the value to estimate below.");
        valToEstimate = getPositiveDouble(in, out); //get target value
        double estimation = -valToEstimate;

        promptForInput(out);
        base1 = getPositiveDoubleNotOne(in, out);
        promptForInput(out);
        base2 = getPositiveDoubleNotOne(in, out);
        promptForInput(out);
        base3 = getPositiveDoubleNotOne(in, out);
        promptForInput(out);
        base4 = getPositiveDoubleNotOne(in, out);

        int[] index = { 0, 0, 0, 0 }; //an array of exponents of each base
        int[] bestIndex = { 0, 0, 0, 0 }; // storing the exponents that produces best estimation
        /*
         * start estimation
         */

        for (index[0] = 0; index[0] < exponents.length; index[0]++) {

            double tempErr = relativeErr;
            double tempEst = estimation;

            for (index[1] = 0; index[1] < exponents.length; index[1]++) {

                for (index[2] = 0; index[2] < exponents.length; index[2]++) {

                    for (index[3] = 0; index[3] < exponents.length; index[3]++) {

                        /*
                         * calculate estimation and relative error of current
                         * set of perimeters
                         */
                        tempEst = Math.pow(base4, exponents[index[3]])
                                * Math.pow(base3, exponents[index[2]])
                                * Math.pow(base2, exponents[index[1]])
                                * Math.pow(base1, exponents[index[0]]);
                        tempErr = Math.abs(valToEstimate - tempEst)
                                / valToEstimate;
                        /*
                         * update better solution
                         */
                        if (tempErr < relativeErr) {
                            relativeErr = tempErr;
                            estimation = tempEst;
                            bestIndex = index.clone();
                        }

                    }

                }

            }

        }

        /*
         * print result
         */
        out.println("The bases are: " + base1 + " " + base2 + " " + base3 + " "
                + base4 + " ");
        out.println("The exponents of the best extimation are: "
                + exponents[bestIndex[0]] + " " + exponents[bestIndex[1]] + " "
                + exponents[bestIndex[2]] + " " + exponents[bestIndex[3]]);
        out.println("The best approximation is: " + estimation
                + " with relative error: " + relativeErr);

    }

}
