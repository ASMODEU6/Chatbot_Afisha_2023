package project;

import org.apache.commons.text.WordUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.questions.*;

import java.util.Iterator;

public class DialogueManager {
    private final AbstractQuestion[] questions;

    public DialogueManager(){
        questions = new AbstractQuestion[5];
        questions[0] = new CityQuestion();
        questions[1] = new DateQuestion();
        questions[2] = new CategoriesQuestion();
        questions[3] = new EndQuestion();
        questions[4] = new RestartQuestion();
    }
    public String askQuestion(UserData userData, String msg){
        String result = "";

        if (msg != null && questions[userData.getCurrentQuestion()].checkAnswer(msg, userData)) {
            userData.setCurrentQuestion(userData.getCurrentQuestion() + 1);
        } else if (userData.getCurrentException() != null){
            switch (userData.getCurrentException()) {
                case "invalidDateFormat" -> result += "Некорректная даты!\n\n";
                case "incorrectResponse" -> result += "Некорректный ответ!\n\n";
                case "cityIsNotInTheList" -> result += "Сервис в указанном вами городе не доступен или название города указанно неверно!\n\n";
                case "categoriesIsNotInTheList" -> result += "\n\n";
                default -> throw new IllegalStateException("Unexpected value: " + userData.getCurrentException());
            }
            userData.setCurrentException(null);
        }

        if (userData.getCurrentQuestion() == 4 && userData.getCurrentDate() != null && userData.getCurrentCity() != null && userData.getCurrentCategories() != null){
            APIClient apiClient = new APIClient();

            Object obj;
            try {
                obj = new JSONParser().parse(apiClient.getEventsObject(userData));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray resultsArray = (JSONArray) jsonObject.get("results");

            result += "Найдено " + jsonObject.get("count") + " событий:\n\n";
            Iterator resultsItr = resultsArray.iterator();

            for (int i = 1; resultsItr.hasNext(); i++) {
                JSONObject resultObject = (JSONObject) resultsItr.next();
                result += i + ". \"" + WordUtils.capitalizeFully(resultObject.get("title").toString()) + "\"\n" +
                        "   Возрастное ограничение: " + resultObject.get("age_restriction") + "\n" +
                        "   Cсылка: " + resultObject.get("site_url") + "\n" ;
            }
        }

        result += questions[userData.getCurrentQuestion()].getQuestion();

        return result;
    }

}
