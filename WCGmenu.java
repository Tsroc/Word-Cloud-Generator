import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class WCGmenu{
    String fileIn;
    int maxWords;
    String fileOut;

    //===== Setters/Getters[START] =====//
    public void setFileIn(String fileIn){
        this.fileIn = fileIn;
    }//setFileIn()
    public String getFileIn(){
        return this.fileIn;
    }//getFileIn()
    public void setMaxWords(int maxWords){
        this.maxWords = maxWords;
    }//setMaxWords()
    public int getMaxWords(){
        return this.maxWords;
    }//getMaxWords()
    public void setFileOut(String fileOut){
        this.fileOut = fileOut;
    }//getFileOut()
    public String getFileOut(){
        return this.fileOut;
    }//setFileOut()
    //===== Setters/Getters[END] =====//

    public void displayMenu(){
        java.util.Scanner sc = new java.util.Scanner(System.in);

        //menu
        System.out.println("Command-Line-Menu");
        System.out.println("Enter FILENAME (http://www.google.com/) (DeBelloGallico.txt)");
        this.setFileIn(sc.nextLine());
        System.out.println("Enter MAXWORDS");
        this.setMaxWords(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter SAVEAS");
        this.setFileOut(sc.nextLine());
    }//displayMenu()

    //TESTS
    public void displayTestMenu(){
        java.util.Scanner sc = new java.util.Scanner(System.in);

        //menu
        System.out.println("Command-Line-Menu");
        System.out.println("Enter FILENAME (http://www.google.com/) (DeBelloGallico.txt)");
        this.setFileIn("DeBelloGallico.txt");
        //this.setFileIn("WarAndPeace-LeoTolstoy.txt");
        //this.setFileIn("PoblachtNaHEireann.txt");
        //this.setFileIn("PoblachtNaHEireann.txt");
        //this.setFileIn("https://www.thejournal.ie/two-arrested-finglas-drugs-samurai-swords-4524379-Mar2019/");
        System.out.println("Enter MAXWORDS");
        this.setMaxWords(25);
        System.out.println("Enter SAVEAS");
        this.setFileOut("WordCloudImg");
    }//displayMenu()
}