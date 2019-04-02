/*
Author: Eoin Wilkie
Class information:
    This class is used to compare Word objects by count 
*/

import java.util.Comparator;

public class WordComparator implements Comparator <Word>{
	//Big-O running time: O(1)
	//Runs one check per object, this is quick.
	
	public int compare(Word current, Word next){
		if (current.getCount() < next.getCount()){
			return 1; //next is greater, higher priority
 		}else if (current.getCount() > next.getCount()){
			return -1; //next is less, lower priority
		}else{
			return 0; //The same as...or equal...
		}
	}
}