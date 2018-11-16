package main;

import assessments.Survey;
import assessments.Test;
import io.Input;
import io.Output;
import questions.EssayAnswer;
import questions.MultipleChoice;
import questions.ShortAnswer;
import questions.TrueFalse;
import questions.Matching;
import questions.Ranking;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    protected static ArrayList<String> mainChoices = new ArrayList<>(Arrays.asList("1", "2"));	// MainMenu choices
    protected static ArrayList<String> surveyChoices = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));// Test/Survey choices
    protected static ArrayList<String> questionChoices = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7")); //Question adding choices

    static Scanner reader = new Scanner(System.in).useDelimiter("\\n");
    private static Survey currentSurvey;
    private static Test currentTest;

    String input;

    public static void MainMenu() throws IOException{

        System.out.println("Select an assessment type: ");
        System.out.println("1) Survey\n2) Test\n");
        String input = reader.next();

        if(!(mainChoices.contains(input))) // if input is not a valid option
        {
            System.out.println("Not a valid option. Please enter a number from the menu provided.");
            MainMenu();	// handle mismatched input by recursive method call
            System.exit(0);
        }
        else
        {
            switch (input) {
                case "1" : SurveyMenu();
                case "2" : TestMenu();
            }
        }
    }

    public static void SurveyMenu() throws IOException {
        System.out.println("1) Create and save a new Survey\n2) Display a Survey\n3) Load and display a Survey\n4) Save a Survey(does nothing)\n5) Quit");
        String input = reader.next();

        if(!(surveyChoices.contains(input))) // if input is not a valid option
        {
            System.out.println("Not a valid option. Please enter a number from the menu provided.");
            SurveyMenu();            // handle mismatched input by recursive method call
            System.exit(0);
        }
        else
        {
            switch (input) {
                case "1" :
                    System.out.println("Enter the name of your survey.\n");
                    String name = reader.next();
                    Survey survey = new Survey(name);
                    currentSurvey = survey;
                    SurveyQuestionMenu();
                    System.exit(0);

                case "2" :
                    System.out.println("List of surveys: \n");
                    File folder = new File("surveys/");
                    File[] listOfFiles = folder.listFiles();

                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            System.out.println(listOfFiles[i].getName());
                        }
                    }
                    System.out.println("\nEnter the name of the survey you wish to display");
                    input = reader.next();

                    try {
                        currentSurvey = Input.loadSurvey(input);
                        System.out.println(currentSurvey.display());
                        SurveyMenu();
                        System.exit(0);
                    } catch (FileNotFoundException | ClassNotFoundException e) {
                        System.out.println("Survey not found! Going back to survey menu");
                        SurveyMenu();
                        System.exit(0);

                    }

                case "3" :
                    System.out.println("List of surveys: \n");
                    folder = new File("surveys/");
                    listOfFiles = folder.listFiles();

                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            System.out.println(listOfFiles[i].getName());
                        }
                    }
                    System.out.println("\nEnter the name of the survey you wish to load, and display");
                    input = reader.next();

                    try {
                        currentSurvey = Input.loadSurvey(input);
                        System.out.println(currentSurvey.display());
                        SurveyMenu();
                        System.exit(0);
                    } catch (FileNotFoundException | ClassNotFoundException e) {
                        System.out.println("Survey not found! Going back to survey menu");
                        SurveyMenu();
                        System.exit(0);
                    }

                case "4" :
                    System.out.println("For this program, surveys are automatically saved to the /surveys/ directory after they are finished being created, via option 1");
                    System.out.println("Thus, I feel like it's fair that I included an informative message if this option is selected. \n\n Returning to main menu \n\n");

                    MainMenu();
                    System.exit(0);

                case "5" :
                    System.out.println("Program quitting!");
                    System.exit(0);
            }
        }
    }

    public static void SurveyQuestionMenu() throws IOException {

        String prompt;
        String input;
        boolean stillAddingQuestions = true;

        while(stillAddingQuestions) {
            System.out.println("Note: Survey is saved upon exiting the question adding menu (7).");
            System.out.println("1) Add a new T/F question\n2) Add a new multiple choice question\n3) Add a new short response question\n4) Add a new essay question\n5) Add a new ranking question\n6) Add a new matching question\n7) Exit");

            input = reader.next();

            if(!(questionChoices.contains(input))) // if input is not a valid option
            {
                System.out.println("Not a valid option. Please enter a number from the menu provided.");
                SurveyQuestionMenu();	// handle mismatched input by recursive method call
                System.exit(0);
            }

            switch (input) {

                case "1":
                    System.out.println("Enter the prompt for your True/False question:");
                    currentSurvey.addQuestion(new TrueFalse(reader.next()));
                    break;

                case "2":
                    ArrayList<String> choices = new ArrayList<String>();

                    System.out.println("Enter the prompt for your multiple choice question:");
                    prompt = reader.next();
                    System.out.println("How many choices will the question have? Enter a number.");
                    int choiceNum = Integer.parseInt(reader.next());
                    for (int i = 1; i <= choiceNum; i++) {
                        System.out.println("Enter choice " + Integer.valueOf(i));
                        choices.add(reader.next());
                    }
                    currentSurvey.addQuestion(new MultipleChoice(prompt, choices));
                    break;

                case "3":
                    System.out.println("Enter the prompt for your Short Answer question:");
                    prompt = reader.next();
                    System.out.println("Enter the character limit for the response to this question:");
                    int charLim = Integer.parseInt(reader.next());
                    currentSurvey.addQuestion(new ShortAnswer(prompt, charLim));        // create new short response
                    break;

                case "4":
                    System.out.println("Enter the prompt for your Essay question:");
                    prompt = reader.next();
                    currentSurvey.addQuestion(new EssayAnswer(prompt));        // create new essay
                    break;

                case "5":
                    System.out.println("Enter the prompt for your Ranking question:");
                    prompt = reader.next();
                    System.out.println("How many items will there be?");
                    int rNum = reader.nextInt();
                    ArrayList<String> rankingChoices = new ArrayList<String>();
                    for (int i = 1; i <= rNum; i++) {
                        System.out.println("Enter Item #" + i + ".");
                        rankingChoices.add(reader.next());
                    }
                    currentSurvey.addQuestion(new Ranking(prompt, rankingChoices));    // create new ranking
                    break;


                case "6":
                    System.out.println("Enter the prompt for your Matching question:");
                    prompt = reader.next();
                    System.out.println("How many choices will there be?");
                    int num = reader.nextInt();
                    ArrayList<String> c1 = new ArrayList<String>();
                    ArrayList<String> c2 = new ArrayList<String>();
                    for (int i = 1; i <= num; i++) {
                        System.out.println("Enter Choice #" + i + " for the first column.");
                        c1.add(reader.next());
                        System.out.println("Enter Choice #" + i + " for the second column.");
                        c2.add(reader.next());
                    }
                    currentSurvey.addQuestion(new Matching(prompt, c1, c2));    // create new matching
                    break;

                case "7":
                    System.out.println("Survey exiting.");
                    Output.saveSurvey(currentSurvey, currentSurvey.getSurveyName());
                    System.out.println("Survey saved.");
                    stillAddingQuestions = false;
                    MainMenu();
                    break;
            }
        }
    }

    public static void TestMenu() throws IOException {
        System.out.println("1) Create and save a new Test\n2) Display a Test\n3) Load and display a Test\n4) Save a Test(does nothing)\n5) Quit");
        String input = reader.next();

        if(!(surveyChoices.contains(input))) // if input is not a valid option
        {
            System.out.println("Not a valid option. Please enter a number from the menu provided.");
            TestMenu();	// handle mismatched input by recursive method call
        }
        else
        {
            switch (input) {
                case "1" :
                    System.out.println("Enter the name of your Test.\n");
                    String name = reader.next();
                    Test test = new Test(name);
                    currentTest = test;
                    TestQuestionMenu();
                    break;

                case "2" :
                    System.out.println("List of tests: \n");
                    File folder = new File("tests/");
                    File[] listOfFiles = folder.listFiles();

                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            System.out.println(listOfFiles[i].getName());
                        }
                    }
                    System.out.println("\nEnter the name of the test you wish to display");
                    input = reader.next();

                    try {
                        currentTest = Input.loadTest(input);
                        System.out.println(currentTest.display());
                        TestMenu();
                        break;
                    } catch (FileNotFoundException | ClassNotFoundException e) {
                        System.out.println("Survey not found! Going back to test menu");
                        TestMenu();
                        break;
                    }

                case "3" :
                    System.out.println("List of tests: \n");
                    folder = new File("tests/");
                    listOfFiles = folder.listFiles();

                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            System.out.println(listOfFiles[i].getName());
                        }
                    }
                    System.out.println("\nEnter the name of the test you wish to display");
                    input = reader.next();

                    try {
                        currentTest = Input.loadTest(input);
                        System.out.println(currentTest.display());
                        TestMenu();
                        break;
                    } catch (FileNotFoundException | ClassNotFoundException e) {
                        System.out.println("Survey not found! Going back to test menu");
                        TestMenu();
                        break;
                    }

                case "4" :
                    System.out.println("For this program, tests are automatically saved to the /tests/ directory after they are finished being created, via option 1");
                    System.out.println("Thus, I feel like it's fair that I included an informative message if this option is selected. \n\n Returning to main menu \n\n");

                    MainMenu();
                    break;

                case "5" :
                    System.out.println("Program quitting!");
                    break;
            }
        }
    }

    public static void TestQuestionMenu() throws IOException {

        String answer;
        String prompt;
        String input;

        boolean stillAddingQuestions = true;

        System.out.println("Note: Test is saved upon exiting the question adding menu (7).");
        System.out.println("1) Add a new T/F question\n2) Add a new multiple choice question\n3) Add a new short response question\n4) Add a new essay question\n5) Add a new ranking question\n6) Add a new matching question\n7) Exit");
        while(stillAddingQuestions) {
            input = reader.next();

            if(!(questionChoices.contains(input))) // if input is not a valid option
            {
                System.out.println("Not a valid option. Please enter a number from the menu provided.");
                TestQuestionMenu();	// handle mismatched input by recursive method call
            }

            switch (input) {

                case "1":
                    System.out.println("Enter the prompt for your True/False question:");
                    prompt = reader.next();
                    System.out.println("Enter the answer for your True/False question (true/false):");
                    answer = reader.next();
                    if (answer.equals("true")) {
                        answer = "1";
                    } else {
                        answer = "2";
                    }
                    currentTest.addQuestion(new TrueFalse(prompt),answer);
                    TestQuestionMenu();
                    break;

                case "2":
                    ArrayList<String> choices = new ArrayList<String>();

                    System.out.println("Enter the prompt for your multiple choice question:");
                    prompt = reader.next();
                    System.out.println("How many choices will the question have? Enter a number. Keep in mind number of the correct answer.");
                    int choiceNum = Integer.parseInt(reader.next());
                    for (int i = 1; i <= choiceNum; i++) {
                        System.out.println("Enter choice " + Integer.valueOf(i));
                        choices.add(reader.next());
                    }
                    System.out.println("Which number choice was the answer to your question?");
                    answer = reader.next();
                    currentTest.addQuestion(new MultipleChoice(prompt, choices), answer);
                    TestQuestionMenu();
                    break;

                case "3":
                    answer = null;
                    System.out.println("Note: Short answer questions do not have an answer, and will be graded manually.:");
                    System.out.println("Enter the prompt for your Short Answer question:");
                    prompt = reader.next();
                    System.out.println("Enter the character limit for the response to this question:");
                    int charLim = Integer.parseInt(reader.next());
                    currentTest.addQuestion(new ShortAnswer(prompt, charLim), answer);        // create new short response
                    TestQuestionMenu();
                    break;

                case "4":
                    answer = null;
                    System.out.println("Note: Essay questions do not have an answer, and will be graded manually.:");
                    System.out.println("Enter the prompt for your Essay question:");
                    prompt = reader.next();
                    currentTest.addQuestion(new EssayAnswer(prompt), answer);        // create new essay
                    TestQuestionMenu();
                    break;

                case "5":
                    System.out.println("Enter the prompt for your Ranking question:");
                    prompt = reader.next();
                    System.out.println("How many items will there be?");
                    int rNum = reader.nextInt();
                    ArrayList<String> rankingChoices = new ArrayList<String>();
                    String rankingAnswerOrder = "";

                    for (int i = 1; i <= rNum; i++) {
                        System.out.println("Enter Item #" + i + ":");
                        rankingChoices.add(reader.next());
                    }

                    //creates answer for ranking question as a string, from top to bottom. example: 3 1 2
                    System.out.println("Enter the answer for your Ranking question, by entering item numbers from ascending to descending order:");
                    for (int i = 1; i <= rNum; i++) {
                        System.out.println("Enter Rank #" + i + ":");
                        rankingAnswerOrder = rankingAnswerOrder + reader.next() + " ";
                    }

                    currentTest.addQuestion(new Ranking(prompt, rankingChoices), rankingAnswerOrder);    // create new ranking
                    TestQuestionMenu();
                    break;

                case "6":
                    System.out.println("Enter the prompt for your Matching question:");
                    prompt = reader.next();
                    System.out.println("How many choices will there be?");
                    int num = reader.nextInt();
                    ArrayList<String> c1 = new ArrayList<String>();
                    ArrayList<String> c2 = new ArrayList<String>();
                    String matchingCorrectAnswers = "";
                    for (int i = 1; i <= num; i++) {
                        System.out.println("Enter Choice #" + i + " for the first column.");
                        c1.add(reader.next());
                        System.out.println("Enter Choice #" + i + " for the second column.");
                        c2.add(reader.next());
                    }

                    System.out.println("Enter the correct match for the following items in column 1.");
                    for (int i = 1; i <= num; i++) {
                        System.out.println("Item " + i + ": " + c1.get(i-1) + ", enter correct choice number for second column :");
                        matchingCorrectAnswers = matchingCorrectAnswers + reader.next();
                    }
                    currentTest.addQuestion(new Matching(prompt, c1, c2), matchingCorrectAnswers);    // create new matching
                    TestQuestionMenu();
                    break;

                case "7":
                    System.out.println("Test exiting.");
                    Output.saveTest(currentTest, currentTest.getSurveyName());
                    System.out.println("Test saved.");
                    stillAddingQuestions = false;
                    MainMenu();
                    break;
            }
        }
    }

    public void main() {
    String string = "ay";

    }



}
