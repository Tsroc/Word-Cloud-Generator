/*
Author: Eoin Wilkie
Class information:
    Implements Parser. Used to parse files. Words are filterd by the following:
        ragex: "[\\p{Punct}\\s]+", the ignore list obtained from the IgnoreWords Class and by size, anything words which contain less than 2 characters are eliminated.
    File is read and words are added to a Map, if the Map contains the word then the value is incremented.
*/

//ADD PACKAGE HERE

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;


public class ParserFile implements Parser{

        //Big-O running time: O(n) as far as I am aware
        //The following is pretty horrible but I do not believe it can be improved much
        //as for the file reading, it starts at the begining and moved to the end inspecting each character element along the way
    public  void parse(String fileIn, Map<String, Integer> frequencyTable){
        String[] words;     //unsure if a different data structure will improve this, the word[] must start at begining and iterate over each element
                            //regardless what datascructure is used, as it takes the line from file, it must all be processed word by word
        String delimiters = "[\\p{Punct}\\s]+";
        
		Set<String> ignoreWords = new IgnoreWords("ignorewords.txt").getIgnoreWords();
		String line;
		
        try {
            System.out.println("Reading a file.");
            BufferedReader br = new BufferedReader(new FileReader(fileIn));

            while ((line = br.readLine()) != null) {
                words = line.toUpperCase().split(delimiters);

                // add to Map
                for (int i = 0; i < words.length; i++) {
                    if (words[i].length() <2) break; //I don't want words which are 1 character
                    if(ignoreWords != null) {
                        if(ignoreWords.contains(words[i]))
                            break;
                    }
                    if (!frequencyTable.containsKey(words[i]))
                        frequencyTable.put(words[i], 1);
                    else
                        frequencyTable.put(words[i], frequencyTable.get(words[i]) + 1);
                }
            }
            System.out.println("Finished reading file.");
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.printf("Unable to open file '%s'%n", fileIn);
        } catch (IOException ex) {
            System.out.printf("Error reading file '%s'%n", fileIn);
        }
    }//parseFile()
}