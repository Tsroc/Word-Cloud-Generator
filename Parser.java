/*
    Author: Eoin Wilkie
    Class information:
        Parser Interface
*/

//ADD PACKAGE HERE

import java.util.Map;
import java.io.IOException;

public interface Parser {
    public void parse(String s, Map<String, Integer> m) throws IOException;
}