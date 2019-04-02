/*
Author: Eoin Wilkie
Class information:
    This class consists of functions to manipulate an input file for the word cloud generation
    Type of input file will be identified(www. or file) and,
    the readFile() calls the Parser Class and assigns the words and frequency of these words to a map.
*/

import java.util.Map;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException; 

public class FileInfo{
    private String file;
    private Map<String, Integer> frequencyTable;

    public FileInfo(String fileIn, Map<String, Integer> frequencyTable){
        this.setFile(fileIn);
        this.setFrequencyTable(frequencyTable);
    }//constructor

    //===== Setters/Getters[START] =====//

        //All setter and getters
        //Big-O running time: O(1) 
        //There are no loops, elements are being accessed at known locations.
        
    public void setFile(String file){
        this.file = file;
    }//setFile()

    public String getFile(){
        return this.file;
    }//getFile()
    
    public void setFrequencyTable(Map<String, Integer> map){
        //Big-O running time: O(1) I believe
        //This is shallow a shallow copy, each element is not copied individually

        this.frequencyTable = map;
    }//setFrequencyTable()

    public Map<String, Integer> getFrequencyTable(){
        return this.frequencyTable;
    }//getFrequencyTable
    //===== Setters/Getters[END] =====//

    /*
        This function reads for user input.
        It calls the determineFileInType() function and calls parseHtml() for webpage and parseFile() for file
    */
    public void readFile() throws  FileNotFoundException, IOException, MalformedURLException{
        //Big-O running time: O(1) 
        //There are no loops.

        Parser p;
        char fileInType = this.determineFileInType();

        switch(fileInType){
            case 'w': 
                    p = new ParserHtml();
                    p.parse(this.getFile(), this.getFrequencyTable());
                break;
            case 'f':  
                    p = new ParserFile();
                    p.parse(this.getFile(), this.getFrequencyTable());
                break;
            default:
                System.out.println("Could not determine FILE/URL.");
                break;
        }
    }//readFile()

    /*
        This function determines what type of input the user has entered.
        It is called as part of readFile()
        Determines a website by being prefixed with http or www.
        Determines a file by being postfixed with txt
    */
    private char determineFileInType(){
        //Big-O running time: O(1)
        //searching at max 8 characters total

        //System.out.println(this.getFile());
        if ((this.getFile().substring(0, 4).equals("http")) || (this.getFile().substring(0, 3).equals("www.")))
            return 'w';
        if (this.getFile().substring(this.getFile().length() - 3, this.getFile().length()).equals("txt"))
            return 'f';
        return ' ';
    }//determineFileInType()
}