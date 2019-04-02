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
import java.util.Set;


public class ParserFile implements Parser{
	long startTime, endTime;

    public  void parse(String fileIn, Map<String, Integer> frequencyTable) throws FileNotFoundException, IOException{
        //Big-O running time: O(n) as far as I am aware
        //The following is pretty horrible but I do not believe it can be improved much
        //as for the file reading, it starts at the begining and moved to the end inspecting each character element along the way
        
        String[] words;     
        String delimiters = "[\\p{Punct}\\s]+";
        
		Set<String> ignoreWords = new IgnoreWords("ignorewords.txt").getIgnoreWords();
		String line;
		
        //try {
            System.out.println("\tReading a file.");
			startTime = System.nanoTime();
            BufferedReader br = new BufferedReader(new FileReader(fileIn));
			endTime = System.nanoTime();
			System.out.println("\tTime taken: " + (endTime - startTime)/1000000 + " milliseconds.");

            System.out.println("\tAdding words to HashMap.");
            startTime = System.nanoTime();
            
            while ((line = br.readLine()) != null) {
                words = line.toUpperCase().split(delimiters);

                // add to Map
                for (int i = 0; i < words.length; i++) {
                    if (words[i].length() <2)  //I don't want words which are 1 character
                        break; 
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
			endTime = System.nanoTime();
            System.out.println("\tTime taken: " + (endTime - startTime)/1000000 + " milliseconds.");
            
            br.close();
            /*
        } catch (FileNotFoundException ex) {
            System.out.printf("Unable to open file '%s'%n", fileIn);
        } catch (IOException ex) {
            System.out.printf("Error reading file '%s'%n", fileIn);
        }*/
    }//parseFile()
}