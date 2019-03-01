import java.util.Map;

public class FileInfo{

    public void readFile(String fileIn, Map<String, Integer> ft){
        //should call parseFile() or parseHtml()
        Parser p;
        char fileInType = determineFileInType(fileIn);

        switch(fileInType){
            case 'w': 
                    p = new ParserHtml();
                    p.parse(fileIn, ft);
                break;
            case 'f':  
                    p = new ParserFile();
                    p.parse(fileIn, ft);
                break;
            default:
                System.out.println("Could not determine FILE/URL.");
                break;
        }
    }//readFile()

    private char determineFileInType(String fileIn){
        // logically decide how to determine better if is website or file.
        if ((fileIn.substring(0, 4).equals("http")) || (fileIn.substring(0, 3).equals("www.")))
            return 'w';
        if (fileIn.substring(fileIn.length() - 3, fileIn.length()).equals("txt"))
            return 'f';
        return ' ';
    }//determineFileInType()
}