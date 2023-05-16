
public class LinkedBinaryTreeNode<E> implements BinaryTreeNode<E> {
    protected E data; // the data of the node
    protected LinkedBinaryTreeNode<E> parent = null; //parent of the node
    protected LinkedBinaryTreeNode<E> left = null; //left child of the node
    protected LinkedBinaryTreeNode<E> right = null; //right child of the node

    /**
     * Constructs a node as the root of its own one-element tree.
     * This is the only public constructor.  The only trees that
     * clients can make directly are simple one-element trees.
     *
     * @param data the data stored in the node
     */
    public LinkedBinaryTreeNode(E data) {
        this.data = data;
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the data of the node
     */
    public E getData() {
        return data;
    }

    /**
     * Modifies the data stored in this node.
     *
     * @param data the new data for the node
     * @return void
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Returns the parent of this node, or null if this node is a root.
     *
     * @return the parent node of the given node
     */
    public BinaryTreeNode<E> getParent() {
      return parent;
    }

    /**
     * Modifies the left child of the node
     *
     * @param child the new child to be set on the left of the node
     * @exception IllegalArgumentException throw the exception if we are trying to set the parent to its child's left
     * @return void
     */
    public void setLeft(BinaryTreeNode<E> child){

        /* determine if the child we want to set left is its parent or not */
        for(LinkedBinaryTreeNode<E> n = this; n!= null; n = n.parent){
            if(n == child){
                throw new IllegalArgumentException();
            }
        }

        LinkedBinaryTreeNode<E> childNode = (LinkedBinaryTreeNode<E>) child;

        /* remove the left child from current node */
        if(this.left != null){
            left.parent = null;
        }

        /* remove the child from its parent, and set its parent to current node */
        if(childNode != null){
            childNode.removeFromParent();
            childNode.parent = this;
        }
         /* set current node's left to the child node */
        this.left = childNode;
    }

    /**
     * Returns the left child of this node, or null if it does
     * not have one.
     *
     * @return the left child of the node
     */
    public BinaryTreeNode<E> getLeft() {
      return left;
    }

    /**
     * Modifies the right child of the node
     *
     * @param child the new child to be set on the right of the node
     * @exception IllegalArgumentException throw the exception if we are trying to set the parent to its child's right
     * @return void
     */
    public void setRight(BinaryTreeNode<E> child){

        /* determine if the child we want to set right is its parent or not */
        for(LinkedBinaryTreeNode<E> n = this; n!= null; n = n.parent){
            if(n == child){
                throw new IllegalArgumentException();
            }
        }

        LinkedBinaryTreeNode<E> childNode = (LinkedBinaryTreeNode<E>) child;

        /* remove the right child from current node */
        if(this.right != null){
            right.parent = null;
        }

        /* remove the child from its parent, and set its parent to current node */
        if(childNode != null){
            childNode.removeFromParent();
            childNode.parent = this;
        }

        /* set current node's right to the child node */
        this.right = childNode;
    }

    /**
     * Returns the right child of this node, or null if it does
     * not have one.
     *
     * @return the right child of the node
     */
    public BinaryTreeNode<E> getRight() {
      return right;
    }

    /**
     * Remove the child from its parentn nothing if the child has no parent
     *
     * @return void
     */
    public void removeFromParent(){

        if(parent != null){
            /* if the child is on the left side of the parent */
            if(parent.left == this){
                parent.left = null;
            }
            /* if the child is on the right side of the parent */
            else if(parent.right == this){
                parent.right = null;
            }
            /* set the child's parent to null */
            this.parent = null;
        }
    }

    /**
     * Traverse the tree in preorder
     *
     * @param visitor to visit the node and do actions as needed
     * @return void
     */
    public void traversePreorder(Visitor visitor){

        /*process first, then left, and then right*/
        visitor.visit(this);

        if(left != null){
            left.traversePreorder(visitor);
        }

        if(right != null){
            right.traversePreorder(visitor);
        }
    }

    /**
     * Traverse the tree in postorder
     *
     * @param visitor to visit the node and do actions as needed
     * @return void
     */
    public void traversePostorder(Visitor visitor){

        /*go left first, then right, process in the last*/

        if(left != null){
            left.traversePostorder(visitor);
        }

        if(right != null){
            right.traversePostorder(visitor);
        }

        visitor.visit(this);
    }

    /**
     * Traverse the tree in inorder
     *
     * @param visitor to visit the node and do actions as needed
     * @return void
     */
    public void traverseInorder(Visitor visitor){

        /*go left first, then process, then go right */
        if(left != null){
            left.traverseInorder(visitor);
        }

        visitor.visit(this);

        if(right != null){
            right.traverseInorder(visitor);
        }

    }















}


