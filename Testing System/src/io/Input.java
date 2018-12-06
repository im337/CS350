package io;

import assessments.Responses;
import assessments.Survey;
import assessments.Test;
import questions.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Input {

    ArrayList<Question> questions = new ArrayList<>();

    static public Survey loadSurvey(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("surveys/" + fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Survey s = (Survey) objectInputStream.readObject();
        return s;
    }

    static public Test loadTest(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("tests/" + fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Test t = (Test) objectInputStream.readObject();
        return t;
    }
    static public Responses loadResponses(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("responses/" + fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Responses r  = (Responses) objectInputStream.readObject();
        return r;
    }


//      //load questions from file
//    public ArrayList<Question> loadQuestions(String fileName) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(fileName);
//        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//
//        boolean cont = true;
//
//        while(cont) {
//            //initialize question q as null
//            Question q = null;
//            try{
//                //attempt to read object into question q
//                q = (Question)objectInputStream.readObject();
//            }
//            catch (IOException | ClassNotFoundException e)
//            {
//                e.printStackTrace();
//            }
//            if (q != null) {
//                //add question q to questions list
//                questions.add(q);
//            }
//            else {
//                //break to loop
//                cont = false;
//            }
//        }
//        return questions;
//    }

}
