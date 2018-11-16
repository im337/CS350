package tests;

import assessments.Survey;
import assessments.Test;
import io.Input;
import questions.Question;
import questions.TrueFalse;

import java.io.IOException;

//funniest name ever!
public class TestTest {

    static Input input = new Input();

    public static void main(String args[]) throws IOException, ClassNotFoundException {
//        Question tfquestion = new TrueFalse("TF Test Prompt");
//        String testName = "test1";
//
//        Test test = new Test(testName);
//        test.addQuestion(tfquestion, "1");

        Test t = Input.loadTest("test1");
        System.out.println("------------");

        System.out.println(t.display());
    }

}
