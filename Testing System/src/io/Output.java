package io;

import assessments.Responses;
import assessments.Survey;
import assessments.Test;

import java.io.*;

public class Output {

    static public void saveSurvey(Survey s, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("surveys/" + fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(s);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    static public void saveTest(Test t, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("tests/" + fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(t);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    static public void saveResponses(Responses r, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("responses/" + fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(r);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

}
