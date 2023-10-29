package project.commands;

import project.UserData;

public class AboutCommand extends AbstractCommand{

    public void doCommand(UserData userData) {}

    public String getDescription() {
        return "информация о проекте";
    }

    public String getMessage(){
        return "Бот предназначен для сбора и отслеживания информации о текущих культурных мероприятиях в разных городах России. " +
                "Он предоставляет пользователям актуальную информацию о концертах, выставках, театральных постановках, фестивалях и " +
                "других культурных мероприятиях, которые проходят в реальном времени. " +
                "Бот также может помочь пользователям найти мероприятия, которые соответствуют их интересам и местоположению.";
    }
}
