package io;

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

}
