/*
Author: Eoin Wilkie
Class information:
    This class creates the list of ignore words. Values are assigned to a TreeSet, values are read from a file and set to upper case.
*/

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
    public void setIgnoreFile(String file){
        //Big-O running time: O(1) 
        this.ignoreFile = file;
    }//setIgnoreFile()

    public String getIgnoreFile(){
        //Big-O running time: O(1) 
        return this.ignoreFile;   
    }//getIgnoreFile()

    public void setIgnoreWords(){
        //Big-O running time: O(n), n being the length of the file ignore words file.
        //I do not believe it can be improved much, certain things must happen. Ignore file must be read by character.

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
        //Big-O running time: O(1) 
        
        return this.ignoreWords != null ? this.ignoreWords: null;
    }//getIgnoreWords()
    //===== Setters/Getters[END] =====//
}