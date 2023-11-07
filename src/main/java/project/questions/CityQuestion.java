package project.questions;

import project.Validator;
import project.UserData;

public class CityQuestion extends AbstractQuestion {
    public CityQuestion() {
        super("Укажите город: ");
    }

    public boolean checkAnswer(String msg, UserData userData){
        if (userData.getCurrentCity() == null) {
            if (Validator.checkCity(userData, msg)) {
                userData.setCurrentCity(msg);
                return true;
            }
        }
        return false;
    }

}
