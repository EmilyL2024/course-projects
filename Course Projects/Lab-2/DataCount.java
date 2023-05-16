
/**
 * This class create is a generic class that stores both the data and a count for the managed lists.
 */
public class DataCount<T> {
    private T data; //store the FQDN name
    private int count; //store the count of names

    /**
     *  This is the constructor for the DataCount class to create a DataCount object with data and count
     *  @param data is the data to store in the object
     *  @param count is the count of the data
     */
    public DataCount (T data, int count){
        this.data = data;
        this.count = count;
    }


    /**
     *  This method provides the data of the object
     *
     *  @return the data of the object
     */
    public T getData() {
        return data;
    }


    /**
     *  This method provides the count of the object
     *
     *  @return the count of the object
     */
    public int getCount() {
        return count;
    }


    /**
     *  This method sets the count of the object
     *
     *  @param count is the updated count of the object
     *  @return void
     */
    public void setCount(int count) {
        this.count = count;
    }


    /**
     *  This method sets the data of the object
     *
     *  @param data is the data of the object
     *  @return void
     */
    public void setData(T data) {
        this.data = data;
    }


    /**
     *  This method updates the count of the object by 1
     *
     *  @param count is the current count of the object
     *  @return void
     */
    public void increment(int count){
        this.count = count + 1;
    }


    /**
     *  This method compares the data between two objects
     *
     *  @param other is the object being inspected
     *  @return true if the data of the two objects is same
     */
    public boolean equals(DataCount<T> other){
        if(this.data == other.data){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     *  This method overrides the toString method to provide the data and the count stored in the object
     *
     *  @return the content of the object
     */
    @Override
    public String toString(){
        String s = "[" + data + ": " + count + "]";
        return s;
    }
}
