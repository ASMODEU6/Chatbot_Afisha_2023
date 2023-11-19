package project.questions;

import project.Validator;

public class CityQuestion extends AbstractQuestion {
    public CityQuestion() {
        super("Укажите город: ");
    }

    public boolean checkAnswer(String msg /*, UserData userData*/){
        return Validator.checkCity(/*userData,*/ msg);
    }

}
