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
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the CSE department criteria for
     * a valid password. Prints an appropriate message to the given output
     * stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        boolean status = true;
        int sufficeCond = 0;
        if (s.length() < 6) {
            status = false;
        } else {

            if (containsUpperCaseLetter(s)) {
                sufficeCond++;
            }
            if (containsLowerCaseLetter(s)) {
                sufficeCond++;
            }
            if (containsDigit(s)) {
                sufficeCond++;
            }
            if (containsSpecialCharacters(s)) {
                sufficeCond++;
            }

        }
        if (status && sufficeCond >= 3) {
            out.println("The password is qualified!");
        } else {
            out.println("The password is unqualified!");
        }

    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsSpecialCharacters(String s) {
        boolean status = false;
        String spChar = "!@#$%^&*()_-+={}[]:;,.?";

        for (int i = 0; i < s.length(); i++) {

            if (spChar.contains((s.substring(i, i + 1)))) {
                status = true;
                break;
            }

        }
        return status;
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        boolean status = false;
        for (int i = 0; i < s.length(); i++) {

            if (Character.isUpperCase(s.charAt(i))) {
                status = true;
                break;
            }

        }
        return status;
    }

    /**
     * Checks if the given String contains an digit.
     *
     * @param s
     *            the String to check
     * @return true if s contains an digit, false otherwise
     */
    private static boolean containsDigit(String s) {
        boolean status = false;
        for (int i = 0; i < s.length(); i++) {

            if (Character.isDigit(s.charAt(i))) {
                status = true;
                break;
            }

        }
        return status;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        boolean status = false;
        for (int i = 0; i < s.length(); i++) {

            if (Character.isLowerCase(s.charAt(i))) {
                status = true;
                break;
            }

        }
        return status;
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
        out.print("Enter a string to check: ");
        String s = in.nextLine();
        checkPassword(s, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
