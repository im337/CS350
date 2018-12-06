package tests;

import assessments.Responses;
import assessments.Survey;
import io.Output;
import main.Util;

import java.io.IOException;

public class UtilTest {

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Survey survey = new Survey("survey 1");


        Util util = new Util();
        util.getResponses(survey);
        System.out.println(util.getNumOfResponses(survey));

    }
}
