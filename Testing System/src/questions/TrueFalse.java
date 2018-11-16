package questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrueFalse extends MultipleChoice {

    protected static ArrayList<String> tf = new ArrayList<>(Arrays.asList("True", "False"));	// only choices in a true/false question

    public TrueFalse(String p) {
        super(p, tf);
    }

}
