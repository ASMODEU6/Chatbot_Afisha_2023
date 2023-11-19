package project.questions;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateQuestion extends AbstractQuestion {

    public DateQuestion() {
        super("Укажите дату в формате ДД-ММ-ГГГГ: ");
    }

    public boolean checkAnswer(String msg /*, UserData userData*/) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            formatter.parse(msg);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
