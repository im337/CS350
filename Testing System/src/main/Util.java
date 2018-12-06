package main;

import assessments.Responses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import assessments.Survey;
import assessments.Test;
import io.Input;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import questions.EssayAnswer;
import questions.Question;
import questions.ShortAnswer;

import javax.xml.ws.Response;

public class Util {

    public int getNumOfResponses(Survey survey) throws IOException, ClassNotFoundException {
        ArrayList<Responses> responsesList = getResponses(survey);
        return responsesList.size();
    }

    public ArrayList<Responses> getResponses(Survey survey) throws IOException, ClassNotFoundException {
        String surveyName = survey.getSurveyName();

        ArrayList<Responses> responsesList = new ArrayList<>();
        File currentDirectory = new File("./responses/");
        File[] currentDir = currentDirectory.listFiles();
        for (File file : currentDir) {
            if (file.getName().contains(surveyName)) {
                Responses response = Input.loadResponses(file.getName());
                responsesList.add(response);
            }
        }

        return responsesList;
    }

    public void tabulateResponses(Survey survey) throws IOException, ClassNotFoundException {
        ArrayList<Responses> responsesList = getResponses(survey);
        int responseIteration = 0;
        for (Question question : survey.questions) {
            System.out.println("Question number " + (responseIteration+1));
            System.out.println(question.display() + "\nResponses:\n");

            for (Responses response : responsesList) {
                System.out.println(response.responses.get(responseIteration));
            }
            System.out.println();
            responseIteration++;
        }
    }

    public ArrayList<Double> gradeTests(Test test) throws IOException, ClassNotFoundException {
        ArrayList<Double> grades = new ArrayList<>();
        ArrayList<Integer> numRights = new ArrayList<>();
        int numRight = 0;
        int numQuestions = 0;
        for (Responses r : getResponses(test)) {
            numQuestions = test.questions.size();
            int i = 0;
            numRight = 0;
            for (Question q : test.questions) {
                if (q instanceof ShortAnswer || q instanceof EssayAnswer) {
                    numQuestions--;
                }
                else if (r.getResponses().get(i).equals(test.answers.get(i))) {
                    numRight++;
                }
                i++;
            }
            numRights.add(numRight);
        }
        int j = 0;
        for (int n : numRights) {
            double grade = ((double) numRights.get(j) / (double) numQuestions) * 100;
            grades.add(grade);
            j++;
        }
        return grades;
    }
}
