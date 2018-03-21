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
public final class CoinChange3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     *
     * public static CoinChange3(int[] coinList, int leftVal, H) { }
     */
    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        int[] coinArr = { 100, 50, 25, 10, 5, 1 };
        out.print("Enter the amount of money to make exchange for(in cents): ");
        String userInput = in.nextLine();
        int coinNum = 0;

        /*
         * check if the name is empty
         */
        while (userInput.isEmpty()) {
            out.println("Error! Number should not be empty!");
            out.print(
                    "Enter the amount of money to make exchange for(in cents): ");
            userInput = in.nextLine();
        }
        int coinSum = Integer.parseInt(userInput);

        while (coinSum != 0) {
            for (int i : coinArr) {
                if (coinSum - i == 0) {
                    coinNum++;
                    coinSum -= i;
                    break;
                } else if (coinSum - i > 0) {
                    coinNum++;
                    coinSum -= i;
                    break;
                }
            }
        }

        out.println("The total number of coin is " + coinNum + "!");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
