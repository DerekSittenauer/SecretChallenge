/*  HEB Developer I Programming Challenge
 *
 *  @author Derek Sittenauer
 *  @version 1.0
 *  @since 05/14/2018
 */
package histogram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Histogram {
    
    public static void main(String[] args) {
        
        //creating buffer object for reading file
        BufferedReader reader;
        //creating hashmap to store words/occurrences
        Map <String, Integer> hmap = new HashMap<>();
		
        try {
            //read input.txt
            reader = new BufferedReader(new FileReader("resources/input.txt"));
            //creating string for each line
            String line = reader.readLine();
            //until end of file
            while (line != null) {
                //if there is no empty space to trim on a line
                if (!line.trim().equals("")) {
                    //string array to store individual words
                    String words [] = line.split(" ");

                    //cycle through the words
                    for (String word : words) {
                        //if there are still words OR
                        //if all blank space has been trimmed from a word
                        if (word == null || word.trim().equals(""))
                            continue;

                        //string for each processed word
                        String processed = word.toLowerCase();
                        //remove any commas
                        processed = processed.replace(",", "");
                        //remove any periods
                        processed = processed.replace(".", "");

                        //if a word has appeared before
                        if (hmap.containsKey(processed))
                            //increment value by 1
                            hmap.put(processed, hmap.get(processed) + 1);
                        //if it's a new word
                        else
                            hmap.put(processed, 1);
                    }
                }
            //read new line to cylce loop
            line = reader.readLine();

            }

            //create set for comparing
            Set<Map.Entry<String, Integer>> set = hmap.entrySet();
            //create array list for comparing
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(set);
            //creating a new collection to compare values
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
            {
                //function used to compare values in hashmap
                public int compare(Map.Entry<String, Integer> value1, Map.Entry<String, Integer> value2)
                {
                    //returns hashmap in descending order
                    return (value2.getValue()).compareTo(value1.getValue());
                }
            });

            //creating outputfile
            PrintWriter output = new PrintWriter("resources/output.txt", "UTF-8");
            //loop to print to output file
            for(Map.Entry<String, Integer> entry:list) {
                //if 4 occurrences
                if(entry.getValue() == 4) 
                    output.printf("%17s %n", entry.getKey()+" | ==== "+entry.getValue());

                //if 3 occurrences
                if(entry.getValue() == 3) 
                    output.printf("%16s %n", entry.getKey()+" | === "+entry.getValue());

                //if 2 occurrences
                if(entry.getValue() == 2) 
                    output.printf("%15s %n", entry.getKey()+" | == "+entry.getValue());

                //if 1 occurrence
                if(entry.getValue() == 1) 
                    output.printf("%14s %n", entry.getKey()+" | = "+entry.getValue());

            }

            /*
            //loop for display to console for testing purposes
            for(Map.Entry<String, Integer> entry:list){
                //if 4 occurrences
                if(entry.getValue() == 4) {
                    System.out.printf("%17s %n", entry.getKey()+" | ==== "+entry.getValue());
                }
                //if 3 occurrences
                if(entry.getValue() == 3) {
                    System.out.printf("%16s %n", entry.getKey()+" | === "+entry.getValue());
                }
                //if 2 occurrences
                if(entry.getValue() == 2) {
                    System.out.printf("%15s %n", entry.getKey()+" | == "+entry.getValue());
                }
                //if 1 occurrence
                if(entry.getValue() == 1) {
                    System.out.printf("%14s %n", entry.getKey()+" | = "+entry.getValue());
                }
            }*/

            //close files
            reader.close();
            output.close();
            
        } catch(IOException e) {
            //displays error and location of error
            e.printStackTrace();
        }
    }
}
