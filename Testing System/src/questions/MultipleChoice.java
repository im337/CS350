package questions;

import java.util.ArrayList;

public class MultipleChoice extends Question{

    public ArrayList<String> choices;

    public MultipleChoice(String p, ArrayList<String> choices) {

        prompt = p;
        this.choices = choices;

    }

    public String display() {
        int i = 1;
        String temp = prompt + "\nChoices:\n";
        for (String choice : choices) {
            temp = temp + Integer.toString(i) + ": " + choice + "\n";
            i++;
        }

        return temp;
    }

}
