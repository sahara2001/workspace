import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Heming Sun
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression. The expression tree contains <plus>,
     * <minus>, <times>, <divide>, and <number> tags and <expression> as root
     * tag
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        int result = Integer.MAX_VALUE;

        /*
         * test if expression tree is valid
         */
        if (exp.numberOfChildren() != 0) {
            /*
             * no root tag
             */

            switch (exp.label()) {
                case "plus":
                    result = evaluate(exp.child(0)) + evaluate(exp.child(1));
                    break;
                case "minus":
                    result = evaluate(exp.child(0)) - evaluate(exp.child(1));
                    break;
                case "times":
                    result = evaluate(exp.child(0)) * evaluate(exp.child(1));
                    break;
                case "divide":
                    result = evaluate(exp.child(0)) / evaluate(exp.child(1));
                    break;
                default:
                    // should print error message
                    break;

            }

        }

        else if (exp.label().equals("number")) {
            /*
             * condition can be deleted because tree should be valid
             */
            result = Integer.parseInt(exp.attributeValue("value"));
        }

        return result;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        out.println("Invalid file location!");

        in.close();
        out.close();
    }

}
