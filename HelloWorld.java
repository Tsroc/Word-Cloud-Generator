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
        //menu.displayTable();

        //System.out.println(menu.getIgnoreList());
        //used to determine which % the word should fall into
        //eg: index 0 is highest, will be assigned size/color to match
        int i = 0;
        int factor = 5;
        float maxWordCount = 0;
        float fWordValue;
        int iWordValue;

        for (Map.Entry<String, Integer> ft: menu.frequencyTable.entrySet()){
            if (i == 0) maxWordCount = ft.getValue();
            //wordValue = ((maxWordCount - ft.getValue()) - maxWordCount);// % 10 * 2;
            fWordValue = (ft.getValue() / maxWordCount) * (factor / 1);
            if (fWordValue < factor - 0.5) { //seems to be a decent place to split it, can be changed.  Looks reasonable for 20, 100, 1000
                fWordValue = --factor;
                maxWordCount = ft.getValue();
            }
            iWordValue = Math.round(fWordValue);
            //System.out.println(i++ + ": " + iWordValue);
            i++;

            wcg = new WordCloudCreator(ft.getKey(), iWordValue);
        }

        WordCloudCreator.saveImg(menu.fileOut);
    }
}