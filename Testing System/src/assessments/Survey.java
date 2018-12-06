package assessments;

import io.Output;
import questions.*;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import main.Main;

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

    public void editQuestion(int questionNum) throws IOException, ClassNotFoundException {
        Scanner reader = new Scanner(System.in).useDelimiter("\\n");

        Question question = questions.get(questionNum-1);

        System.out.println(question.display());
        System.out.println("Input option :\n1) Modify prompt \n2) Modify answer choices \n3) Exit");
        int input = reader.nextInt();
        switch(input) {
            case 1 :
                System.out.println("Edit question prompt? \n1) yes \n2) no");
                input = reader.nextInt();
                switch (input) {
                    case 1 :
                        System.out.println("Enter new prompt : ");
                        String promptInput = reader.next();
                        question.setPrompt(promptInput);
                        break;
                    case 2 :
                        Main.MainMenu();
                }
                break;
            case 2 :
                if (question instanceof ShortAnswer || question instanceof EssayAnswer || question instanceof TrueFalse) {
                    System.out.println("This type of question type doesn't have a modifiable answer response! Returning...");
                    editQuestion(questionNum);
                    System.exit(0);
                }
                else if (question instanceof MultipleChoice) {
                    System.out.println("Edit which response?");
                    input = reader.nextInt();
                    System.out.println("Enter new response :");
                    String choiceInput = reader.next();
                    MultipleChoice multipleChoice = (MultipleChoice)question;
                    multipleChoice.choices.set(input-1, choiceInput);
                }
                else if (question instanceof Ranking) {
                    System.out.println("Edit which response?");
                    input = reader.nextInt();
                    System.out.println("Enter new response :");
                    String choiceInput = reader.next();
                    Ranking ranking = (Ranking)question;
                    ranking.col1.set(input-1, choiceInput);
                }
                else if (question instanceof Matching) {
                    System.out.println("Edit which row?");
                    input = reader.nextInt();
                    System.out.println("Enter new column 1 response :");
                    String col1Input = reader.next();
                    System.out.println("Enter new column 2 response :");
                    String col2Input = reader.next();
                    Matching matching = (Matching) question;
                    matching.col1.set(input-1, col1Input);
                    matching.col2.set(input-1, col2Input);
                }
                break;
            case 3 :
                Main.SurveyMenu();

        }





    }


}
