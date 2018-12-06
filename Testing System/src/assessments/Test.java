package assessments;

import main.Main;
import questions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test extends Survey {

    String surveyName;

    public ArrayList<String> answers = new ArrayList<>();

    private int score;

    public Test(String name) {
        super(name);
        surveyName = name;
    }

    public void addQuestion(Question q, String answer) {
        questions.add(q);
        if (q instanceof ShortAnswer || q instanceof EssayAnswer) {
            answers.add("No answer for free response question.");
        } else {
            answers.add(answer);
        }
    }

    @Override
    public void editQuestion(int questionNum) throws IOException, ClassNotFoundException {
        Scanner reader = new Scanner(System.in).useDelimiter("\\n");

        Question question = questions.get(questionNum-1);

        System.out.println(question.display());
        System.out.println("Input option :\n1) Modify prompt \n2) Modify answer choices \n3) Modify Answer \n4) Exit");
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
            case 3:
                editAnswer(questionNum);
                break;

            case 4:
                Main.TestMenu();

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
            temp = temp + "\nAnswer " + answers.get(qNum - 1) + "\n";
            qNum++;
        }
        return temp;
    }

    public void editAnswer(int questionNum) throws IOException, ClassNotFoundException {
        Scanner reader = new Scanner(System.in).useDelimiter("\\n");

        Question question = questions.get(questionNum - 1);
        System.out.println("Do you wish to modify the correct answer? (y)es or (n)o");
        String input = reader.next();
        if (input.equals("y")) {
            if (question instanceof ShortAnswer || question instanceof EssayAnswer) {
                System.out.println("This question has no answer! Returning...");
            } else if (question instanceof TrueFalse || question instanceof MultipleChoice) {
                System.out.println("Enter the choice for the new correct answer.");
                input = reader.next();
                answers.set(questionNum-1, input);
            } else if (question instanceof Ranking) {
                System.out.println("Enter the new correct rankings, separated by commas. Example: 1,2,3");
                input = reader.next();
                answers.set(questionNum-1, input);

            } else if (question instanceof Matching) {
                System.out.println("Enter the new correct matches with each pair separated by a space, separated by commas. Example: 1 1,2 2,3 3");
                input = reader.next();
                answers.set(questionNum-1, input);
            }
        }

    }
}