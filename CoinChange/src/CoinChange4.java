import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import java.util.HashMap;

/**
 * Put a short phrase describing the program here.
 *
 * @author Heming Sun
 *
 */
public final class CoinChange4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    public static void CoinChange3(int[] coinList, int leftVal, HashMap< int, int[]> search) {

        if(search.contains(leftVal)) {
            return search.get(leftVal);
        }


        else {

                for (int i : coinArr) {
                    if (coinSum - i >= 0) {
                        coinNum++;
                        coinSum -= i;
                        break;
                }
            }
        }
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

        int[] coinArr = { 100, 50, 25, 10, 5, 1 };
        int[] resultArr = { 0, 0, 0, 0, 0, 0 };
        String[] nameArr = { "dollar", "half-dollar", "quarter", "dime",
                "nickel", "penny" };

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
        out.print("The coin list is: ");

        int count = 0;
        while (coinSum != 0) {
            resultArr[count] = coinSum / coinArr[count];

            coinSum = coinSum % coinArr[count];
            count++;
        }
        for (int i = 0; i < resultArr.length; i++) {
            coinNum += resultArr[i];
            if (resultArr[i] > 0) {
                out.print(" " + nameArr[i] + ":" + resultArr[i] + ";");
            }
        }
        out.println("\nThe total number of coin is " + coinNum + "!");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
