import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class MapComparator <K, V extends Comparable<V>> implements Comparator <Map.Entry<K, V>>{
	public int compare(Map.Entry<K, V> current, Map.Entry<K, V> next){
		if (((Number) ((Map) current).values()).intValue() < ((Number) ((Map) next).values()).intValue()){
		//if (current.getValue().compareTo(next.getValue());{
			return -1; //Less than
 		}else if (((Number) ((Map) current).values()).intValue() > ((Number) ((Map) next).values()).intValue()){
			return 1; //Greater than
		}else{
			return 0; //The same as...or equal...
		}
	}




}