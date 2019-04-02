/*
Author: Eoin Wilkie
Class information:
    Implements Parser. Used to parse websites. Words are filterd by the following:
        ragex: tagsTable/StringBuilder tags, the ignore list obtained from the IgnoreWords Class.
	File is read and words are added to a Map, if the Map contains the word then the value is incremented.
	
	Note: ParserHTML takes significantly longer than ParserFile, this is due to reading a URL,
	my tests have shown adding th HashMap for both to take similar time.
*/

import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException; 
import java.util.Set;

public class ParserHtml implements Parser{
	long startTime, endTime;
   
    public void parse(String webIn, Map<String, Integer> frequencyTable) throws IOException, MalformedURLException {
        //Big-O running time: O(n): n being the length of the file. 
        //I do not believe it can be improved much, certain things must happen. Ignore file must be read by character.
		
		BufferedReader br;
		Set<String> ignoreWords = new IgnoreWords("ignorewords.txt").getIgnoreWords();
        String[] words;	
		String line;

        String pattern = "[\\p{Punct}\\s]+";

		System.out.println("\tReading a webpage.");
		startTime = System.nanoTime();
		java.net.URL url = new java.net.URL(webIn);
	
		br = new BufferedReader(new InputStreamReader(url.openStream()));
		endTime = System.nanoTime();
		System.out.println("\tTime taken: " + (endTime - startTime)/1000000 + " milliseconds.");

		System.out.println("\tAdding words to HashMap.");
		startTime = System.nanoTime();
		
		while ((line = br.readLine()) != null) {
			line = line.toUpperCase().replaceAll("\\<.*?>","");
			line.replaceAll(pattern, "");
			words = line.split(pattern);

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
		endTime = System.nanoTime();
		System.out.println("\tTime taken: " + (endTime - startTime)/1000000 + " milliseconds.");

		br.close();
	}// readFile()
}