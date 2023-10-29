package project;

import java.util.Date;

public class UserData {
    private int currentQuestion = 0;
    private String currentCity = null;
    private Date currentDate = null;
    private String currentException = null;

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentException() {
        return currentException;
    }

    public void setCurrentException(String currentException) {
        this.currentException = currentException;
    }
}
