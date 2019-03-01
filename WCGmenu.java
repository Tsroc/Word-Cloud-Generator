import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class WCGmenu{
    //vars
    String fileIn;
    int maxWords;
    String fileOut;
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
        //this.fileIn = "WarAndPeace-LeoTolstoy.txt";
        System.out.println("Enter MAXWORDS");
        this.maxWords = 20; 
        //this.maxWords = sc.nextInt();
        //sc.nextLine();
        System.out.println("Enter SAVEAS");
        //this.fileOut = sc.nextLine();
        this.fileOut = "WordCloudImg";
    }//displayMenu()


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