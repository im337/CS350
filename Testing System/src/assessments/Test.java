package assessments;

import questions.EssayAnswer;
import questions.Question;
import questions.ShortAnswer;

import java.io.IOException;
import java.util.ArrayList;

public class Test extends Survey {

    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();

    private int score;

    public Test(String name) {
        super(name);
    }

    public void addQuestion(Question q, String answer) {
        questions.add(q);
        if(q instanceof ShortAnswer || q instanceof EssayAnswer) {
            answers.add("No answer for free response question.");
        }
        else {
            answers.add(answer);
        }
    }

    public void removeQuestion(int i) {
        questions.remove(i);
        answers.remove(i);
    }

    public String display() {
        int qNum = 1;
        String temp = "Displaying " + surveyName + "\n";

        for (Question q : questions) {
            temp = temp + "\nQuestion " + qNum + "\n";
            temp = temp + "\n" + q.display() + "\n";
            temp = temp + "\nAnswer " + answers.get(qNum-1) + "\n";
            qNum++;
        }
        return temp;
    }
}
