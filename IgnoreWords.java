/*
Author: Eoin Wilkie
Class information:
    This class creates the list of ignore words. Values are assigned to a TreeSet, values are read from a file and set to upper case.
*/

//ADD PACKAGE HERE

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public final class IgnoreWords{
    private String ignoreFile;
    private Set<String> ignoreWords;

    public IgnoreWords(String ignoreFile){
        setIgnoreFile(ignoreFile);
        this.setIgnoreWords();
    }//constructor

    //===== Setters/Getters[START] =====//
    //Big-O running time: O(1) as far as I am aware
    //there is no loops here, simply returning the variables.
    public void setIgnoreFile(String file){
        this.ignoreFile = file;
    }//setIgnoreFile()
    public String getIgnoreFile(){
        return this.ignoreFile;   
    }//getIgnoreFile()

    public void setIgnoreWords(){
        //Big-O running time: O(n) as far as I am aware
        //The following is pretty horrible but I do not believe it can be improved much
        //String line seems awful and should be replaced with StringBuffer for re-used memory space
        //as for the file reading, it starts at the begining and moved to the end inspecting each character element along the way

        this.ignoreWords = new TreeSet<>();
        String line = null;
		StringBuffer sbIgnoreWords = new StringBuffer();
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(this.getIgnoreFile()));

            while ((line = br.readLine()) != null) {
                sbIgnoreWords.setLength(0);
                sbIgnoreWords.append(line.toUpperCase()); 
                this.ignoreWords.add(sbIgnoreWords.toString());
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.printf("Unable to open file '%s'%n", this.getIgnoreFile());
        } catch (IOException ex) {
            System.out.printf("Error reading file '%s'%n", this.getIgnoreFile());
            // ex.printStackTrace();
        }
    }//getIgnoreList()

    public Set<String> getIgnoreWords(){
        //Big-O running time: O(1) as far as I am aware
        //Returns the object

        return this.ignoreWords != null ? this.ignoreWords: null;
    }//getIgnoreWords()
    //===== Setters/Getters[END] =====//
}