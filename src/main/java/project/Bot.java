package project;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Objects;

public class Bot extends TelegramLongPollingBot {
    private final HashMap<Long, UserData> users;
    public Bot(){
        users = new HashMap<>();
    }
    public String getBotUsername() {
        return "afisha_kb_bot";
    }

    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Long userId = message.getFrom().getId();
        String messageText = message.getText();

        if (!users.containsKey(userId)) users.put(userId, new UserData());

        DialogueManager manager = new DialogueManager(users.get(userId));
        ConsoleCommand console = new ConsoleCommand();

        if (!Objects.equals(console.findCommand(users.get(userId), messageText), messageText)) {
            sendText(userId, console.findCommand(users.get(userId), messageText));
            sendText(userId, manager.askQuestion(users.get(userId), null));
        } else {
            sendText(userId, manager.askQuestion(users.get(userId), messageText));
        }

    }

}
