import java.util.Iterator;
import java.util.LinkedList;


/**
 * This class creates a sorted linked list to store information in ascending order
 */
public class SortedList<T extends Comparable<? super T>> implements List<T>, Iterable<T> {
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
     *  This method adds element to the list in ascending order
     *
     *  @param  data is the data of the added element
     *  @return void
     */
    public void add(T data){

        boolean check = false; //check if the list has added element or not
        int size = list.size(); //list size


        /* if the list is empty, add data */
        if (list.isEmpty()) {
            list.add(data);
            check = true;
        }
        else{

            /* add data in ascending order */
            for (int i = 0; i < size; i++) {
                if (list.get(i).compareTo(data) > 0){
                    list.add(i, data);
                    check = true;
                    break;
                }
            }

        }


        /* if none of the data in the list is greater than the inspected one, add at end */
        if (!check) {
            list.addLast(data);
        }


    }


    /**
     *  This method determines whether the list contains a certain element or not
     *
     *  @param  data is the data of the element being inspected
     *  @return ture if the element is in the list
     */
    public boolean contains(T data) {

        boolean check = false; //whether the list contains the element or not
        boolean c = false; //whether the element inspected is greater than all the element in the list

        /* if the list is empty, it contains no data */
        if (list.isEmpty()) {
           return false;
        }
        else {
            for (T item : list) {

                /* check if the data is in the list */
                if (data.compareTo(item) == 0) {
                    check = true;
                    c = true;
                    break;
                }

                /* if current item is smaller than the inspected data, then the data is not in the list */
                if (data.compareTo(item) < 0) {
                    c = true;
                    break;
                }

            }
        }

        /* if all the items in the list are smaller than the inspected one, data is not in the list  */
        if (!c) {
            return false;
        }

        return check;


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
    public Iterator<T> iterator() {
        return list.iterator();
    }



}
