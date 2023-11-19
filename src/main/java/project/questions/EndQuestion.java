package project.questions;

public class EndQuestion extends AbstractQuestion {
    public EndQuestion() {
        super("Хотите изменить данные? (да/нет)");
    }

    public boolean checkAnswer(String msg /*, UserData userData*/) {
        return !msg.equalsIgnoreCase("ДА");
    }
}
