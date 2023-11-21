package project;

import org.apache.commons.text.WordUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.questions.*;

import java.text.SimpleDateFormat;
import java.util.Iterator;

public class DialogueManager {
    private static AbstractQuestion[] questions;
    private final Object[] userDataArray;
    private final String[] exceptionArray;

    public DialogueManager(UserData userData){
        exceptionArray = new String[5];
        exceptionArray[0] = "Сервис в указанном вами городе не доступен или название города указанно неверно!\n\n";
        exceptionArray[1] = "Некорректная даты!\n\n";
        exceptionArray[2] = "Некорректная категория!\n\n";
        exceptionArray[3] = "";

        userDataArray = new Object[5];
        userDataArray[0] = userData.getCurrentCity();
        userDataArray[1] = userData.getCurrentDate();
        userDataArray[2] = userData.getCurrentCategories();
        userDataArray[3] = null;

        questions = new AbstractQuestion[5];
        questions[0] = new CityQuestion();
        questions[1] = new DateQuestion();
        questions[2] = new CategoriesQuestion();
        questions[3] = new RestartQuestion();
    }
    public String askQuestion(UserData userData, String msg){
        String result = "";

        if (msg != null && userDataArray[userData.getCurrentQuestion()] == null && questions[userData.getCurrentQuestion()].checkAnswer(msg)) {
            switch (userData.getCurrentQuestion()) {
                case 0 -> userData.setCurrentCity(msg);
                case 1 -> {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        userData.setCurrentDate(formatter.parse(msg));
                    } catch (java.text.ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> userData.setCurrentCategories(msg);
                case 3 -> {
                    userData.setCurrentQuestion(-1);
                    userData.setCurrentCity(null);
                    userData.setCurrentDate(null);
                    userData.setCurrentCategories(null);
                    userData.setCurrentPage(1);
                    userData.setMaxPage(1);
                    userData.clearResultsArray();
                }
            }
            userData.setCurrentQuestion(userData.getCurrentQuestion() + 1);
        } else if (msg != null){
            result += exceptionArray[userData.getCurrentQuestion()];
        }

        if (userData.getCurrentQuestion() == 3 && userData.getCurrentDate() != null && userData.getCurrentCity() != null && userData.getCurrentCategories() != null){
            result += getAPIClientResultsList(userData);
        } else {
            result += questions[userData.getCurrentQuestion()].getQuestion();
        }

        return result;
    }

    static String getAPIClientResultsList(UserData userData) {
        String result = "";

        APIClient apiClient = new APIClient(userData);

        Object obj;
        try {
            obj = new JSONParser().parse(apiClient.getEventsListObject());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = (JSONObject) obj;
        JSONArray resultsArray = (JSONArray) jsonObject.get("results");

        userData.setMaxPage(((int)(Integer.parseInt(jsonObject.get("count").toString())-0.1)/8)+1);
        userData.setCountResults(Integer.parseInt(jsonObject.get("count").toString()));

        result += "Найдено " + jsonObject.get("count") + " событий:\n\n";
        Iterator resultsItr = resultsArray.iterator();

        for (int i = 1; resultsItr.hasNext(); i++) {
            JSONObject resultObject = (JSONObject) resultsItr.next();
            userData.setResultsArray(i + (userData.getCurrentPage()-1)*8, resultObject.get("id").toString());
            result += (i + (userData.getCurrentPage()-1)*8) + ". \"" + WordUtils.capitalizeFully(resultObject.get("title").toString()) + "\"\n";
        }

        return result;
    }

    static String getAPIClientResult(UserData userData, Integer id){
        String result = "";

        APIClient apiClient = new APIClient(userData);

        Object obj;
        try {
            obj = new JSONParser().parse(apiClient.getEventObject(id));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = (JSONObject) obj;

        result += WordUtils.capitalizeFully(jsonObject.get("title").toString())+"\n\n" +
                jsonObject.get("description").toString() + "\n" +
                "Возрастное ограничение - " + jsonObject.get("age_restriction").toString() + "\n" +
                "Сайт: " + jsonObject.get("site_url");

        return result;
    }

}
