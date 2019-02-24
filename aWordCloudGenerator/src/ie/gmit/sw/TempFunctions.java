package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * To be cleaned once class structure is decided
 */
public class TempFunctions {
	
	//search Map and return n elements of map
	public Map mostFreqWords(Map<String, Integer> map, int mapSize) {
		//Map 
		return null;
	}
	
	//sort Map
	public Map<String, Integer> sortMap(Map<String, Integer> map, int mapSize) {
		//Map 
		//LinkedHashMap lhm = (LinkedHashMap) map;
		/*
		List<String> keys = new LinkedList<String>(map.keySet());
		List<Integer> values = new LinkedList<Integer>(map.values());
		*/	
		List<Map.Entry<String, Integer>> al = new LinkedList<>(map.entrySet());
		//Collections.sort(al, new MapComparator());
		al.sort(Entry.comparingByValue());
		Collections.reverse(al);
		
		Map<String, Integer> result = new LinkedHashMap<>(mapSize);
		
		int i = 0;
		for (Entry<String, Integer> entry : al) {
			if (i >= mapSize) break;
			//System.out.println(entry.getKey() + " " + entry.getValue());
            result.put(entry.getKey(), entry.getValue());
            i++;
        }
		return result;
		

		//convert sortedMap back to Map
		//Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		//for (Entry<String, Integer> entry : al) {
		//	map.put(entry.getKey(), entry.getValue());
		//}
		
		
	}
}
