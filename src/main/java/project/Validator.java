package project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Validator {

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
            return true;
        }
        userData.setCurrentException("cityIsNotInTheList");
        return false;
    }

    public static boolean checkCategories(UserData userData, String categoriesName){
        categoriesName = categoriesName.toUpperCase();

        Object obj;
        try {
            obj = new JSONParser().parse((new FileReader("src/main/java/project/json/categories.json")));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        JSONArray categoriesArray = (JSONArray) obj;

        Iterator categoriesItr = categoriesArray.iterator();
        while (categoriesItr.hasNext()) {
            JSONObject categoriesObject = (JSONObject) categoriesItr.next();
            if (categoriesObject.get("name").toString().toUpperCase().equals(categoriesName)){
                return true;
            }
        }
        userData.setCurrentException("categoriesIsNotInTheList");
        return false;
    }
}
