import java.util.Iterator;

/**
 * A simple list interface. You can add methods, but
 * do NOT change what is already here.
 */
public interface List<T> extends Iterable<T> {
    /**
     * Adds the given item at the beginning of the list.
     */
    void add(T item);

    /**
     * Returns true if the item exists in the list, false otherwise
     */
    boolean contains(T item);

    /**
     * Returns the number of items currently in the list.
     */
    int size();

    /**
     * Returns whether the list is empty or not.
     */
    boolean isEmpty();
}


