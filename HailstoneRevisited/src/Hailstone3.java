import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Heming Sun
 *
 */
public final class Hailstone3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone3() {
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
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        NaturalNumber tempNum = new NaturalNumber1L(n);
        final NaturalNumber one = new NaturalNumber1L(1);
        final NaturalNumber two = new NaturalNumber1L(2);
        final NaturalNumber zero = new NaturalNumber1L(0);
        final NaturalNumber three = new NaturalNumber1L(3);

        NaturalNumber maximum = new NaturalNumber1L(n); //max number

        int length = 1;
        while (tempNum.compareTo(one) > 0) {
            out.print(tempNum.toString() + " ");
            NaturalNumber temp = new NaturalNumber1L(tempNum);
            if (temp.divide(two).equals(zero)) {
                tempNum = temp;
            } else {
                tempNum.multiply(three);
                tempNum.add(one);
            }

            if (maximum.compareTo(tempNum) < 0) {
                maximum = new NaturalNumber1L(tempNum);
            }
            length++;
        }
        out.print(tempNum.toString());
        out.print("\r\nLength: " + length);
        out.println("\r\nMaximum: " + maximum.toString());

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

        out.print("Do you want to generate series? (y/n)");
        while (in.nextLine().equalsIgnoreCase("y")) {

            String input = "";
            int inputNum = -1;
            while (!FormatChecker.canParseInt(input) || inputNum <= 0) {
                out.print("Enter a natual number greater than 0:");
                input = in.nextLine();
                if (FormatChecker.canParseInt(input)) {
                    inputNum = Integer.parseInt(input);
                }
            }
            NaturalNumber defaultNum = new NaturalNumber1L(inputNum);
            /*
             * Put your main program code here; it may call myMethod as shown
             */
            generateSeries(defaultNum, out);
            out.println("Original: " + defaultNum.toString());
            /*
             * Close input and output streams
             */

            out.print("Do you want to generate series? (y/n)");
        }
        in.close();
        out.close();
    }

}
