import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class IgnoreWords{
    private String ignoreFile;  // = "ignorewords.txt";  //can add function to change this
    private String strIgnoreWords;  // = null;           //can change data type which stores this data, ?tree might be prefered for fast search

    public IgnoreWords(String ignoreFile){
        this.ignoreFile = ignoreFile;
        this.setIgnoreWords();
    }

    public String getIgnoreWords(){
        return this.strIgnoreWords != null ? strIgnoreWords: null;
    }

    //may overRide this function to allow file to be passed, not necessary for now
    public void setIgnoreWords(){
        String line = null;
		StringBuffer sbIgnoreWords = new StringBuffer();
		
		//FileReader fr;
        BufferedReader br;
        
        try {
            //fr = new FileReader(ignoreFile);
            //br = new BufferedReader(fr);
            br = new BufferedReader(new FileReader(ignoreFile));

            while ((line = br.readLine()) != null) {
                sbIgnoreWords.append(line.toLowerCase());       //may change to upperCase as output is uppercase.
                sbIgnoreWords.append(" ");
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.printf("Unable to open file '%s'%n", ignoreFile);
        } catch (IOException ex) {
            System.out.printf("Error reading file '%s'%n", ignoreFile);
            // ex.printStackTrace();
        }
        strIgnoreWords = sbIgnoreWords.toString();
    }//getIgnoreList()

}