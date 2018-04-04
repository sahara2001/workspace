import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 */
public final class Queue1LSort1 extends Queue1L<String> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort1() {
        super();
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * (q * <removeMin>) is permutation of #q  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

        Queue<String> tmp = new Queue1L<>();
        String min = q.dequeue();
        String tmpMin = min;
        tmp.enqueue(min);

        while (q.length() != 0) {
            tmpMin = q.dequeue();
            min = (order.compare(min, tmpMin) < 0) ? min : tmpMin;

            tmp.enqueue(tmpMin);
        }

        int count = 1;
        while (tmp.length() != 0) {
            String temp = tmp.dequeue();
            if (temp.equals(min) && count != 0) {
                count--;
                continue;
            } else {
                q.enqueue(temp);
            }
        }

        /*
         * This line added just to make the program compilable. Should be
         * replaced with appropriate return statement.
         */
        return min;
    }

    @Override
    public void sort(Comparator<String> order) {
        assert order != null : "Violation of: order is not null";
        Queue<String> tmp = new Queue1L<>();

        while (this.length() != 0) {
            tmp.enqueue(this.removeMin(this, order));

        }
        while (tmp.length() != 0) {
            this.enqueue(tmp.dequeue());
        }
    }

}
