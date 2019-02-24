package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Map;

public final class Parser {
	// for now parser will work on file only, will add URL later
	/*
	 * A parser that reads the file or URL line-by-line, extracts each word and adds
	 * it to a frequency table.
	 */
	/*
	 * Frequency table will be another class, for now I will read the file or URL
	 * line-by-line and print to screen
	 */
	/*
	 * Note: PARSER is converting everything to lowercase, this may not be ideal. Perhaps can overcome this
	 * by displying words in uppercase.
	 * Issue created with words The/the and Caesar/caesar. There should be a way to check if a word
	 * is in both upper/lower case and if it is only in upper or lower case.
	 */

	@SuppressWarnings("unused")
	private String filename;

	// constructors
	private Parser() {
		// Parser is not supposed to be instanced.
	}

	public static void read(Map<String, Integer> map, String filename, char fileType) {
		//change to string builder
		String[] words;
		// String delimiters = "\\s+|,\\s*|\\.\\s*";
		// Seems to have split correctly, look into later
		// This will not correctly take words such as "can't"
		String delimiters = "[\\p{Punct}\\s]+";
		String ignoreFile = "ignorewords.txt";
		String line;
		String strIgnoreWords;
		//StringBuffer line = new StringBuffer();
		StringBuffer sbIgnoreWords = new StringBuffer();
		
		FileReader fr;
		BufferedReader br;

		// parse filename
		if (fileType == 'f') {

			// filter ignore words
			// ignoreList must be read in from file, ?should entire program stop if unable
			// to read this? For now, no
			try {
				// FileReader reads text files in the default encoding.
				fr = new FileReader(ignoreFile);
				br = new BufferedReader(fr);

				while ((line = br.readLine()) != null) {
					sbIgnoreWords.append(line.toLowerCase());
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
			//*/

			try {
				System.out.println("Reading a file.");
				fr = new FileReader(filename);
				br = new BufferedReader(fr);

				while ((line = br.readLine()) != null) {

					// System.out.println(line);

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
				System.out.println("Finished reading file.");
				br.close();
			} catch (FileNotFoundException ex) {
				System.out.printf("Unable to open file '%s'%n", filename);
			} catch (IOException ex) {
				System.out.printf("Error reading file '%s'%n", filename);
				// ex.printStackTrace();
			}
		}
		// parse webpage
		/*
		Note: webpages are not returning much words once tags are removed. May require recursive fucntion
		but I'm unsure of how they are laid out and how I would search that.
		Perhaps checking all links on the page and entering if it is the same website, diff page.
		For now I will leave this and focus on file reading
		*/
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
				 */

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

	public static char fileType(String filename) {
		char inputType;

		// checks are simple, use ragex?
		// logically decide how to determine better if is website or file.
		if ((filename.substring(0, 4).equals("http")) || (filename.substring(0, 3).equals("www.")))
			return 'w';
		if (filename.substring(filename.length() - 3, filename.length()).equals("txt"))
			return 'f';
		return ' ';
	}
}
