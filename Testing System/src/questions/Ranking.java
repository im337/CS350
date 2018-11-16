package questions;

import java.util.ArrayList;

public class Ranking extends Matching {

    static ArrayList<String> col = new ArrayList<>();

    public Ranking(String prompt, ArrayList<String> column) {
        super(prompt, column, col); // col is a dummy column, will not be used.
    }

    @Override
    public String display() {
        String temp = prompt + "\n";
        for (int i = 0; i < col1.size(); i++) {
            temp = temp + String.valueOf(i+1) + ": " + col1.get(i) + "\n";
        }
        return temp;

    }

}
