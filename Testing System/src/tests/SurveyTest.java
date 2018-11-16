package tests;

import assessments.Survey;
import io.Input;
import questions.Question;
import questions.TrueFalse;

import java.io.IOException;
import java.util.ArrayList;

public class SurveyTest {

    static Input input = new Input();

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Question tfquestion = new TrueFalse("TF Test Prompt");
        String surveyName = "test1";

        Survey survey = new Survey(surveyName);
        survey.addQuestion(tfquestion);

        System.out.println(survey.display());
 //       Survey s = input.loadSurvey("test1");

    }
}
