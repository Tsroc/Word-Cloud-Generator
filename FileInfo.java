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
    public void setFile(String file){
        this.file = file;
    }//setFile()
    public String getFile(){
        return this.file;
    }//getFile()
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
    //may be smarter way to determine if user input is a file or web 
    private char determineFileInType(){
        // logically decide how to determine better if is website or file.
        if ((this.getFile().substring(0, 4).equals("http")) || (this.getFile().substring(0, 3).equals("www.")))
            return 'w';
        if (this.getFile().substring(this.getFile().length() - 3, this.getFile().length()).equals("txt"))

            return 'f';
        return ' ';
    }//determineFileInType()
}