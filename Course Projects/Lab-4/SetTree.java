
import java.util.ArrayList;

/**
 * A class that constructs a hash table with AVL trees with insertion, removal and lookup.
 */
public class SetTree<E> {
    ArrayList<AVLTree<E>> table = null;

    /**
     * Construct a hash table with AVL trees storing in it
     *
     * @param tableSize is the size for hash table
     */
    public SetTree(int tableSize) {

        /* check if the table is empty or not*/
        if(tableSize == 0){
            throw new NullPointerException("Invalid Table");
        }

        table = new ArrayList<>(tableSize); //create a hash table using array list

        /* initialize each index with an AVL tree*/
        for(int i = 0; i < tableSize; i++){
            table.add(new AVLTree<>());
        }
    }

    /**
     * Check whether the hash table contains a certain element or not
     *
     * @param value is the value to be searched in the table
     * @return true if the value is in the table
     */
    public boolean contains(E value) {

        /* check if the table is empty or not */
        if(table.size() == 0){
            throw new NullPointerException("Invalid Table");
        }

        /* get the corresponding index for the value */
        int indx = hash(value)%(table.size());

        /* check whether this index is already in the table */
        if (table.get(indx).root != null){
            if(table.get(indx).nodeContaining(value) != null){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Add an element into the table
     *
     * @param value is the value to be added in the table
     * @return true if we could add the value
     */
    public boolean add(E value) {

        /* check if the table is empty or not*/
        if(table.size() == 0){
            throw new NullPointerException("Invalid Table");
        }

        /* get the index for the value */
        int indx = hash(value)%(table.size());

        /* if the value is not in the table, add the value */
        if(table.get(indx).nodeContaining(value) != null){
            return false;
        }
        else{
            table.get(indx).add(value);
            return true;
        }

    }

    /**
     * Remove an element from the table
     *
     * @param value is the value to be removed from the table
     * @return true if we could remove the value
     */
    public boolean remove(E value) {

        /* check if the table is empty or not */
        if(table.size() == 0){
            throw new NullPointerException("Invalid Table");
        }

        /* get the index for the value */
        int indx = hash(value)%(table.size());

        /* if the value is in the table, remove it */
        if(table.get(indx).root != null && table.get(indx).nodeContaining(value) != null){
            table.get(indx).remove(value);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Get the size of the table
     *
     * @return the size of the table
     */
    public int size() {

        int size = 0;

        for(int i = 0; i < table.size(); i++){
            size = size + table.get(i).getSize(table.get(i).root);
        }

        return size;
    }

    /**
     * Get the load factor of the table
     *
     * @return the load factor of the table
     */
    public double loadFactor() {
        return (double)size()/table.size();
    }

    /**
     * Get the standard deviation of the load factor of the table
     *
     * @return the standard deviation of the load factor of the table
     */
    public double loadStd() {
        double average = (double)size()/table.size(); //calculate the average size per entry
        double difference = 0.0; //to store the difference between average and size for each entry
        double std = 0;

        /* sum the difference for each entry together */
        for(int i = 0; i < table.size(); i++){
            difference = difference + Math.pow(table.get(i).getSize(table.get(i).root) - average, 2);
        }

        /* calculate the standard deviation for the load factor */
        if(table.size() > 1) {
            std = Math.sqrt(difference /(table.size() - 1));
        }
        else{
            return 0;
        }

        return std;
    }

    /**
     * Get the hash value for the information we want to store
     *
     * @param value is the value we want to hash
     * @return the hash value
     */
    private int hash(E value){
        return value.hashCode();
    }

    /**
     * Print out the table
     *
     * @return the string representation of the table
     */
    public String toString() {
        String result = ""; //store the table
        String s = ""; //store the content of each table entry

        /* get the string representation of the table */
        for(int i = 0; i < table.size(); i++){
            if(table.get(i).root == null){
                s = "null";
            }
            else{
                s = table.get(i).stringTree(table.get(i).root);
            }
            result = result + "table[" + i + "] = " + s + "\n";
        }

        return result;
    }
}


