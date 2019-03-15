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

public class Runner{
    public static void main(String[] args){
        int i;
        char menuSelection = ' ';
        Map<String, Integer> frequencyTable;
        Word[] words;       //must change how Word collection is created, store as priority queue and implement comparitor on queue 
        WordCloudCreator wcg;
        WCGmenu menu;

        Scanner sc = new Scanner(System.in);
        
        //loop menu until exit selected
        //loop will continue until N/n entered
        do{
            i = 0;
            menu = new WCGmenu();
            menu.displayTestMenu();
            //menu.displayMenu();
            frequencyTable = new HashMap<String, Integer>();

            words = new Word[menu.getMaxWords()];

            FileInfo file = new FileInfo(menu.getFileIn(), TableFunctions.getFullTable(frequencyTable));
            frequencyTable = TableFunctions.getFullTable(frequencyTable);
            file.readFile();
            //frequencyTable = TableFunctions.sortTable(frequencyTable);
            frequencyTable = TableFunctions.getShortTable(frequencyTable, menu.getMaxWords());

                //Big-O running time: O(1) 
                //It is my understanding that 0(1) represents a constant value, where as 0(n) represenst the number of elements in a structure
                //This floop will run as many times as was input by the user at the menu screen, which is significantly less than the frequencyTables elements
                //However this may be 0(n) times if using words.length as n, but I do believe this is best case scenario what must be achieved in this loop.
            for (Map.Entry<String, Integer> ft: frequencyTable.entrySet()){
                if (ft.getKey() == ""){ continue; }
                //System.out.printf(".%s, %d\n", ft.getKey(), ft.getValue());
                words[i] = new Word(ft.getKey(), ft.getValue());
                if (i >= menu.getMaxWords()) break; else i++;    //may not be necessary
            }

            wcg = new WordCloudCreator(words);

            //check if file exists already, if so, ust re-set factor sizing and other static var's which have been changed
            WordCloudCreator.saveImg(menu.getFileOut());

            System.out.println("Image created.");
            System.out.print("\nCreate a new word cloud?: Y/n ");
            menuSelection = sc.next().toUpperCase().charAt(0);
            System.out.println();

        }while((menuSelection != 'N'));//||(menuSelection != 'n'));
    }
}