/*
Author: Eoin Wilkie
Class information:
    This class contains functions to manipulate the data held in a Map<String, Integer> in order to return 
*/
//*NOTE* currently using a LinkedHashMap to allow for an ordered Map, should be changed to a different data structure. Priority?

//ADD PACKAGE HERE

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TableFunctions{
        //Big-O running time: O(1)
        //returns Map object
    public static java.util.Map<String, Integer> getFullTable(Map<String, Integer> map){
        return map;
    }//getFullTable()

        //Big-O running time: O(n) or O(n2) - (not quite n2 I think)?
        //What the loop does: creates new linkedList object and assigns it the values of map, I'm not sure how this runs, it links it to the object address - ?
        //al.sort comparingValues - I believe this is O(n2) but I don't think its avoidable 7
        //Collections.reverse - O(n)  - loops once over n
        //final loop is O(n) - loops once over n
    public static Map<String, Integer> getShortTable(Map<String, Integer> map, int size){
        List<Map.Entry<String, Integer>> al = new LinkedList<>(map.entrySet());
        //Collections.sort(al, new MapComparator());
		al.sort(Entry.comparingByValue());
		Collections.reverse(al);
		
		Map<String, Integer> result = new LinkedHashMap<>(size);
		
		int i = 0;
		for (Entry<String, Integer> entry : al) {
			if (i >= size) break;
			//System.out.println(entry.getKey() + " " + entry.getValue());
            result.put(entry.getKey(), entry.getValue());
            i++;
        }
        return result;
    }//getShortTable()

        //Big-O running time: O(n) or O(n2) - (not quite n2 I think)?
        //Same as previous
        //What the loop does: creates new linkedList object and assigns it the values of map, I'm not sure how this runs, it links it to the object address - ?
        //al.sort comparingValues - I believe this is O(n2) but I don't think its avoidable 7
        //Collections.reverse - O(n)  - loops once over n
        //final loop is O(n) - loops once over n
    public static Map<String, Integer> sortTable(Map<String, Integer> map){
        List<Map.Entry<String, Integer>> al = new LinkedList<>(map.entrySet());
		//Collections.sort(al, new MapComparator());
		al.sort(Entry.comparingByValue());
		Collections.reverse(al);
		
		Map<String, Integer> result = new LinkedHashMap<>(map.size());
		
		for (Entry<String, Integer> entry : al) {
			//System.out.println(entry.getKey() + " " + entry.getValue());
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }//sortTable()

    
        //Big-O running time: O(n) - ?
        //loops over n once
    public void displayTable(Map<String, Integer> map){
        map.forEach((k, v) ->{
            System.out.printf("Word: %s\tCount: %d\n", k, v);
        });
    }//getFullTable()
}