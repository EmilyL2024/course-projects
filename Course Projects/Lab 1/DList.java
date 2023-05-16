import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;


/**
 * This class creates a double linked list to store information
 */
public class DList<T> implements List<T>, Iterable<T> {

    protected LinkedList<T> list = new LinkedList<T>(); // a double linked list to store the valid names from the file

    /**
     *  This method provides the size of the list
     *
     *  @return is the size of the list
     */
    public int size() {
        return list.size();
    }


    /**
     *  This method adds element to the list
     *
     *  @param  data is the data of the added element
     *  @return void
     */
    public void add(T data) {
        list.add(data);
    }


    /**
     *  This method determines whether the list contains a certain element or not
     *
     *  @param  data is the data of the element being inspected
     *  @return ture if the element is in the list
     */
    public boolean contains(T data) {
        return list.contains(data);
    }


    /**
     *  This method verifies whether the list is empty or not
     *
     *  @return true is the list is empty
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }


    /**
     *  This method provides an iterator to iterate through the list
     *
     *  @return the iterator for the list
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }


    }

