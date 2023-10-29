package project;

import org.apache.commons.text.WordUtils;
import project.questions.*;
public class DialogueManager {
    private final AbstractQuestion[] questions;
    public DialogueManager(){
        questions = new AbstractQuestion[4];
        questions[0] = new CityQuestion();
        questions[1] = new DateQuestion();
        questions[2] = new EndQuestion();
        questions[3] = new RestartQuestion();
    }
    public String askQuestion(UserData userData, String msg){
        String result = "";

        if (msg != null && questions[userData.getCurrentQuestion()].checkAnswer(msg, userData)) {
            userData.setCurrentQuestion(userData.getCurrentQuestion() + 1);
        } else if (userData.getCurrentException() != null){
            switch (userData.getCurrentException()) {
                case "invalidDateFormat" -> result += "Некорректная даты!\n\n";
                case "incorrectResponse" -> result += "Некорректный ответ!\n\n";
                case "cityIsNotSupported" -> result += "Я пока что не могу посмотреть мероприятия в городе " + WordUtils.capitalizeFully(msg) + ". Пожалуйста, укажите другой город!\n\n";
                case "cityIsNotInTheList" -> result += "Сервис в указанном вами городе не доступен или название города указанно неверно!\n\n";
                default -> throw new IllegalStateException("Unexpected value: " + userData.getCurrentException());
            }
            userData.setCurrentException(null);
        }

        if (userData.getCurrentQuestion() == 3 && userData.getCurrentDate() != null && userData.getCurrentCity() != null){
            result += "Здесь должен быть результат парсинга по запросу: " + userData.getCurrentCity() + "; "
                    + userData.getCurrentDate().toString() + ".\n\nСпасибо что пользуетесь нашим ботом!\n\n";
            userData.setCurrentCity(null);
            userData.setCurrentDate(null);
        }

        result += questions[userData.getCurrentQuestion()].getQuestion();

        return result;
    }

}
