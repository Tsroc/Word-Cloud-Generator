/*
Author: Eoin Wilkie
Class information:
    Runner class contains the main method for the program.
    Contain a HashMap which is assigned values from the FileInfo Class, this Map will then be reduced in size to the users requested word cloud size.
    This array is then passed through the WordCloudCreator Class to create the word cloud.
*/

//ADD PACKAGE HERE

import java.util.Map.Entry;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Queue;
import java.util.PriorityQueue;

public class Runner{
    public static void main(String[] args){
        char menuSelection = ' ';
        Map<String, Integer> frequencyTable;
        Queue<Word> words;
        Queue<Word> words_cloud;
        Word tempWord;
        WordCloudCreator wcg;
        WCGmenu menu;

        Scanner sc = new Scanner(System.in);
        
        //loop will continue until N/n entered
        do{
            menu = new WCGmenu();
            menu.displayTestMenu();
            //menu.displayMenu();

            frequencyTable = new HashMap<String, Integer>();
            words = new PriorityQueue<>(menu.getMaxWords(), new WordComparator());      //orders list
            words_cloud = new PriorityQueue<>(menu.getMaxWords(), new WordComparator());      

            FileInfo file = new FileInfo(menu.getFileIn(), frequencyTable);
            file.readFile();

                //Big-O running time: O(n2)
                //Adds every element of HashMap to Queue and sorts it, I'm not sure how bad this is
                //It would seem that the comparitor check is quick as it simply checks value,
                //but correct placement on the Queue seems to imply O(n2)
            for (Map.Entry<String, Integer> ft: frequencyTable.entrySet()){
                if (ft.getKey() == "")
                    continue;
                words.offer(new Word(ft.getKey(), ft.getValue()));
                //all must be placed on queue to order the queue
            }

            for(int i = 0; i <= menu.getMaxWords(); i++){
                tempWord = words.poll();
                words_cloud.offer(tempWord);
            }

            wcg = new WordCloudCreator(words_cloud);
            WordCloudCreator.saveImg(menu.getFileOut());
            System.out.println("Image created.");
            System.out.print("\nCreate a new word cloud?: Y/n ");
            menuSelection = sc.next().toUpperCase().charAt(0);
            System.out.println();
        }while((menuSelection != 'N'));
    }
}