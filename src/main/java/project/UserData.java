package project;

import java.util.Date;
import java.util.HashMap;

public class UserData {
    private int currentQuestion = 0;
    private int currentPage = 1;
    private int maxPage = 1;
    private int countResults = 0;
    private HashMap<Integer, String> resultsArray = new HashMap<>();
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

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getCountResults() {
        return countResults;
    }

    public void setCountResults(int countResults) {
        this.countResults = countResults;
    }

    public String getResultsArray(Integer i) {
        return resultsArray.get(i);
    }

    public void setResultsArray(Integer i, String id) {
        this.resultsArray.put(i, id);
    }

    public void clearResultsArray() {
        this.resultsArray.clear();
    }
}
