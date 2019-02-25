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
        System.out.println("Enter SAVEAS");
        //this.fileOut = sc.nextLine();
        this.fileOut = "WordCloudImg";
        


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
        /* NOT IMPLEMENTED
		if (fileType == 'w') {
			// seems to work on some webpages, will implement functionality later
			//seems it would be best to use Jsoup for this
		    final String [] tagsTab = {"!doctype","a","abbr","acronym","address","applet","area","article","aside","audio","b","base","basefont","bdi","bdo","bgsound","big","blink","blockquote","body","br","button","canvas","caption","center","cite","code","col","colgroup","content","data","datalist","dd","decorator","del","details","dfn","dir","div","dl","dt","element","em","embed","fieldset","figcaption","figure","font","footer","form","frame","frameset","h1","h2","h3","h4","h5","h6","head","header","hgroup","hr","html","i","iframe","img","input","ins","isindex","kbd","keygen","label","legend","li","link","listing","main","map","mark","marquee","menu","menuitem","meta","meter","nav","nobr","noframes","noscript","object","ol","optgroup","option","output","p","param","plaintext","pre","progress","q","rp","rt","ruby","s","samp","script","section","select","shadow","small","source","spacer","span","strike","strong","style","sub","summary","sup","table","tbody","td","template","textarea","tfoot","th","thead","time","title","tr","track","tt","u","ul","var","video","wbr","xmp"};
	        StringBuilder tags = new StringBuilder();

		    for (int i=0;i<tagsTab.length;i++) {
	            tags.append(tagsTab[i].toLowerCase()).append('|').append(tagsTab[i].toUpperCase());
	            if (i<tagsTab.length-1) {
	                tags.append('|');
	            }
	        }
		    String pattern = "</?("+tags.toString()+"){1}.*?/?>";

			try {
				System.out.println("Reading a webpage.");
				java.net.URL url = new java.net.URL(filename);
				br = new BufferedReader(new InputStreamReader(url.openStream()));

				/*
				 * System.out.println("protocol = " + url.getProtocol());
				 * System.out.println("authority = " + url.getAuthority());
				 * System.out.println("host = " + url.getHost()); System.out.println("port = " +
				 * url.getPort()); System.out.println("path = " + url.getPath());
				 * System.out.println("query = " + url.getQuery());
				 * System.out.println("filename = " + url.getFile());
				 * System.out.println("ref = " + url.getRef());
				 *

				while ((line = br.readLine()) != null) {
					//line = line.toLowerCase().replaceAll("\\<.*?>","");
					System.out.println(line);
					line.replaceAll(pattern, "");
					words = line.toLowerCase().split(delimiters);

					// add to Map
					for (int i = 0; i < words.length; i++) {
						if(sbIgnoreWords != null) {
							//ignore words have been read
							strIgnoreWords = sbIgnoreWords.toString();

							if(strIgnoreWords.contains(words[i]))
							{
								//System.out.println(words[i]);
								break;
							}
						}

						if (!map.containsKey(words[i])) {
							map.put(words[i], 1);
						} else {
							map.put(words[i], map.get(words[i]) + 1);
						}
					}

				}

				// Close BufferedReader
				br.close();
			} catch (java.net.MalformedURLException e) {
				System.out.printf("Malformed URL: %s%n", e.getMessage());
			} catch (IOException e) {
				System.out.printf("I/O Error: %s%n", e.getMessage());
			}
		}
	}// readFile()
        */
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