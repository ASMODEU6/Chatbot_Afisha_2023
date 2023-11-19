package project;

import java.util.Date;

public class UserData {
    private int currentQuestion = 0;
    private int currentPage = 0;
    private String currentCity = null;
    private Date currentDate = null;
    private String currentCategories = null;

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

    public String getCurrentCategories() {
        return currentCategories;
    }

    public void setCurrentCategories(String currentCategories) {
        this.currentCategories = currentCategories;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
