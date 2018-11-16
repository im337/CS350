package tests;

import assessments.Survey;
import io.Output;
import questions.TrueFalse;

import java.io.IOException;

public class SaveSurveyTest {

    public static void main(String args[]) throws IOException {

        Survey s = new Survey("survey1");
        s.addQuestion(new TrueFalse("Is this a t/f question?"));

        Output.saveSurvey(s, s.getSurveyName());

    }
}
