import java.util.Map;
public class ParserHtml implements Parser{
    public void parse(String s, Map<String, Integer> m){
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
        */
	}// readFile()
}