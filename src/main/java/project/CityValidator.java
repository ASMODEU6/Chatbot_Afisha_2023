package project;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class CityValidator {

    public static boolean checkCity(UserData userData, String cityName){
        cityName = cityName.toUpperCase();

        Object obj;
        try {
            obj = new JSONParser().parse((new FileReader("src/main/java/project/json/city.json")));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = (JSONObject) obj;
        JSONObject cityObject = (JSONObject) jsonObject.get("city");

        if (cityObject.containsKey(cityName.toUpperCase())){
            if ((boolean) cityObject.get(cityName.toUpperCase())){
                return true;
            }
            userData.setCurrentException("cityIsNotSupported");
            return false;
        }
        userData.setCurrentException("cityIsNotInTheList");
        return false;
    }
}
