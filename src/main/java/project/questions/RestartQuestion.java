package project.questions;

public class RestartQuestion extends AbstractQuestion {
    public RestartQuestion() {
        super("\nНапишите мне \"старт\" для нового поиска.");
    }

    public boolean checkAnswer(String msg /*, UserData userData*/){
        return msg.equalsIgnoreCase("СТАРТ");
    }
}
