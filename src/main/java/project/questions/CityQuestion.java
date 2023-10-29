package project.questions;

import project.CityValidator;
import project.UserData;

public class CityQuestion extends AbstractQuestion {
    public CityQuestion() {
        super("Укажите город: ");
    }

    public boolean checkAnswer(String msg, UserData userData){
        if (userData.getCurrentCity() == null) {
            if (CityValidator.checkCity(userData, msg)) {
                userData.setCurrentCity(msg);
                return true;
            }
        }
        return false;
    }

}
