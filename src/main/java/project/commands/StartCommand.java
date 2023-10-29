package project.commands;


import project.UserData;

public class StartCommand extends AbstractCommand{
    public void doCommand(UserData userData) {
        userData.setCurrentQuestion(0);
        userData.setCurrentDate(null);
        userData.setCurrentCity(null);
    }
    public String getDescription() {
        return "перезапуск бота";
    }
    public String getMessage(){
        return """
                Привет! Я - бот, который помогает узнать о предстоящих мероприятиях в вашем городе. Просто введите дату и город, и я предоставлю вам список самых интересных событий. Следите за обновлениями!

                (введите /help для списка команд)""";
    }
}
