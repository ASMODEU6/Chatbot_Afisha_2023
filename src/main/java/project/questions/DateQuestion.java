package project.questions;

import project.UserData;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateQuestion extends AbstractQuestion {

    public DateQuestion() {
        super("Укажите дату в формате ДД-ММ-ГГГГ: ");
    }

    public boolean checkAnswer(String msg, UserData userData) {
        if (userData.getCurrentDate() == null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            try {
                userData.setCurrentDate(formatter.parse(msg));
            } catch (ParseException e) {
                userData.setCurrentException("invalidDateFormat");
                return false;
            }
        }
        return true;
    }
}
