import java.io.IOException;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

public class test {

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas1.toStringWithCommas(n);
    }

    @Test
    private void t1() throws IOException {

    }
}
