## Project Description:
- This program simulates the a simpler version of the process of how a company mantains their product database, which includes adding new identifiers into the database or deleting existing identifiers from the database.
  - The database is updated through a file, where each line in the file is a possible database update. 
   - Each update line consists of two items, an action-code (char) and a product identifier (a String) separated by at least one space.
  - There are no duplicates in the database
- The program will read the inventory file and use either a binary search tree or AVL tree to store the product identifiers

## File Description:
- BinaryTreeNode: an interface for binary trees.
- LinkedBinaryTreeNode: a generic class for consturcting the node stored in binary trees
- BinarySearchTree: a binary search tree class with insertion, removal and lookup.
- AVLTree: an extension of the binary search tree class that maintains the balance of the tree after each move
- CountNode: a visitor class to count the number of nodes in a tree
- Inventory: main method for exeucting the program and writing out the result tree to a file

## Result:
The program will report:
  - Number of inventory updates conducted (number of adds and delete conducted)
  - Type of tree used
  - Size of the tree
  - Height of the tree
  - Execution time
