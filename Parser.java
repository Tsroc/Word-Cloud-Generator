/*
    Author: Eoin Wilkie
    Class information:
        Parser Interface
*/

import java.util.Map;
import java.io.IOException;

public interface Parser {
    public void parse(String s, Map<String, Integer> m) throws IOException;
}