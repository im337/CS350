package tests;

import questions.MultipleChoice;
import questions.Question;
import questions.TrueFalse;

import java.util.ArrayList;

public class QuestionsTest {

    public static void main(String args[]) {

        //Multiple Choice
        ArrayList<String> choices = new ArrayList<>();
        choices.add("choice 1");
        choices.add("choice 2");
        choices.add("choice 3");
        choices.add("choice 4");

        Question mcquestion = new MultipleChoice("MC Test Prompt", choices);

        System.out.println(mcquestion.display());

        //True False
        Question tfquestion = new TrueFalse("TF Test Prompt");
        System.out.println(tfquestion.display());
    }
}
