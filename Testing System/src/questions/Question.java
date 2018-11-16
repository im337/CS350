package questions;

public abstract class Question implements java.io.Serializable {

    protected String prompt;

    public abstract String display();

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

}
