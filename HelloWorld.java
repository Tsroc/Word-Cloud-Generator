import java.util.Map.Entry;
import java.util.Map;

public class HelloWorld{
    public static void main(String[] args){
        System.out.println("HelloWorld");
        WordCloudCreator wcg;
        WCGmenu menu = new WCGmenu();
        menu.displayMenu();
        new FileInfo().readFile(menu.fileIn, menu.getFullTable());
        //menu.frequencyTable = menu.getShortTable();
        menu.frequencyTable = menu.sortTable();
        menu.frequencyTable = menu.getShortTable();

        //can convert String map to <Word, Integer> map, to carry weight for img creation
        //wordCount will no longer be required, but weight will be. Weight algorithm can be placed elsewhere.

        //used to determine which % the word should fall into
        //eg: index 0 is highest, will be assigned size/color to match
        int i = 0;

        Word[] words = new Word[menu.maxWords];
        
        //requires test and ? fix
        for (Map.Entry<String, Integer> ft: menu.frequencyTable.entrySet()){
            words[i] = new Word(ft.getKey(), ft.getValue());
            wcg = new WordCloudCreator(words[i]);
            if (i >= menu.maxWords) break; else i++;
        }
        

        WordCloudCreator.saveImg(menu.fileOut);
    }
}