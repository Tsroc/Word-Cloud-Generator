/*
Author: Eoin Wilkie
Class information:
    This Class provides the console manu for user input. Allows the user to input file/website, wordCloud size and an output name for the word cloud.
    A second menu is provided for test purposes.
*/

public class WCGmenu{
    private String fileIn;
    private int maxWords;
    private String fileOut;

    //===== Setters/Getters[START] =====//
    
        //All setter and getters
        //Big-O running time: O(1) 
        //There are no loops, elements are being accessed at known locations.

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
        //Big-O running time: O(1) 
        //There are no loops, elements are being accessed at known locations.

        java.util.Scanner sc = new java.util.Scanner(System.in);

        //menu
        System.out.println("Command-Line-Menu");
        System.out.print("Enter filename or webpage: ");
        this.setFileIn(sc.nextLine());
        System.out.print("Enter number of words in word cloud: ");
        this.setMaxWords(sc.nextInt());
        sc.nextLine();
        System.out.print("Save file as:(Do not include .png) ");
        this.setFileOut(sc.nextLine());
    }//displayMenu()
}