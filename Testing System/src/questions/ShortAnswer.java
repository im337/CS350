package questions;

public class ShortAnswer extends EssayAnswer{

    int charLimit;

    public ShortAnswer(String p, int cLim) {
        super(p);
        this.charLimit = cLim;
    }

    @Override
    public String display() {
        return prompt + ". Character limit : " + charLimit;
    }

    public void setCharLimit(int cLim) {
        this.charLimit = cLim;
    }
}
