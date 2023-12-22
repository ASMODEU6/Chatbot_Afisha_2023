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
class APIClientTest {
    @Mock
    UserData mUserData = org.mockito.Mockito.mock(UserData.class);
    @InjectMocks
    APIClient apiClient = new APIClient(mUserData);
    @Test
    void getEventObject() {
        assertEquals(apiClient.getEventObject(60845), "{\"title\":\"вечеринка Steptusin vs. Ya Booty Beats\",\"description\":\"Мастодонты питерского грува и басса схлестнутся в дружеском баттле, чтобы поделиться с поклонниками новой музыкой, а заодно и отпраздновать трёхлетие формации Ya Booty Beats.\",\"age_restriction\":\"18+\",\"site_url\":\"https://kudago.com/spb/event/vecherinka-steptusin-vs-ya-booty-beats/\"}");
        assertEquals(apiClient.getEventObject(100000), "{\"detail\":\"Не найдено.\"}");
    }

    @Test
    public void getEventsListObject() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        when(mUserData.getCurrentDate()).thenReturn(formatter.parse("12-12-2023"));
        when(mUserData.getCurrentCity()).thenReturn("Москва");
        when(mUserData.getCurrentPage()).thenReturn(1);
        when(mUserData.getCurrentCategories()).thenReturn("Детям");

        assertEquals(apiClient.getEventsListObject(), "{\"count\":20,\"next\":\"https://kudago.com/public-api/v1.4/events/?actual_since=1702321200&actual_until=1702407600&categories=kids&expand=&fields=id%2Ctitle&ids=&is_free=false&lang=ru&lat=&location=msk&lon=&order_by=&page=2&page_size=8&radius=&text_format=text\",\"previous\":null,\"results\":[{\"id\":51836,\"title\":\"квест «Красная кнопка»\"},{\"id\":188274,\"title\":\"развлечения в Smile Park на ВДНХ\"},{\"id\":201160,\"title\":\"арт-вечеринка ArtSpace\"},{\"id\":203456,\"title\":\"создание мягкой игрушки с сердцем в Парке развлечений «Остров Мечты»\"},{\"id\":202997,\"title\":\"интерактивная выставка-игра «Путь воды» в Москвариуме\"},{\"id\":201840,\"title\":\"развлечения для детей и взрослых от Dream Game Center в «Острове Мечты»\"},{\"id\":207826,\"title\":\"групповые экскурсии на смотровой площадке  PANORAMA360\"},{\"id\":195448,\"title\":\"выставка «Сокровища гробницы Тутанхамона»\"}]}");

        when(mUserData.getCurrentDate()).thenReturn(formatter.parse("12-12-1983"));

        assertEquals(apiClient.getEventsListObject(), "{\"count\":0,\"next\":null,\"previous\":null,\"results\":[]}");
    }
}