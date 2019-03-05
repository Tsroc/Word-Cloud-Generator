import java.util.Map.Entry;
import java.util.Map;

public class HelloWorld{
    public static void main(String[] args){
        System.out.println("HelloWorld");


        java.util.Map<String, Integer> frequencyTable = new java.util.HashMap<String, Integer>();

        WordCloudCreator wcg;
        WCGmenu menu = new WCGmenu();
        //menu.displayMenu();
        menu.displayTestMenu();

        FileInfo file = new FileInfo(menu.getFileIn(), TableFunctions.getFullTable(frequencyTable));
        frequencyTable = TableFunctions.getFullTable(frequencyTable);
        file.readFile();
        //menu.frequencyTable = menu.getShortTable();
        frequencyTable = TableFunctions.sortTable(frequencyTable);
        frequencyTable = TableFunctions.getShortTable(frequencyTable, menu.getMaxWords());

        //can convert String map to <Word, Integer> map, to carry weight for img creation
        //wordCount will no longer be required, but weight will be. Weight algorithm can be placed elsewhere.

        //used to determine which % the word should fall into
        //eg: index 0 is highest, will be assigned size/color to match
        int i = 0;

        Word[] words = new Word[menu.maxWords];
        
        //requires test and ? fix
        for (Map.Entry<String, Integer> ft: frequencyTable.entrySet()){
            words[i] = new Word(ft.getKey(), ft.getValue());
            if (i >= menu.maxWords) break; else i++;    //may not be necessary
        }

        wcg = new WordCloudCreator(words);
        

        WordCloudCreator.saveImg(menu.fileOut);
    }
}