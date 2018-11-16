package assessments;

import io.Output;
import questions.Question;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class Survey implements java.io.Serializable{

    String surveyName;
    public ArrayList<Question> questions = new ArrayList<>();

    public Survey(String surveyName) {
        this.surveyName = surveyName;
    }

    public void addQuestion(Question q) throws IOException {
        questions.add(q);
    }

    //display all questions
    public String display() {
        int qNum = 1;
        String temp = "Displaying " + surveyName + "\n";

        for (Question q : questions) {
            temp = temp + "\nQuestion " + qNum + "\n";
            temp = temp + "\n" + q.display() + "\n";
            qNum++;
        }
        return temp;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getSurveyName() {
        return surveyName;
    }
}
