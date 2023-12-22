package project;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DialogueManagerTest {

    @Mock
    UserData mUserData = org.mockito.Mockito.mock(UserData.class);

    @Test
    void getAPIClientResultsList() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        when(mUserData.getCurrentDate()).thenReturn(formatter.parse("12-12-2023"));
        when(mUserData.getCurrentCity()).thenReturn("Москва");
        when(mUserData.getCurrentPage()).thenReturn(1);
        when(mUserData.getCurrentCategories()).thenReturn("Детям");

        assertNotEquals(DialogueManager.getAPIClientResultsList(mUserData), "");
    }

    @Test
    void getAPIClientResult() {
        assertNotEquals(DialogueManager.getAPIClientResult(mUserData, 60845), "");
    }
}