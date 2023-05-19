import java.util.Comparator;

/**
 * A simple AVL tree class.
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    /**
     * Constructs an empty AVL that only accepts Comparables.
     *
     * @return void
     */
    public AVLTree() {
        this(null);
    }

    /**
     * Constructs an AVL that orders its items according
     * to the given comparator.
     *
     * @return void
     */
    public AVLTree(Comparator<E> c){
        super(c);
    }

    /**
     * Add an item into the tree, then rebalance the tree from the added node
     *
     * @param data the new data we want to add into the tree
     * @return void
     */
    @Override
    public void add(E data) {
        super.add(data);
        BinaryTreeNode<E> n = nodeContaining(data);
        if (n != null) {
            rebalance(n);
        }
    }

    /**
     * Remove an item from the tree, rebalance the tree from the parent of the removed node after removal
     *
     * @param data the data we want to remove from the tree
     * @return void
     */
    @Override
    public void remove (E data) {

        BinaryTreeNode<E> n = nodeContaining(data);

        if (n != null) {

            /* if the node removed has two children, get its predecessor's parent */
            if (n.getLeft() != null && n.getRight() != null){
                n = predecessor(n);
                n = n.getParent();
            }
            /* else, get the node's parent*/
            else{
                n = n.getParent();
            }

            super.remove(data);

            rebalance(n);

        }
    }

    /**
     * Balance the tree to make sure that the height difference between two subtrees is less than 2
     *
     * @param node is the node we are rebalancing from
     * @return void
     */
    protected void rebalance (BinaryTreeNode<E> node){

        while (node != null){

            /*get the height difference between the left subtree and right subtree*/
            int heightDiff = getHeight(node.getLeft()) - getHeight(node.getRight());

            /* if unbalanced on left subtree */
            if (heightDiff == 2){
                heightDiff = getHeight(node.getLeft().getLeft()) - getHeight(node.getLeft().getRight());

                /* if it is left-left, rotate right*/
                if (heightDiff > 0){
                    rotateRight(node);
                }
                /* if it is left-right, rotate left, then rotate right*/
                else{
                    rotateLeft(node.getLeft());
                    rotateRight(node);
                }
            }
            /* if unbalanced on right subtree */
            else if (heightDiff == -2){
                heightDiff = getHeight(node.getRight().getLeft()) - getHeight(node.getRight().getRight());

                /* if it is right-right, rotate left */
                if (heightDiff < 0){
                    rotateLeft(node);
                }
                /* if it is right-left, rotate right, then rotate left*/
                else{
                    rotateRight(node.getRight());
                    rotateLeft(node);
                }
            }

            /* traverse the entire tree to make it balance */
            node = node.getParent();
        }
    }

    /**
     * Rotate left from the given node
     *
     * @param node is the node where the unbalance occurs
     * @return void
     */
    public void rotateLeft (BinaryTreeNode<E> node){

        /* create a new node with the given node's data*/
        LinkedBinaryTreeNode<E> n = new LinkedBinaryTreeNode<>(node.getData());

        /* get the right child's data from the given node*/
        E temp = node.getRight().getData();

        /* attach the left child of the right child of the node to the right of the node created */
        if (node.getRight().getLeft() != null){
            n.setRight(node.getRight().getLeft());
        }

        /* remove the right child of the node */
        super.remove(temp);

        /* set the node's data as its right child */
        node.setData(temp);

        /* set the left child of the created node as the node's left child */
        if (node.getLeft() != null){
            n.setLeft(node.getLeft());
        }

        /* attached the created node to the left side of the node */
        node.setLeft(n);

    }

    /**
     * Rotate right from the given node
     *
     * @param node is the node where the unbalance occurs
     * @return void
     */
    public void rotateRight (BinaryTreeNode<E> node){

        /* create a new node with the given node's data*/
        LinkedBinaryTreeNode<E> n = new LinkedBinaryTreeNode<>(node.getData());

        /* get the left child's data from the given node*/
        E temp = node.getLeft().getData();

        /* attach the right child of the left child of the node to the left of the node created */
        if (node.getLeft().getRight() != null){
            n.setLeft(node.getLeft().getRight());
        }

        /* remove the left child of the node */
        super.remove(temp);

        /* set the node's data as its left child */
        node.setData(temp);

        /* set the right child of the created node as the node's right child */
        if (node.getRight() != null){
            n.setRight(node.getRight());
        }

        /* attached the created node to the right side of the node */
        node.setRight(n);

    }


}
