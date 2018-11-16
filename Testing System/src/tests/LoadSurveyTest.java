package tests;

import assessments.Survey;
import io.Input;

import java.io.IOException;

public class LoadSurveyTest {

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Survey s = Input.loadSurvey("survey1");
        System.out.println(s.display());
    }
}
