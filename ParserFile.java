import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class ParserFile implements Parser{
    public  void parse(String fileIn, Map<String, Integer> frequencyTable){
        String[] words;
        String delimiters = "[\\p{Punct}\\s]+";
		String ignoreWords = new IgnoreWords().getIgnoreWords();
		String line;
		
        try {
            System.out.println("Reading a file.");
            BufferedReader br = new BufferedReader(new FileReader(fileIn));

            while ((line = br.readLine()) != null) {

                // System.out.println(line);
                words = line.toLowerCase().split(delimiters);

                // add to Map
                for (int i = 0; i < words.length; i++) {
                    if(ignoreWords != null) {
                        
                        if(ignoreWords.contains(words[i]))
                        {
                            //System.out.println(words[i]);
                            break;
                        }
                    }
                    //better way to do this 
                    if (!frequencyTable.containsKey(words[i])) {
                        frequencyTable.put(words[i], 1);
                    } else {
                        frequencyTable.put(words[i], frequencyTable.get(words[i]) + 1);
                    }
                }

            }
            System.out.println("Finished reading file.");
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.printf("Unable to open file '%s'%n", fileIn);
        } catch (IOException ex) {
            System.out.printf("Error reading file '%s'%n", fileIn);
				// ex.printStackTrace();
        }
    }//parseFile()
}