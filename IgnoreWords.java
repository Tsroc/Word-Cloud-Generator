import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

//note, may be best to save this in a tree structure for String strIgnoreWords;
public final class IgnoreWords{
    private String ignoreFile;
    private String ignoreWords;

    public IgnoreWords(String ignoreFile){
        setIgnoreFile(ignoreFile);
        this.setIgnoreWords();
    }//constructor

    //===== Setters/Getters[START] =====//
    public void setIgnoreFile(String file){
        this.ignoreFile = file;
    }//setIgnoreFile()
    public String getIgnoreFile(){
        return this.ignoreFile;   
    }//getIgnoreFile()

    public void setIgnoreWords(){
        String line = null;
		StringBuffer sbIgnoreWords = new StringBuffer();
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(this.getIgnoreFile()));

            while ((line = br.readLine()) != null) {
                sbIgnoreWords.append(line.toLowerCase()); 
                sbIgnoreWords.append(" ");
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.printf("Unable to open file '%s'%n", this.getIgnoreFile());
        } catch (IOException ex) {
            System.out.printf("Error reading file '%s'%n", this.getIgnoreFile());
            // ex.printStackTrace();
        }
        this.ignoreWords = sbIgnoreWords.toString();
    }//getIgnoreList()
    public String getIgnoreWords(){
        return this.ignoreWords != null ? this.ignoreWords: null;
    }//getIgnoreWords()
    //===== Setters/Getters[END] =====//
}