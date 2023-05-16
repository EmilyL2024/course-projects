import java.lang.Iterable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class creates a linked list that changes the most recently accessed node’s
 * data with the data in the current front of the list.
 */
public class FrontList<T> implements List<T>, Iterable<T> {
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
        list.addFirst(new DataCount <>(data , 1));
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
    public boolean contains(T data){
        Iterator <DataCount <T>> itr1 = list.iterator(); // an iterator to iterate through the entire list
        Iterator <DataCount <T>> itr2 = list.iterator(); // an iterator to trace the first element
        int count = 0; // used to check whether the current element is the first or not

        /* if the list is empty, return false */
        if(list.isEmpty()){
            return false;
        }
        else {
            DataCount <T> front = itr2.next(); //trace the first element

            /* a for loop to iterate through the list */
            while (itr1.hasNext()) {
                DataCount<T> n = itr1.next(); //the current node

                /* if the current element is the first, increment the count and return true*/
                if (count == 0) {
                    if (n.getData().equals(data)) {
                        n.increment(n.getCount());
                        return true;
                    }
                }
                else {
                    /* if the inspected element is in the list*/
                    if (n.getData().equals(data)) {
                        n.increment(n.getCount()); //increment the count

                        /* exchange the data and the count in the current node with the first node*/
                        T temp = front.getData();
                        front.setData(n.getData());
                        n.setData(temp);

                        int c = front.getCount();
                        front.setCount(n.getCount());
                        n.setCount(c);

                        return true;
                    }
                }
                count++; //update the count
            }
        }

        /* if the inspected element is not in the list, return false */
        return false;
    }


    /**
     *  This class overrides the iterator method to make the list iterable
     */
    private class FLIterator implements Iterator<T> {
        private Iterator<DataCount<T>> it = list.iterator(); // create an iterator for front list


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
        return new FrontList.FLIterator();
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
