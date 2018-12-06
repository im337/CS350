package questions;

import java.util.ArrayList;

public class Matching extends Question {

    public ArrayList<String> col1 = new ArrayList<>();
    public ArrayList<String> col2 = new ArrayList<>();

    public Matching(String prompt, ArrayList<String> col1, ArrayList<String> col2) {
        this.prompt = prompt;
        this.col1 = col1;
        this.col2 = col2;
    }

    @Override
    public String display() {
        String temp = prompt + "\n";
        for (int i = 0; i < col1.size(); i++) {
            temp = temp + col1.get(i) + "    " + col2.get(i) + "\n";
        }
        return temp;
    }
}
