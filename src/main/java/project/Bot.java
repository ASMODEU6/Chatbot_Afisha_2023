package project;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Math.min;

public class Bot extends TelegramLongPollingBot {
    private final HashMap<Long, UserData> users;
    private ReplyKeyboardMarkup[] keyboardArray;

    private InlineKeyboardMarkup keyboardM1;
    private InlineKeyboardMarkup keyboardM2;
    private InlineKeyboardMarkup keyboardM3;
    private InlineKeyboardMarkup keyboardM4;

    public Bot(){
//        setInlineKeyboard();
        setReplayKeyboardArray();
        users = new HashMap<>();
    }
    public void setReplayKeyboardArray(){
        keyboardArray = new ReplyKeyboardMarkup[4];

        for (int i = 0; i < 4; i++){
            keyboardArray[i] = new ReplyKeyboardMarkup();
            keyboardArray[i].setResizeKeyboard(true);
            keyboardArray[i].setOneTimeKeyboard(true);
        }

        KeyboardRow keyboardDateRow = new KeyboardRow();
        KeyboardRow keyboardCityRowOne = new KeyboardRow();
        KeyboardRow keyboardCityRowTwo = new KeyboardRow();
        KeyboardRow keyboardCityRowThree = new KeyboardRow();
        KeyboardRow keyboardCategoriesRowOne = new KeyboardRow();
        KeyboardRow keyboardCategoriesRowTwo = new KeyboardRow();
        KeyboardRow keyboardCategoriesRowThree = new KeyboardRow();
        KeyboardRow keyboardCategoriesRowFour = new KeyboardRow();
        KeyboardRow keyboardCategoriesRowFive = new KeyboardRow();
        KeyboardRow keyboardCategoriesRowSix = new KeyboardRow();
        KeyboardRow keyboardCategoriesRowSeven = new KeyboardRow();
        KeyboardRow keyboardCategoriesRowEight  = new KeyboardRow();
        KeyboardRow keyboardCategoriesRowNine = new KeyboardRow();
        KeyboardRow keyboardCategoriesRowTen = new KeyboardRow();
        KeyboardRow keyboardRestartRowTwo = new KeyboardRow();

        ArrayList<KeyboardRow> keyboardCity = new ArrayList<>();
        ArrayList<KeyboardRow> keyboardDate = new ArrayList<>();
        ArrayList<KeyboardRow> keyboardCategories = new ArrayList<>();
        ArrayList<KeyboardRow> keyboardRestart = new ArrayList<>();

        keyboardCity.add(keyboardCityRowOne);
        keyboardCity.add(keyboardCityRowTwo);
        keyboardCity.add(keyboardCityRowThree);

        keyboardCityRowOne.add(new KeyboardButton("Екатеринбург"));
        keyboardCityRowTwo.add(new KeyboardButton("Москва"));
        keyboardCityRowThree.add(new KeyboardButton("Санкт-Петербург"));

        keyboardArray[0].setKeyboard(keyboardCity);

        keyboardDate.add(keyboardDateRow);

        Date now = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd-MM-yyyy");

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, 1);

        keyboardDateRow.add(new KeyboardButton(formatForDateNow.format(now)));
        keyboardDateRow.add(new KeyboardButton(formatForDateNow.format(cal.getTime())));

        keyboardArray[1].setKeyboard(keyboardDate);

        keyboardCategories.add(keyboardCategoriesRowOne);
        keyboardCategories.add(keyboardCategoriesRowTwo);
        keyboardCategories.add(keyboardCategoriesRowThree);
        keyboardCategories.add(keyboardCategoriesRowFour);
        keyboardCategories.add(keyboardCategoriesRowFive);
        keyboardCategories.add(keyboardCategoriesRowSix);
        keyboardCategories.add(keyboardCategoriesRowSeven);
        keyboardCategories.add(keyboardCategoriesRowEight);
        keyboardCategories.add(keyboardCategoriesRowNine);
        keyboardCategories.add(keyboardCategoriesRowTen);

        keyboardCategoriesRowOne.add(new KeyboardButton("Кинопоказы"));
        keyboardCategoriesRowTwo.add(new KeyboardButton("Концерты"));
        keyboardCategoriesRowThree.add(new KeyboardButton("Развлечения"));
        keyboardCategoriesRowFour.add(new KeyboardButton("Выставки"));
        keyboardCategoriesRowFive.add(new KeyboardButton("Фестивали"));
        keyboardCategoriesRowSix.add(new KeyboardButton("Детям"));
        keyboardCategoriesRowSeven.add(new KeyboardButton("Вечеринки"));
        keyboardCategoriesRowEight.add(new KeyboardButton("Квесты"));
        keyboardCategoriesRowNine.add(new KeyboardButton("Спектакли"));
        keyboardCategoriesRowTen.add(new KeyboardButton("Экскурсии"));

        keyboardArray[2].setKeyboard(keyboardCategories);

        keyboardRestart.add(keyboardRestartRowTwo);

        keyboardRestartRowTwo.add(new KeyboardButton("Старт"));

        keyboardArray[3].setKeyboard(keyboardRestart);
    }
    public void setInlineKeyboard(UserData userData){
        var back = InlineKeyboardButton.builder()
                .text("Вернуться к списку мероприятий").callbackData("back")
                .build();

        var next = InlineKeyboardButton.builder()
                .text("Следующая ст.").callbackData("next")
                .build();

        var last = InlineKeyboardButton.builder()
                .text("Предыдущая ст.").callbackData("last")
                .build();

        ArrayList<InlineKeyboardButton> keyboardRowListOne = new ArrayList<>();

        for (int i = (userData.getCurrentPage() - 1) * 8 + 1; i <= min(userData.getCurrentPage() * 8, userData.getCountResults()); i++){
            keyboardRowListOne.add(InlineKeyboardButton.builder()
                    .text(i+"").callbackData(userData.getResultsArray(i))
                    .build());
        }

        ArrayList<InlineKeyboardButton> keyboardRowListTwo = new ArrayList<>();

        keyboardRowListTwo.add(last);
        keyboardRowListTwo.add(next);

        keyboardM1 = InlineKeyboardMarkup.builder()
                .keyboardRow(keyboardRowListOne)
                .keyboardRow(List.of(next))
                .build();

        keyboardM2 = InlineKeyboardMarkup.builder()
                .keyboardRow(keyboardRowListOne)
                .keyboardRow(keyboardRowListTwo)
                .build();

        keyboardM3 = InlineKeyboardMarkup.builder()
                .keyboardRow(keyboardRowListOne)
                .keyboardRow(List.of(last))
                .build();

        keyboardM4 = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(back))
                .build();
    }
    public String getBotUsername() {
        return "afisha_kb_bot";
    }

    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

    public void sendText(Long who, String what, ReplyKeyboardMarkup rkm){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();

        sm.setReplyMarkup(rkm);

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendText(Long who, String txt, InlineKeyboardMarkup kb){
        SendMessage sm = SendMessage.builder().chatId(who.toString())
                .parseMode("HTML").text(txt)
                .replyMarkup(kb).build();

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void buttonTap(Long id, String queryId, String data, int msgId) throws TelegramApiException {

        EditMessageText newTxt = EditMessageText.builder()
                .chatId(id.toString())
                .messageId(msgId).text("").build();

        EditMessageReplyMarkup newKb = EditMessageReplyMarkup.builder()
                .chatId(id.toString()).messageId(msgId).build();

        if(data.equals("next")) {
            users.get(id).setCurrentPage(users.get(id).getCurrentPage() + 1);
            newTxt.setText(DialogueManager.getAPIClientResultsList(users.get(id)));
        } else if(data.equals("last")) {
            users.get(id).setCurrentPage(users.get(id).getCurrentPage() - 1);
            newTxt.setText(DialogueManager.getAPIClientResultsList(users.get(id)));
        } else if (data.equals("back")) {
            newTxt.setText(DialogueManager.getAPIClientResultsList(users.get(id)));
        } else {
            newTxt.setText(DialogueManager.getAPIClientResult(users.get(id), Integer.parseInt(data)));
        }

        setInlineKeyboard(users.get(id));
        if (data.equals("next") || data.equals("last") || data.equals("back")){
            if (users.get(id).getCurrentPage() == 1) newKb.setReplyMarkup(keyboardM1);
            else if (users.get(id).getCurrentPage() == users.get(id).getMaxPage()) newKb.setReplyMarkup(keyboardM3);
            else newKb.setReplyMarkup(keyboardM2);
        } else {
            newKb.setReplyMarkup(keyboardM4);
        }

        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(queryId).build();

        execute(close);
        execute(newTxt);
        execute(newKb);
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long userId = message.getFrom().getId();
            Long chatId = update.getMessage().getChatId();
            String messageText = message.getText();

            if (!users.containsKey(chatId)) users.put(chatId, new UserData());

            DialogueManager manager = new DialogueManager(users.get(chatId));
            ConsoleCommand console = new ConsoleCommand();

            if (!Objects.equals(console.findCommand(users.get(chatId), messageText), messageText)) {
                sendText(userId, console.findCommand(users.get(chatId), messageText), keyboardArray[users.get(chatId).getCurrentQuestion()]);
                sendText(userId, manager.askQuestion(users.get(chatId), null), keyboardArray[users.get(chatId).getCurrentQuestion()]);
            } else if (users.get(chatId).getCurrentQuestion() == 2) {
                manager.getAPIClientResultsList(users.get(chatId));
                setInlineKeyboard(users.get(chatId));
                if (users.get(chatId).getMaxPage() > 1) sendText(userId, manager.askQuestion(users.get(chatId), messageText), keyboardM1);
                else sendText(userId, manager.askQuestion(users.get(chatId), messageText), keyboardArray[users.get(chatId).getCurrentQuestion()]);
                sendText(userId, "Напишите мне \"старт\" для нового поиска.", keyboardArray[users.get(chatId).getCurrentQuestion()]);
            } else {
                sendText(userId, manager.askQuestion(users.get(chatId), messageText), keyboardArray[users.get(chatId).getCurrentQuestion()]);
            }
        } else if (update.hasCallbackQuery()) {
            Message message = update.getCallbackQuery().getMessage();

            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            String queryId = update.getCallbackQuery().getId();
            Integer msgId = message.getMessageId();
            String data = update.getCallbackQuery().getData();

            try {
                buttonTap(chatId, queryId, data, msgId);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
