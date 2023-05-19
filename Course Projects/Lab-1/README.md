
### Project Description:
- This is a Java program that processes a file containing a list of possible FQDNs (Fully Qualified Domain Name) to report on the number of unique FQDNs, 2LD (2nd Level Domain), and TLDs (Top-Level Domain) found in the file.
  - Example of FQDN: google.com
  - Example of 2LD: google.com
  - Example of TLD: com
- To ensure the efficiency of the program, doubly linked-lists and sorted linked-lists are used for processing and searching through the file

### File Description:
- DList: doubly linked-lists implementation using Java API LinkedList, storing the data in the order they appear in the file
- SortedList: sorted linked-lists implementation, storing the data in the sorted order
- List: the generic linked-list interface, which extends from the Iterable interface
- ProcessFQDN: main methods to search through the file using either one of the lists and write out the results to a file

### Results:
 The program will report:
  - type of list used
  - the number of FQDNs
  - the number of unique FQDNs
  - the number of unique TLDs
  - the number of unique 2LDs 
  - execution time.



