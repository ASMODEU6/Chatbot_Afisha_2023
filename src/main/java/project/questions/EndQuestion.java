package project.questions;

import project.UserData;

public class EndQuestion extends AbstractQuestion {
    public EndQuestion() {
        super("Хотите изменить данные? (да/нет)");
    }

    public boolean checkAnswer(String msg, UserData userData) {
        if (msg.equalsIgnoreCase("ДА")) {
            userData.setCurrentQuestion(0);
            userData.setCurrentCity(null);
            userData.setCurrentDate(null);
            return false;
        } else if (msg.equalsIgnoreCase("НЕТ")){
            return true;
        }
        userData.setCurrentException("incorrectResponse");
        return false;
    }
}
