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
public final class HelloJack {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private HelloJack() {
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

        out.print("Enter Your Name: ");
        String userInput = in.nextLine();

        /*
         * check if the name is empty
         */
        while (userInput.isEmpty()) {
            out.println("Error! Name should not be empty!");
            out.print("Enter Your Name: ");
            userInput = in.nextLine();
        }
        out.println("Hello " + userInput + "!");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
