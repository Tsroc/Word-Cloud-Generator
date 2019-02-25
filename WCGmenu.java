import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class WCGmenu{
    //vars
    String fileIn;
    int maxWords;
    String fileOut;
    char fileInType;
    java.util.Map<String, Integer> frequencyTable = new java.util.HashMap<String, Integer>();
    
    //enum for fileInType

    //constructors
    //functions
    public void displayMenu(){
        java.util.Scanner sc = new java.util.Scanner(System.in);

        //menu
        System.out.println("Command-Line-Menu");
        System.out.println("Enter FILENAME (http://www.google.com/) (DeBelloGallico.txt)");
        //this.fileIn = sc.nextLine();
        this.fileIn = "DeBelloGallico.txt";
        
        System.out.println("Enter MAXWORDS");
        this.maxWords = 20; 
        //this.maxWords = sc.nextInt();
        //sc.nextLine();
        /*
        System.out.println("Enter SAVEAS");
        this.fileOut = sc.nextLine();
        */


    }//displayMenu()

    private char determineFileInType(){
        // logically decide how to determine better if is website or file.
        if ((this.fileIn.substring(0, 4).equals("http")) || (this.fileIn.substring(0, 3).equals("www.")))
            return 'w';
        if (this.fileIn.substring(this.fileIn.length() - 3, this.fileIn.length()).equals("txt"))
            return 'f';
        return ' ';
    }//determineFileInType()

    public void readFile(){
     //should call parseFile() or parseHtml()
     this.fileInType = determineFileInType();

     switch(fileInType){
         case 'w':  parseHtml();
            break;
         case 'f':  parseFile();
            break;
         default:
            System.out.println("Could not determine FILE/URL.");
            break;
     }
    }//readFile()

    private void parseFile(){
        	String[] words;
		// String delimiters = "\\s+|,\\s*|\\.\\s*";
		// Seems to have split correctly, look into later
		// This will not correctly take words such as "can't"
		String delimiters = "[\\p{Punct}\\s]+";
		String ignoreFile = this.getIgnoreList();
		String line;
		//String strIgnoreWords = null;
		//StringBuffer line = new StringBuffer();
		//StringBuffer sbIgnoreWords = new StringBuffer();
		
		FileReader fr;
        BufferedReader br;

        try {
            System.out.println("Reading a file.");
            fr = new FileReader(this.fileIn);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {

                // System.out.println(line);
                words = line.toLowerCase().split(delimiters);

                // add to Map
                for (int i = 0; i < words.length; i++) {
                    if(ignoreFile != null) {
                        
                        if(ignoreFile.contains(words[i]))
                        {
                            //System.out.println(words[i]);
                            break;
                        }
                    }
                    
                    if (!this.frequencyTable.containsKey(words[i])) {
                        this.frequencyTable.put(words[i], 1);
                    } else {
                        this.frequencyTable.put(words[i], this.frequencyTable.get(words[i]) + 1);
                    }
                }

            }
            // Close BufferedReader
            System.out.println("Finished reading file.");
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.printf("Unable to open file '%s'%n", this.fileIn);
        } catch (IOException ex) {
            System.out.printf("Error reading file '%s'%n", this.fileIn);
				// ex.printStackTrace();
        }
    }//parseFile()

    private void parseHtml(){

    }//parseHtml()

    public String getIgnoreList(){
        String ignoreFile = "ignorewords.txt";
        String line = null;
        String strIgnoreWords = null;
		StringBuffer sbIgnoreWords = new StringBuffer();
		
		FileReader fr;
		BufferedReader br;

        // filter ignore words
        // ignoreList must be read in from file, ?should entire program stop if unable
        // to read this? For now, no
        try {
            // FileReader reads text files in the default encoding.
            fr = new FileReader(ignoreFile);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                sbIgnoreWords.append(line.toLowerCase());
                sbIgnoreWords.append(" ");
            }
            //System.out.println(sbIgnoreWords);
            // Always close files.
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.printf("Unable to open file '%s'%n", ignoreFile);
        } catch (IOException ex) {
            System.out.printf("Error reading file '%s'%n", ignoreFile);
            // ex.printStackTrace();
        }
        strIgnoreWords = sbIgnoreWords.toString();
        
        return strIgnoreWords;
    }//getIgnoreList()

    public java.util.Map<String, Integer> getFullTable(){
        return frequencyTable;
    }//getFullTable()

    public Map<String, Integer> getShortTable(){
        List<Map.Entry<String, Integer>> al = new LinkedList<>(this.frequencyTable.entrySet());
		//Collections.sort(al, new MapComparator());
		al.sort(Entry.comparingByValue());
		Collections.reverse(al);
		
		Map<String, Integer> result = new LinkedHashMap<>(this.maxWords);
		
		int i = 0;
		for (Entry<String, Integer> entry : al) {
			if (i >= this.maxWords) break;
			//System.out.println(entry.getKey() + " " + entry.getValue());
            result.put(entry.getKey(), entry.getValue());
            i++;
        }
        return result;
    }//getShortTable()

    public Map<String, Integer> sortTable(){
        List<Map.Entry<String, Integer>> al = new LinkedList<>(this.frequencyTable.entrySet());
		//Collections.sort(al, new MapComparator());
		al.sort(Entry.comparingByValue());
		Collections.reverse(al);
		
		Map<String, Integer> result = new LinkedHashMap<>(this.frequencyTable.size());
		
		for (Entry<String, Integer> entry : al) {
			//System.out.println(entry.getKey() + " " + entry.getValue());
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
        
    }//sortTable()

    public void displayTable(){
        frequencyTable.forEach((k, v) ->{
            System.out.printf("Word: %s\tCount: %d\n", k, v);
        });
    }//getFullTable()



}