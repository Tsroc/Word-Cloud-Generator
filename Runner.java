/*
Author: Eoin Wilkie
Class information:
    Runner class contains the main method for the program.
    Contain a HashMap which is assigned values from the FileInfo Class, this Map will then be reduced in size to the users requested word cloud size.
    This array is then passed through the WordCloudCreator Class to create the word cloud.
*/

//ADD PACKAGE HERE

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Queue;
import java.util.PriorityQueue;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Runner{
    public static void main(String[] args){
        char menuSelection = ' ';
        long startTime, endTime;
        WCGmenu menu;
        Map<String, Integer> frequencyTable;
        Queue<Word> words, words_cloud;
        Word tempWord;
        WordCloudCreator wcg;

        Scanner sc = new Scanner(System.in);
        
        //loop will continue until N/n entered
        do{
            menu = new WCGmenu();
            menu.displayMenu();

            frequencyTable = new HashMap<String, Integer>();
            words = new PriorityQueue<>(menu.getMaxWords(), new WordComparator());      //orders list
            words_cloud = new PriorityQueue<>(menu.getMaxWords(), new WordComparator());      

            try{
                FileInfo file = new FileInfo(menu.getFileIn(), frequencyTable);
                System.out.println("Reading file and creating HashMap...");
                startTime = System.nanoTime();
                file.readFile();
                endTime = System.nanoTime();
                System.out.println("Time taken: " + (endTime - startTime)/1000000 + " milliseconds.");

                //Big-O running time: nLog(n)
                //Adds every element of HashMap to PriorityQueue and sorts it.
                System.out.println("Creating PriorityQeueue...");
                startTime = System.nanoTime();
                for (Map.Entry<String, Integer> ft: frequencyTable.entrySet()){
                    if (ft.getKey() == "")
                        continue;
                    words.offer(new Word(ft.getKey(), ft.getValue()));
                    //all must be placed on queue to order the queue
                }

                //second priorityQueue created from the highest value elements of first queue, based on word cloud size input
                for(int i = 0; i <= menu.getMaxWords(); i++){
                    tempWord = words.poll();
                    words_cloud.offer(tempWord);
                }
                endTime = System.nanoTime();
                System.out.println("Time taken: " + (endTime - startTime)/1000000 + " milliseconds.");

                System.out.println("Creating WordCloud...");
                startTime = System.nanoTime();
                wcg = new WordCloudCreator(words_cloud);
                WordCloudCreator.saveImg(menu.getFileOut());
                endTime = System.nanoTime();
                System.out.println("Time taken: " + (endTime - startTime)/1000000 + " milliseconds.");
            }
            catch (Exception ex){
                //many exceptions can be thrown here because the parser is not throwing its error and the code which follows it does not do so conditionally based on its success
                //but this will ensure the program will not crash if file is entered incorrectly
                //will try to clean this up.
                System.out.println("Exception thrown: " + ex.toString());
            }

            System.out.println("Create a new word cloud? ");
            menuSelection = sc.next().toUpperCase().charAt(0);
            System.out.println();
        }while((menuSelection != 'N'));
    }
}