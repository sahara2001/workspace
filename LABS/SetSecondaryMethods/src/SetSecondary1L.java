import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body
        Set<T> tmp = new SetSecondary1L<>();
        Set<T> removed = new SetSecondary1L<>();
        for (T i : this) {
            if (!s.contains(i)) {
                tmp.add(i);
            } else {
                removed.add(i);
            }
        }
        this.transferFrom(tmp);
        return removed;

    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body

        Set<T> intersection = new SetSecondary1L<>();

        //add i not in this to this, else add to intersection adn reset s
        for (T i : s) {
            if (!this.contains(i)) {
                this.add(i);
            } else {
                intersection.add(i);
            }
        }

        s.transferFrom(intersection);

    }

    /**
     * Reports whether {@code this} is a subset of {@code s}. (A is a subset of
     * B exactly when every element of A is also an element of B.)
     *
     * @param s
     *            the second set
     * @return whether {@code this} is a subset of {@code s}
     * @ensures isSubset = (this is subset of s)
     */
    @Override
    public boolean isSubset(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body
        Set<T> intersection = new SetSecondary1L<>();
        boolean status = true;
        //add i not in this to this, else add to intersection adn reset s
        for (T i : this) {
            if (!s.contains(i)) {
                status = false;
                break;
            }
        }
        status = status && s.size() >= this.size();

        return status;

    }

}
