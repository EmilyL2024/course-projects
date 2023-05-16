import java.util.Comparator;

/**
 * A binary search tree class with insertion, removal and lookup.
 * A comparator is used to order the items in the tree.  All tree
 * items must be distinct according to the comparator.  If no comparator
 * is supplied the natural order of tree elements is used.
 */
public class BinarySearchTree<E> {

    /**
     * Root of the tree.
     *
     */
    protected BinaryTreeNode<E> root = null;

    /**
     * Comparator used to order the items in the tree.  If null,
     * the natural order of the items will be used.
     */
    private Comparator<E> comparator;

    /**
     * Constructs an empty BST that can only accept Comparables
     * as items.
     *
     * @return void
     */
    public BinarySearchTree() {
        this(null);
    }

    /**
     * Constructs a BST that orders its items according to the
     * given comparator.
     *
     * @param c is a given comparator to compare items in the tree
     * @return void
     */
    public BinarySearchTree(Comparator<E> c) {
        comparator = c;
    }

    /**
     * Returns the root of the tree.
     *
     * @return is the root of the tree
     */
    protected BinaryTreeNode getRoot() {
        return root;
    }

    /**
     * Makes the given node the new root of the tree.
     *
     * @param node is the node we want to set as the root of the tree
     * @return void
     */
    protected void setRoot(BinaryTreeNode<E> node) {
        /*break the old link*/
        if (node != null) {
            node.removeFromParent();
        }
        /*set the node as root*/
        root = node;
    }

    /**
     * Add an item into the tree, set it as the root if the tree is empty
     *
     * @param data the new data we want to add into the tree
     * @return void
     */
    public void add(E data) {

        /* if the tree is empty, create root */
        if (root == null) {
            root = new LinkedBinaryTreeNode<>(data);
        }

        /* get the root of the tree */
        BinaryTreeNode<E> n = root;

        while (true) {
            int result = compare(data, n.getData());

            /* if the data is duplicate, do nothing */
            if (result == 0) {
                n.setData(data);
                return;
            }
            /* set the new data to the right if it is greater than the right child */
            else if (result > 0) {
                if (n.getRight() == null) {
                    n.setRight(new LinkedBinaryTreeNode<>(data));
                    return;
                }
                n = n.getRight();
            }
            /* set the new data to the left if it is smaller than the left child */
            else {
                if (n.getLeft() == null) {
                    n.setLeft(new LinkedBinaryTreeNode<>(data));
                    return;
                }
                n = n.getLeft();
            }
        }
    }

    /**
     * Remove an item from the tree
     *
     * @param data the data we want to remove from the tree
     * @return void
     */
    public void remove(E data) {
        BinaryTreeNode<E> node = nodeContaining(data);

        /* if the node is null, return */
        if (node == null) {
            return;
        }
        /* if the node has two children, find its predecessor */
        else if (node.getLeft() != null && node.getRight() != null) {
            BinaryTreeNode<E> predecessor = predecessor(node);
            /* set the data of the node as its predecessor */
            node.setData(predecessor.getData());
            /* set the node as its predecessor for removal */
            node = predecessor;
        }

        /* get the node we are trying to pull up after removal*/
        BinaryTreeNode<E> pullUp = null;

        if (node.getLeft() == null) {
            pullUp = node.getRight();
        } else {
            pullUp = node.getLeft();
        }

        /* remove the node */
        if (node == root) {
            setRoot(pullUp);
        } else if (node.getParent().getLeft() == node) {
            node.getParent().setLeft(pullUp);
        } else {
            node.getParent().setRight(pullUp);
        }

    }

    /**
     * Compare the item in the tree
     *
     * @param d1 is the first item we want to compare
     * @param d2 is the second item we want to compare
     * @return an integer value to indicate whether d1 is greater than, less than, or equal to d2.
     */
    public int compare(E d1, E d2) {

        /* use comparator to compare the two data */
        if (comparator == null) {
            return ((Comparable<E>) d1).compareTo(d2);
        } else {
            return comparator.compare(d1, d2);
        }

    }

    /**
     * Fide the predecessor of the given node, return null if the node is null
     *
     * @param node is the node we are trying to find predecessor for
     * @return is the predecessor of the node
     */
    protected BinaryTreeNode<E> predecessor(BinaryTreeNode<E> node) {

        /* get the left node of the node we are trying to find predecessor for */
        BinaryTreeNode<E> n = node.getLeft();

        /* go all the way to right to find the predecessor */
        if (n != null) {
            while (n.getRight() != null) {
                n = n.getRight();
            }
        }

        return n;
    }

    /**
     * Find whether the tree contains the item or not to avoid duplicate. Return null if the item is not in the tree
     *
     * @param data is the data we want to see if the tree contains or not
     * @return the node containing the data
     */
    public BinaryTreeNode<E> nodeContaining(E data) {

        /* starting from the root, search the tree to find the value */
        for (BinaryTreeNode<E> n = root; n != null; ) {
            int result = compare(data, n.getData());
            if (result == 0) {
                return n;
            } else if (result < 0) {
                n = n.getLeft();
            } else {
                n = n.getRight();
            }
        }

        return null;
    }

    /**
     * Return the size of the tree, return 0 if the tree is empty
     *
     * @param n is the root of the tree
     * @return the size of the tree
     */
    public int getSize(BinaryTreeNode<E> n){
        CountNode<E> visitor = new CountNode<E>(); //create a visitor that could count the number of nodes in the tree

        /* if the tree is not empty, traverse it to get size */
        if (n != null) {
            n.traversePreorder(visitor);
        }

        return  visitor.getCount();
    }

    /**
     * Return the height of the tree, return -1 if the node is null
     *
     * @param n is the node we are trying to find height for
     * @return the height of the given node
     */
    public int getHeight(BinaryTreeNode<E> n){

        /* if the node is null, return -1 */
        if (n == null){
            return -1;
        }

        /* recursive calls to traverse the left and right subtrees of the given node */
        int leftHeight = 1 + getHeight(n.getLeft());
        int rightHeight = 1+getHeight(n.getRight());

        /* return the higher height from the subtree */
        if (leftHeight <= rightHeight){
            return rightHeight;
        }
        else{
            return leftHeight;
        }
    }

    String t = ""; // a string that represent the binary search tree

    /**
     * Return the string that represents the binary search tree, null if the tree is empty
     *
     * @param r is the root of the tree
     * @return the string representation of the binary search tree
     */
    public String stringTree(BinaryTreeNode<E> r) {

        /* return null if the three is empty */
        if (r == null){
            return t;
        }

        /* transform the binary search tree into a string */
        t = t + "(";
        if(r.getLeft() != null){
            stringTree(r.getLeft());
        }


        t = t + r.getData();


        if(r.getRight() != null){
            stringTree(r.getRight());
        }

        t = t + ")";


        return t;

    }


}


