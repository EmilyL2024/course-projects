/**
 * This program finds the unique FQDNs and 2LDs in a file, the file
 * is a command line argument.
 *
 * Useage:
 *   java ProcessFQDN -[d|s] names.txt results.txt
 *
 * @author Emily Lu {@literal <lub20@wful.edu>}
 * @version 0.2, Sep. 12, 2022
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class ProcessFQDN {
   /**
    * main method, where the magic starts...
    
    * @param args String[], -[d|s] names.txt results.txt
    */
    public static void main(String[] args) {
        List<String> uniqueHost = null;  // unique hostnames
        List<String> unique2LD = null;   // unique 2LD names
        List<String> uniqueTLD = null;   // unique TLD names
        int numFQDN;                        // number of FQDNs

        /* check whether the command line is valid */
        if(!argsOK(args))
            System.exit(1);

        /* use the list specified by the user to store the names */
        if (args[0].equals("-d")) {

            uniqueHost = new DList<String>();
            unique2LD = new DList<String>();
            uniqueTLD = new DList<String>();

        }  
        else if (args[0].equals("-s")) {

            uniqueHost = new SortedList<String>();
            unique2LD = new SortedList<String>();
            uniqueTLD = new SortedList<String>();

        }
        else {
            System.out.println("list type " + args[0] + " is incorrect ");
            return;
        }

        /* record the time used */
        final long startTime = System.currentTimeMillis();
        numFQDN = readNameFile(args[1], uniqueHost, unique2LD, uniqueTLD);
        displayNameInfo(args[2], numFQDN, uniqueHost, unique2LD, uniqueTLD);
        final long endTime = System.currentTimeMillis();

        /* just subtract the two times */
        long difference = endTime - startTime;
        System.out.println("Time to complete: " + difference + " msec");
    }



    /**
     *  This method reads (and eventually stores) FQDN and 2LD in the fqdn file
     *
     *  @param  fileName is the fqdn filename (command line argument)
     *  @param  uniqueHost is the list that stores unique FQDN names
     *  @param  unique2LD is the list that stores unique 2LD names
     *  @param  uniqueTLD is the list that stores unique TLD names
     *  @return is the number of valid FQDN found in the file
     */
    static int readNameFile(String fileName, List<String> uniqueHost, List<String> unique2LD,
                            List<String> uniqueTLD) {

        FileInputStream myFile = null; // to read in the file

        int numFQDN = 0; // the number of valid fqdn names
        boolean length = true; // check whether the length of the label is valid or not

        /* reading text file containing the list of names */
        try {
            myFile = new FileInputStream(fileName);
        }
        catch(IOException e){
            System.out.println("File not found");
        }

        Scanner fileReader = new Scanner(myFile); // to read the file

        while(fileReader.hasNextLine()){

            /* processing line of text */
            String data = fileReader.nextLine(); // the data in the file
            data = data.toLowerCase(); //convert all the data into lower cases

            /* check whether the text is a valid name */
            if (data.contains(".") && data.length() <= 253) {

                String[] s = data.split("\\.");

                /* check whether the length of the text is valid */
                if (s.length >= 2) {

                    for (String i : s) {
                        if (i.length() < 1 || i.length() > 63) {
                            length = false;
                            break;
                        }
                    }

                    /* if the name is valid, increment the valid number */
                    if (length) {
                        numFQDN++;
                    }

                    /* if the name is valid and unique, add to the uniqueHost list  */
                    if (length && !uniqueHost.contains(data)) {
                        uniqueHost.add(data);
                    }

                    /* if the last label of the name is valid and unique, add to the uniqueTLD list  */
                    if (length && !uniqueTLD.contains(s[s.length-1])) {
                        uniqueTLD.add(s[s.length-1]);
                    }

                    String s2 = s[s.length-2] + "." + s[s.length-1]; // the 2LD name

                    /* if the last two labels of the name are valid and unique, add to the unique2LD list  */
                    if (length && !unique2LD.contains(s2)) {
                        unique2LD.add(s2);
                    }
                }


        }
            length = true; //update the value

    }

        fileReader.close();

        String fileType = uniqueHost.getClass().getSimpleName(); // the name of the list used
        fileName = fileName.substring(4); // the file name without the address

        /* print out the name of the list used */
        if (fileType.equals("DList")) {
            System.out.println("Processing " + fileName + " using double linked list");
        }
        else{
            System.out.println("Processing " + fileName + " using sorted linked list");
        }

        return numFQDN;  
    }


    /**
     *  This method displays the stats and FQDN and 2LD found in the file
     *
     *  @param  filename is the result filename (command line argument)
     *  @param  uniqueHost is the list that stores unique FQDN names
     *  @param  unique2LD is the list that stores unique 2LD names
     *  @param  uniqueTLD is the list that stores unique TLD names
     *  @return void
     */
    static void displayNameInfo(String filename, int numFQDN, List<String> uniqueHost, List<String> unique2LD,
                                List<String> uniqueTLD) {

        Iterator itH = uniqueHost.iterator(); //iterator to iterate through the uniqueHost list
        Iterator it2 = unique2LD.iterator(); //iterator to iterate through the unique2LD list
        Iterator itT = uniqueTLD.iterator(); //iterator to iterate through the uniqueTLD list

        FileOutputStream myFile = null; //to write to a file

        /* print out the number of elements in the list  */
        System.out.println("Found " + numFQDN + " FQDNs, " + uniqueHost.size() + " unique FQDNs, " + unique2LD.size() + " unique 2LDs, and " + uniqueTLD.size() + " unique TLDs");

        /* writing out the list of names to a text file */
        try {
            myFile = new FileOutputStream(filename);
        }
        catch (IOException e) {
            System.out.println("File not found.");
        }

        PrintWriter fileWriter = new PrintWriter(myFile); // to write to the result file

        fileWriter.print("Unique FQDNs: ");

        /* iterate through the entire list to write out all the elements */
        while (itH.hasNext()) {
            fileWriter.print("[" + itH.next() + "] ");
        }

        fileWriter.println("");
        fileWriter.print("Unique 2LDs: ");
        while (it2.hasNext()) {
            fileWriter.print("[" + it2.next() + "] ");
        }

        fileWriter.println("");
        fileWriter.print("Unique TLDs: ");
        while (itT.hasNext()) {
            fileWriter.print("[" + itT.next() + "] ");
        }

        fileWriter.close();
    }


    /**
     *  This method returns true if the command line arguments are acceptable,
     *
     *  @param  args list of Strings (command line arguments)
     *  @return true if the arguments are acceptable, false otherwise
     */
     private static boolean argsOK(String[] args) {
         if (args.length < 3) {
             System.out.println("Usage: java program -[d|s] input_name_file output_name_file ");
             System.out.println("  -[d|s]            list type (double, sorted) ");
             System.out.println("  input_name_file   file containing hostnames ");
             System.out.println("  output_name_file  file to write unique FQDNs, 2LDs, and TLDs ");
             return false;
         }
         return true;
     }

   


}
