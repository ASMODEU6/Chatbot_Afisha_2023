package project;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BotTest {

    @Mock
    UserData mUserData = org.mockito.Mockito.mock(UserData.class);
    Bot bot = new Bot();
    @Test
    void setInlineKeyboard() {

        when(mUserData.getCurrentPage()).thenReturn(2);
        when(mUserData.getCountResults()).thenReturn(54);
        when(mUserData.getResultsArray(9)).thenReturn("2312");
        when(mUserData.getResultsArray(10)).thenReturn("2312");
        when(mUserData.getResultsArray(11)).thenReturn("2312");
        when(mUserData.getResultsArray(12)).thenReturn("2312");
        when(mUserData.getResultsArray(13)).thenReturn("2312");
        when(mUserData.getResultsArray(14)).thenReturn("2312");
        when(mUserData.getResultsArray(15)).thenReturn("2312");
        when(mUserData.getResultsArray(16)).thenReturn("2312");
        when(mUserData.getMaxPage()).thenReturn(7);

        bot.setInlineKeyboard(mUserData);

        assertEquals(bot.keyboardM2.getKeyboard().get(0).size(), 8);

        when(mUserData.getCurrentPage()).thenReturn(1);
        when(mUserData.getCountResults()).thenReturn(6);
        when(mUserData.getResultsArray(1)).thenReturn("2312");
        when(mUserData.getResultsArray(2)).thenReturn("2312");
        when(mUserData.getResultsArray(3)).thenReturn("2312");
        when(mUserData.getResultsArray(4)).thenReturn("2312");
        when(mUserData.getResultsArray(5)).thenReturn("2312");
        when(mUserData.getResultsArray(6)).thenReturn("2312");
        when(mUserData.getMaxPage()).thenReturn(1);

        bot.setInlineKeyboard(mUserData);

        assertEquals(bot.keyboardM1.getKeyboard().size(), 1);
        assertEquals(bot.keyboardM1.getKeyboard().get(0).size(), 6);

        when(mUserData.getCurrentPage()).thenReturn(1);
        when(mUserData.getCountResults()).thenReturn(0);
        when(mUserData.getMaxPage()).thenReturn(1);

        bot.setInlineKeyboard(mUserData);

        assertEquals(bot.keyboardM1.getKeyboard().size(), 1);
        assertEquals(bot.keyboardM1.getKeyboard().get(0).size(), 0);

        when(mUserData.getCurrentPage()).thenReturn(1);
        when(mUserData.getCountResults()).thenReturn(12);
        when(mUserData.getResultsArray(1)).thenReturn("2312");
        when(mUserData.getResultsArray(2)).thenReturn("2312");
        when(mUserData.getResultsArray(3)).thenReturn("2312");
        when(mUserData.getResultsArray(4)).thenReturn("2312");
        when(mUserData.getResultsArray(5)).thenReturn("2312");
        when(mUserData.getResultsArray(6)).thenReturn("2312");
        when(mUserData.getResultsArray(7)).thenReturn("2312");
        when(mUserData.getResultsArray(8)).thenReturn("2312");
        when(mUserData.getMaxPage()).thenReturn(2);

        bot.setInlineKeyboard(mUserData);

        assertEquals(bot.keyboardM1.getKeyboard().size(), 2);
        assertEquals(bot.keyboardM1.getKeyboard().get(0).size(), 8);

        when(mUserData.getCurrentPage()).thenReturn(2);
        when(mUserData.getCountResults()).thenReturn(14);
        when(mUserData.getResultsArray(9)).thenReturn("2312");
        when(mUserData.getResultsArray(10)).thenReturn("2312");
        when(mUserData.getResultsArray(11)).thenReturn("2312");
        when(mUserData.getResultsArray(12)).thenReturn("2312");
        when(mUserData.getResultsArray(13)).thenReturn("2312");
        when(mUserData.getResultsArray(14)).thenReturn("2312");
        when(mUserData.getMaxPage()).thenReturn(2);

        bot.setInlineKeyboard(mUserData);

        assertEquals(bot.keyboardM3.getKeyboard().get(0).size(), 6);
    }
}