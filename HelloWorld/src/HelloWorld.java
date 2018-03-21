import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld Program.
 *
 * @author Heming Sun
 */
public final class HelloWorld {
    /**
     * No-argument constructor -- private to prevent instantiation.
     */
    private HelloWorld() {
    }

    /**
     * Main Method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println("Hello World!");
        out.close();
    }

}
