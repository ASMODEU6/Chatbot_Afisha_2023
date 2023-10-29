package project.questions;

import project.UserData;
public class RestartQuestion extends AbstractQuestion {
    public RestartQuestion() {
        super("Если захотите еще что-нибуть посмотреть, напишите мне \"старт\".");
    }

    @Override
    public boolean checkAnswer(String msg, UserData userData){
        if (msg.equalsIgnoreCase("СТАРТ")){
            userData.setCurrentQuestion(0);
            userData.setCurrentCity(null);
            userData.setCurrentDate(null);
        }
        return false;
    }
}
