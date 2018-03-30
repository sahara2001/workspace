import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;

/**
 * Extension of {@code NaturalNumber2} with secondary operations implemented as
 * instance methods: add, subtract, and power.
 *
 * @author Put your name here
 *
 */
public final class NaturalNumberInstanceOps extends NaturalNumber2 {

    public static NaturalNumber ONE = new NaturalNumber1L(1);
    public static NaturalNumber ZERO = new NaturalNumber1L(0);

    /**
     * No-argument constructor.
     */
    public NaturalNumberInstanceOps() {
    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumberInstanceOps(int i) {
        super(i);
    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumberInstanceOps(String s) {
        super(s);
    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumberInstanceOps(NaturalNumber n) {
        super(n);
    }

    /**
     * Multiplies {@code this} by {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} to multiply by
     * @updates this
     * @ensures this = #this * n
     */
    @Override
    public void multiply(NaturalNumber n) {
        NaturalNumber tmpN = new NaturalNumber1L(n);
        NaturalNumber tmp = new NaturalNumber1L(this);
        NaturalNumber tmpSum = new NaturalNumber1L(this);

        int remain = n.divideBy10();
        int tmpRemain = remain;

        //case when n == 0
        if (n.compareTo(ZERO) == 0) {

        } else {

            while (tmpRemain != 0) {
                tmpSum.add(tmp);
                tmpRemain--;
            }
            this.multiply(n);
            this.add(tmpSum);
            n.multiplyBy10(remain);
            this.subtract(tmp);
        }
    }

    @Override
    public void add(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        /**
         * @decreases n
         */
        int thisLowDigit = this.divideBy10();
        int nLowDigit = n.divideBy10();
        if (!n.isZero()) {
            this.add(n);
        }
        thisLowDigit += nLowDigit;
        if (thisLowDigit >= RADIX) {
            thisLowDigit -= RADIX;
            this.increment();
        }
        this.multiplyBy10(thisLowDigit);
        n.multiplyBy10(nLowDigit);
    }

    @Override
    public void subtract(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        assert this.compareTo(n) >= 0 : "Violation of: this >= n";

        // TODO - fill in body
        int thisLow = this.divideBy10();
        int nLow = n.divideBy10();

        if (!this.isZero()) {
            if (thisLow < nLow) {
                this.decrement();
                thisLow += 10 - nLow;
            } else {
                thisLow -= nLow;
            }
            this.subtract(n);
        } else {
            thisLow -= nLow;
        }

        this.multiplyBy10(thisLow);
        n.multiplyBy10(nLow);

    }
    /*
     * for reference
     *
     * private static int power(int n, int p) { int result = 1, count = 0; if (n
     * == 0 || n == 1) { result = n; } else if (p == 1) { result = n; } else if
     * (p % 2 == 0) { result = power(n, p / 2); result *= result; } else {
     * result = power(n, (p - 1) / 2); result *= result * n; } return result; }
     */

    @Override
    public void power(int p) {
        assert p >= 0 : "Violation of: p >= 0";

        // TODO - fill in body

        if (this.compareTo(ONE) == 0 || this.compareTo(ZERO) == 0 || p == 1) {

        } else if (p % 2 == 0) {

            this.power(p / 2);
            NaturalNumber tmp3 = new NaturalNumber1L(this);
            this.multiply(tmp3);
        } else {
            NaturalNumber tmp1 = new NaturalNumber1L(this);
            this.power((p - 1) / 2);
            NaturalNumber tmp2 = new NaturalNumber1L(this);
            this.multiply(tmp2);
            this.multiply(tmp1);
        }

    }

}
