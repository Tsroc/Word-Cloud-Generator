import java.util.Map.Entry;
import java.util.Map;

public class HelloWorld{
    public static void main(String[] args){
        System.out.println("HelloWorld");
        WCGmenu menu = new WCGmenu();
        WordCloudCreator wcg;
        menu.displayMenu();
        menu.readFile();
        //menu.frequencyTable = menu.getShortTable();
        menu.frequencyTable = menu.sortTable();
        menu.frequencyTable = menu.getShortTable();
        menu.displayTable();

        //System.out.println(menu.getIgnoreList());

        for (Map.Entry<String, Integer> ft: menu.frequencyTable.entrySet()){
            wcg = new WordCloudCreator(ft.getKey());
        }

        WordCloudCreator.saveImg(menu.fileOut);
    }
}