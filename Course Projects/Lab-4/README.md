## Project Description:
- This program is similar to Lab-3. The only difference is that this project will use a set composed of a hash table that uses separate chaining (to manage collisions) consisting of AVL trees to conduct inventory updates.
  - The set content is the hash table elements, where each element is an AVL tree (appear in the format of a string) of the identifiers.

## File Descrition:
- BinaryTreeNode: an interface for binary trees.
- LinkedBinaryTreeNode: a generic class for consturcting the node stored in binary trees
- BinarySearchTree: a binary search tree class with insertion, removal and lookup.
- AVLTree: an extension of the binary search tree class that maintains the balance of the tree after each move
- CountNode: a visitor class to count the number of nodes in a tree
- SetTree: a generic class that constructs a set (map) implemented as a hash table with separate chaining, entries that hash to the same table entry (causing a collision) are stored in an AVL tree (one tree per table entry).
  - This is the primary data structure used in main method 
- Inventory2: main method for exeucting the program

## Results:
The program will report:
  - Number of inventory updates performed (those that actually updated the set)
  - Number of identifiers (values) stored in the set
  - Hash table load factor (average number of values stored per hash table element)
  - Standard deviation of the size of tree between hash table elements
  - Set contents (the resulting tree stored in each entry)
  - Execution time
