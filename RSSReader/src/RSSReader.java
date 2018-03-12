import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Heming Sun
 *
 */
public final class RSSReader {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSReader() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * use three array of same size to store necessary information
         */
        int[] tagIndex = { -1, -1, -1 };
        String[] tagStr = { "title", "link", "description" };
        String[] actualTag = { "", "", "" };
        /*
         * find elements in channel
         */
        for (int i = 0; i < tagIndex.length; i++) {

            tagIndex[i] = getChildElement(channel, tagStr[i]);
            if (tagIndex[i] != -1
                    && channel.child(tagIndex[i]).numberOfChildren() != 0) {
                actualTag[i] = channel.child(tagIndex[i]).child(0).label();
            } else {
                tagIndex[i] = -1;
            }
        }

        /*
         * print header
         */
        out.print("<html> <head>");

        /*
         * print title
         */
        if (tagIndex[0] != -1) {
            out.print(" <title>");

            out.print(actualTag[0]);
        } else {
            out.print(" <title>Empty Title");
        }
        out.print("</title>");
        out.println("</head> </body>");

        /*
         * print heading
         */

        if (tagIndex[0] != -1 && tagIndex[1] != -1) {
            out.print("<h1>\r\n <a href=\"" + actualTag[1] + "\">");

            out.print(actualTag[0]);
        } else if (tagIndex[1] != -1) {
            out.print("<h1>\r\n <a href=\"" + actualTag[1] + "\">Empty Title");
        } else {
            out.print("<h1>\r\n <a>Empty Title");
        }
        out.print(" </a>\r\n</h1>");

        /*
         * print description
         *
         */
        if (tagIndex[0] != -1) {
            out.print("\n<p>");

            out.print("\n" + actualTag[2]);
        } else {
            out.print(" <p>\nNo Description");
        }
        out.print("\n</p>");

        // print table start tag
        out.println("\n<table border=\"1\">");
        out.print("<tr>\r\n");
        out.print(" <th>Date</th>\r\n");
        out.print(" <th>Source</th>\r\n");
        out.print(" <th>News</th>\r\n");
        out.print("</tr>\r\n");

    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * TODO: fill in body
         */
        out.println("</table>");
        out.println("</body> </html>");

    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        /*
         * TODO: fill in body
         */
        int index = -1;
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            XMLTree temp = xml.child(i);
            if (temp.isTag()) {
                if (temp.label().equals(tag) && temp.numberOfChildren() != 0) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * hasTag denotes whether xmlTree has tags in corresponding position in
         * tagStr,
         *
         */
        boolean[] hasTag = { false, false, false, false, false };
        String[] tagStr = { "pubDate", "source", "title", "description",
                "link" };
        String[] actualTag = { "", "", "", "", "" };
        boolean hasSrc = false;
        String srcText = "";

        for (int i = 0; i < item.numberOfChildren(); i++) {

            XMLTree temp = item.child(i);
            if (temp.label().equals(tagStr[0])
                    && temp.numberOfChildren() != 0) {
                hasTag[0] = true;
                actualTag[0] = temp.child(0).label();
            } else if (temp.label().equals(tagStr[1])) {
                hasTag[1] = true;
                actualTag[1] = temp.attributeValue("url");
                if (temp.numberOfChildren() != 0) {
                    hasSrc = true;
                    srcText = temp.child(0).label();
                }
            } else if (temp.label().equals(tagStr[2])
                    && temp.numberOfChildren() != 0) {
                hasTag[2] = true;
                actualTag[2] = temp.child(0).label();
            } else if (temp.label().equals(tagStr[3])
                    && temp.numberOfChildren() != 0) {
                hasTag[3] = true;
                actualTag[3] = temp.child(0).label();
            } else if (temp.label().equals(tagStr[4])
                    && temp.numberOfChildren() != 0) {
                hasTag[4] = true;
                actualTag[4] = temp.child(0).label();
            }
        }

        /*
         * print out date
         */
        out.print("<tr>");
        out.print("\n<th>");
        if (hasTag[0]) {
            out.print(actualTag[0]);
        } else {
            out.print("No date avaliable");
        }
        out.println("</th>");

        /*
         * print out source
         */
        out.print("<th>");
        if (hasTag[1]) {
            out.print("\r\n <a href=\"" + actualTag[1] + "\">");
        }
        if (hasSrc) {
            out.print(srcText + "\r\n</a>");
        } else {
            out.print("No source avaliable \r\n</a>");
        }

        out.println(" \r\n</th>");

        /*
         * print out title
         */

        out.print("<th>");
        if (hasTag[4]) {
            out.print("\r\n <a href=\"" + actualTag[4] + "\">");
        } else {
            out.print("\r\n <a>");
        }

        if (hasTag[2] && !actualTag[2].isEmpty()) {

            out.print(actualTag[2]);
        } else if (hasTag[3] && !actualTag[2].isEmpty()) {
            out.print(actualTag[3]);
        } else {
            out.print("No title avaliable");
        }
        out.println(" </a>\r\n</th>");
        out.println("</tr>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * TODO: fill in body
         */

        SimpleWriter fileOut = new SimpleWriter1L("index.html");

        out.print("Enter a valid url:");
        String input = in.nextLine();
        XMLTree xml = new XMLTree1(input);
        while (!xml.label().equals("rss")
                || !xml.attributeValue("version").equals("2.0")) {
            out.print("Error!Enter a valid url(rss 2.0):");
            input = in.nextLine();
            xml = new XMLTree1(input);

        }

        XMLTree channel = xml.child(0);

        outputHeader(channel, fileOut);

        for (int i = 0; i < channel.numberOfChildren(); i++) {
            XMLTree temp = channel.child(i);
            if (temp.label().equals("item")) {
                processItem(temp, fileOut);
            }

        }

        outputFooter(fileOut);
        out.println("File is print to index.html");

        in.close();
        out.close();
    }

}