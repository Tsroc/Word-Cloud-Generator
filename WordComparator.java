

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class WordComparator implements Comparator <Word>{
	public int compare(Word current, Word next){
		if (current.getCount() < next.getCount()){
			return 1; //Less than
 		}else if (current.getCount() > next.getCount()){
			return -1; //Greater than
		}else{
			return 0; //The same as...or equal...
		}
	}
}