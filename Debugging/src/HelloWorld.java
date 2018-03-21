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
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        double x = 456.0;
        double x1 = x / 100;
        double y = 100.0 * x1; //0.01 * 45600.0;

        //the 100.0*4.56 won't works because the storage of decimals cannot work perfectly

        while (x > 400.0) {
            x1 = x / 100;
            y = 100.0 * x1;
            if (x == y) {
                out.println("equal");
            } else {
                out.println("not equal");
            }
            x -= 1.0;
        }

        out.close();
    }

}
