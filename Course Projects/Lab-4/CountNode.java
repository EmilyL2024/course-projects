public class CountNode<E> implements BinaryTreeNode.Visitor<E>{

    int count = 0;


    public void visit(BinaryTreeNode<E> r){
        if(r != null) {
            count++;
        }
    }

    public int getCount(){
        return count;
    }

}
