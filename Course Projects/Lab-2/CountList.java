
import javax.xml.crypto.Data;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class creates a linked list that transposes node data based on access and current counts.
 */
public class CountList<T> implements List<T>, Iterable<T>{
    protected LinkedList<DataCount <T>> list = new LinkedList <>(); // a doubly linked list storing DataCount object

    /**
     *  This method provides the size of the list
     *
     *  @return is the size of the list
     */
     public int size () {
        return list .size ();
     }


    /**
     *  This method adds a DataCount object to the list
     *
     *  @param  data is the data of the added element
     *  @return void
     */
     public void add(T data ) {
        list.add(new DataCount <>(data , 1));
     }


    /**
     *  This method verifies whether the list is empty or not
     *
     *  @return true is the list is empty
     */
     public boolean isEmpty () {
         return list .size () == 0;
     }


    /**
     *  This method determines whether the list contains a certain element or not.
     *  Then organizes the list depending on the access of the element
     *
     *  @param  data is the data of the element being inspected
     *  @return ture if the element is in the list
     */
     public boolean contains (T data) {
         Iterator<DataCount<T>> itr1 = list.iterator(); //an iterator to iterate through the entire list
         Iterator<DataCount<T>> itr2 = list.iterator(); //an iterator to trace the previous node

         int count = 0; // used to check whether there is a previous element or not

        /* if the list is empty, return false */
         if (list.isEmpty()) {
             return false;
         }

         /* a while loop to iterate through the list */
         while (itr1.hasNext()) {
             DataCount<T> n = itr1.next(); //the current node

             /* if there is no previous element and list contains the data, increment the count and return true*/
             if (count == 0) {
                if (n.getData().equals(data)) {
                    n.increment(n.getCount());
                    return true;
                }
             }
             /* if there is a previous element*/
             else {
                 DataCount<T> prev = itr2.next(); //trace the previous element

                 if (n.getData().equals(data)) {
                     n.increment(n.getCount()); //increment the count if the data is in the list

                     /* if the current count is greater than the previous count, exchange the data between two elements*/
                     if (n.getCount() >= prev.getCount()) {
                         T temp = prev.getData();
                         prev.setData(n.getData());
                         n.setData(temp);

                         int c = prev.getCount();
                         prev.setCount(n.getCount());
                         n.setCount(c);
                        }

                        return true;
                    }
                }
                count++; //update the count
            }

         /* if the data is not in the list, return false*/
            return false;



        }


    /**
     *  This class overrides the iterator method to make the list iterable
     */
    private class CLIterator implements Iterator<T> {
        private Iterator<DataCount<T>> it = list.iterator(); // create an iterator for transpose list


        /**
         *  This method determines whether the list has next element
         *
         *  @return ture if there is a next element
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }


        /**
         *  This method returns the data of the next element
         *
         *  @return the data of the next element
         */
        @Override
        public T next() {
            /* return the next data item and advance nextNode */
            return (it.next()).getData();
        }


    }

    /* method required for Iterable interface */
        @Override
        public Iterator<T> iterator() {
            return new CLIterator();
        }


    /**
     *  This method overrides the toString method to print out the list content
     *
     *  @return the content of the list
     */
    @Override
    public String toString(){
        String s = "";
        for(DataCount<T> item:list){
            s += item + " ";
        }
        return s;
    }

}

