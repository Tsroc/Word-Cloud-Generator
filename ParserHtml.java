/*
Author: Eoin Wilkie
Class information:
    Implements Parser. Used to parse websites. Words are filterd by the following:
        ragex: tagsTable/StringBuilder tags, the ignore list obtained from the IgnoreWords Class.
    File is read and words are added to a Map, if the Map contains the word then the value is incremented.
*/

//ADD PACKAGE HERE

import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Set;

public class ParserHtml implements Parser{
   
	public void parse(String webIn, Map<String, Integer> frequencyTable){
        //Big-O running time: O(n) as far as I am aware
        //The following is pretty horrible but I do not believe it can be improved much
		//as for the file reading, it starts at the begining and moved to the end inspecting each character element along the way
		
		BufferedReader br;
		Set<String> ignoreWords = new IgnoreWords("ignorewords.txt").getIgnoreWords();
        String[] words;	
		String line;

        String pattern = "[\\p{Punct}\\s]+";

		try {
			System.out.println("Reading a webpage.");
			java.net.URL url = new java.net.URL(webIn);
        
			br = new BufferedReader(new InputStreamReader(url.openStream()));

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
			br.close();
		} catch (java.net.MalformedURLException e) {
			System.out.printf("Malformed URL: %s%n", e.getMessage());
		} catch (IOException e) {
			System.out.printf("I/O Error: %s%n", e.getMessage());
		}
	}// readFile()
}