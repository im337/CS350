package assessments;

import java.util.ArrayList;

public class Responses implements java.io.Serializable {

    public ArrayList<String> responses = new ArrayList<>();

    public void addResponse(String input) {
        this.responses.add(input);
    }

    public void display() {
        int resNum = 1;
        for (String response : responses) {
            System.out.println("Response " + resNum + " : " + responses.get(resNum - 1) + "\n");
        }
    }

    public ArrayList<String> getResponses() {
        return responses;
    }
}
