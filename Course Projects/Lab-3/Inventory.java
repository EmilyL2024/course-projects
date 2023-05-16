import java.io.*;
import java.util.Scanner;

/**
 * This program creates a BST/AVL based inventory database from a file, the file
 * is a command line argument.
 *
 * Useage:
 *   java program -[a|b] input_name_file output_name_file
 *
 * @author Emily Lu {@literal <lub20@wfu.edu>}
 * @version 0.2, Nov.8, 2022
 */

public class Inventory {

    /**
     * main method, where the magic starts...
     * @param args String[], -[a|b] input_name_file output_name_file
     */
    public static void main(String[] args) {

        int updates = 0; // number of inventory updates

        BinarySearchTree<String> tree = null;  //binary tree

        if(!argsOK(args))
            System.exit(1);

        /* use the tree specified by the user... */
        if(args[0].equals("-a")) {
            tree = new AVLTree<String>();  
        }
        else {
            tree = new BinarySearchTree<String>();  
        }

        final long startTime = System.currentTimeMillis();
        updates = readInventoryFile(args[1], tree);
        displayInventoryInfo(args[2], tree, updates);
        final long endTime = System.currentTimeMillis();

        /* just subtract the two times */
        long difference = endTime - startTime;
        System.out.println("Time to complete: " + difference + " msec");
    }  


    /**
     *  This method the inventory file and stores in a tree
     *
     *  @param  inFile is the inventory filename (command line argument)
     *  @param  tree is the inventory database (a BST or AVL)
     *  @return the number of database updates made
     */
    private static int readInventoryFile(String inFile, BinarySearchTree<String> tree) {
        int numUpdates = 0;

        FileInputStream myFile = null; // to read in the file

        // reading the name file
        try {
            myFile = new FileInputStream(inFile);
        }
        catch(IOException e){
            System.out.println("File not found");
        }

        Scanner fileReader = new Scanner(myFile); // to read the file

        while(fileReader.hasNextLine()) {
            String data = fileReader.nextLine(); //get the data from the file
            String[] s = data.split("\\s"); //split the data by whitespace

            String d1 = s[0].toLowerCase(); //ignore the case of the first data
            String d2 = s[1]; //get the second data

            int size = tree.getSize(tree.root); //get the size before we made the change

            /* add item */
            if(d1.equals("a")){
                tree.add(d2);
            }
            /* remove item */
            else if(d1.equals("d")){
                tree.remove(d2);
            }

            int temp = tree.getSize(tree.root); //get the size after we made the change

            /* if the size changes after the action, increment the update by 1 */
            if (size != temp){
                numUpdates++;
            }


        }

        fileReader.close(); //close the fileReader

        return numUpdates;
    }


    /**
     *  This method displays the inventory database information (screen and file) 
     *
     *  @param  outFile is the output filename (command line argument)
     *  @param  tree is the inventory database (a BST or AVL)
     *  @param  updates is the number of database updates made
     */
     private static void displayInventoryInfo(String outFile, BinarySearchTree<String> tree, int updates) {

         FileOutputStream myFile = null; //to write to a file

         /* print our the information about the tree */
         System.out.println("Number of inventory updates: " + updates);

         /* get tree type */
         String fileType = tree.getClass().getSimpleName();
         if (fileType.equals("BinarySearchTree")){
             fileType = "BST";
         }
         else if (fileType.equals("AVLTree")){
             fileType = "AVL";
         }

         /* print out the number of type, size, and height of the tree  */
         System.out.println("Tree type:" + fileType + ", size: " + tree.getSize(tree.root) + ", height: " + tree.getHeight(tree.root));

         System.out.println("Tree string written to: " + outFile); //print out the resulting file


         /* writing out tree in string form to the file */
          try {
             myFile = new FileOutputStream(outFile);
         }
         catch (IOException e) {
             System.out.println("File not found.");
         }

         PrintWriter fileWriter = new PrintWriter(myFile); // to write to the result file

         fileWriter.println(tree.stringTree(tree.root));

         fileWriter.close(); //close the fileWriter


     }


   /**
     * Checks the command line args are correct (1 string)
     *
     * @param args String[] first is inventory file
     * @return true if everything is OK, false otherwise
     */
    private static boolean argsOK(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage: java program -[a|b] inventoryFile outputFile ");
            System.out.println("  -[a|b]         tree type (avl, bst) ");
            System.out.println("  inventoryFile  file containing inventory ");
            System.out.println("  outputFile     file to write tree string ");
            return false;
        }
        return true;
    }

}  


