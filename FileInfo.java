/*
Author: Eoin Wilkie
Class information:
    This class consists of functions to manipulate an input file for the word cloud generation
    Type of input file will be identified(www. or file) and,
    the readFile() calls the Parser Class and assigns the words and frequency of these words to a map.
*/

//ADD PACKAGE HERE

import java.util.Map;

public class FileInfo{
    private String file;
    private Map<String, Integer> frequencyTable;

    public FileInfo(String fileIn, Map<String, Integer> frequencyTable){
        //replace with setters and getters;
        this.setFile(fileIn);
        this.setFrequencyTable(frequencyTable);
    }//constructor

    //===== Setters/Getters[START] =====//
        //Big-O running time: O(1) as far as I am aware
        //there is no loops here, simply returning the variables.
    public void setFile(String file){
        this.file = file;
    }//setFile()
    public String getFile(){
        return this.file;
    }//getFile()
        //Big-O running time: O(1) I believe
        //This is shallow a shallow copy, each element is not copied individually
    public void setFrequencyTable(Map<String, Integer> map){
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

        //Big-O running time: O(1) 
        //There is nothing which impacts runtime. There is no loops and not much data being manipulated.
    public void readFile(){
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
    */
        //Big-O running time: O(1), n being length of String this.file
        //this methos id not quite O(n) as I have reduced the search to searching 8 characters total
    private char determineFileInType(){
        // logically decide how to determine better if is website or file.
        if ((this.getFile().substring(0, 4).equals("http")) || (this.getFile().substring(0, 3).equals("www.")))
            return 'w';
        if (this.getFile().substring(this.getFile().length() - 3, this.getFile().length()).equals("txt"))

            return 'f';
        return ' ';
    }//determineFileInType()
}