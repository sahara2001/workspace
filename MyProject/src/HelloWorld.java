import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class HelloWorld {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HelloWorld() {
        // no code needed here
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        int result = 0;
        n.divideBy10();
        result++;
        if (!n.isZero()) {

            result += numberOfDigits(n);
        }

        return result;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static int sumOfDigits1(NaturalNumber n) {
        int result = 0;
        int tmp = n.divideBy10();

        if (!n.isZero()) {
            result += sumOfDigits1(n);
        }

        result += tmp;
        n.multiplyBy10(tmp);
        return result;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigits(NaturalNumber n) {

        int remain = n.divideBy10();

        NaturalNumber result = new NaturalNumber1L(remain);

        if (!n.isZero()) {
            result.add(sumOfDigits(n));
        }

        n.multiplyBy10(remain);

        return result;
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {

        int remain1 = n.divideBy10() / 2;

        if (!n.isZero()) {

            int remain2 = n.divideBy10();

            if (remain2 % 2 != 0) {

                remain1 += 5;

            }

            n.multiplyBy10(remain2);

            divideBy2(n);

        }

        n.multiplyBy10(remain1);

    }

    /*
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s {@code String} to be checked
     *
     * @return true if {@code s} is a palindrome, false otherwise
     *
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        boolean status = true;
        int len = s.length();
        if (len > 1) {
            if (s.charAt(0) != s.charAt(len - 1)) {
                status = false;
            }
            status = status && isPalindrome(s.substring(1, len - 1));
        }

        return status;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        NaturalNumber tmp = new NaturalNumber1L(2111);

        out.println(numberOfDigits(tmp));
        tmp = new NaturalNumber1L(234);
        out.println(sumOfDigits(tmp).toInt());
        tmp = new NaturalNumber1L(234);

        out.println(sumOfDigits1(tmp));
        tmp = new NaturalNumber1L(2111);
        divideBy2(tmp);
        out.println(tmp);
        out.print(isPalindrome("sertres"));

        out.println("Hello World!");
        out.close();
    }

}
