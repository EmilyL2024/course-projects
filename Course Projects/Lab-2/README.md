## Project Description:
- This project is almost identical to Lab 1, except this version of the project aims to improve the search time by making the list self-organized, which is to move more frequently accessed items towards the beginning of the list.
- We developed three new self-organized lists (count managed, first managed, and transpose managed) as well as the sorted linked-list and the doubly linked-lists from the last project in this project.

## File Description:
- DList: doubly linked-lists implementation using Java API LinkedList, storing the data in the order they appear in the file
- SortedList: sorted linked-lists implementation, storing the data in the sorted order
- List: the generic linked-list interface, which extends from the Iterable interface

- DataCount: a generic class to store both the generic data and a count storing the number of times an item is accessed
  - all lists below stores DataCount objects  
- CountList: an iterable, self-organizing list that transposes node data based on access and current counts.
  - when searched, the data in the matching node will be exchanged with the data in the previous node **if its count is greater than or equal to the preceding node**, if there is a preceding node.
  - new node are added at the end of the list.
- TransposeList: an iterable, self-organizing list that transposes nodes based on access. 
  - when searched, the node containing the matching data will have its count incremented, then the node’s data will be exchanged with its **previous node’s data**, if it exists.
  - new node are added at the end of the list.
- FrontList: an iterable, self-organizing list that exchanges the most recently accessed node’s data with the data in the current front of the list. 
  - when searched, the node containing the matching data will have its count incremented then the node’s data will be exchanged with its **first node’s data in the list**.
  - new node are added at the **beginning** of the list

- ProcessFQDN2: main methods to search through the file using either one of the lists and write out the results (the content stored in the list) to a file

## Results:
The program will report:
  - type of list used
  - the number of FQDNs
  - the number of unique FQDNs
  - the number of unique TLDs
  - the number of unique 2LDs 
  - execution time.



