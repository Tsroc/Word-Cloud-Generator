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

    public void displayTable(Map<String, Integer> map){
        map.forEach((k, v) ->{
            System.out.printf("Word: %s\tCount: %d\n", k, v);
        });
    }//getFullTable()
}