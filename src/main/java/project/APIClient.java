package project;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class APIClient {
    final CloseableHttpClient httpclient = HttpClients.createDefault();
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getEventsObject (UserData userData){
        long unixTime = userData.getCurrentDate().getTime() / 1000L;

        Object obj;
        String categories = "";

        try {
            obj = new JSONParser().parse((new FileReader("src/main/java/project/json/city.json")));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = (JSONObject) obj;
        JSONObject cityObject = (JSONObject) jsonObject.get("city");

        try {
            obj = new JSONParser().parse((new FileReader("src/main/java/project/json/categories.json")));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        JSONArray categoriesArray = (JSONArray) obj;
        Iterator categoriesItr = categoriesArray.iterator();
        while (categoriesItr.hasNext()) {
            JSONObject categoriesObject = (JSONObject) categoriesItr.next();
            if (categoriesObject.get("name").toString().toUpperCase().equals(userData.getCurrentCategories().toUpperCase())){
                categories = categoriesObject.get("slug").toString();
            }
        }

        final HttpUriRequest httpGet = new HttpGet("https://kudago.com/public-api/v1.4/events/?" +
                "lang=ru&" +
                "fields=next,id,dates,title,description,place,age_restriction,site_url,categories&" +
                "expand=&" +
                "order_by=&" +
                "text_format=text&" +
                "ids=&" +
                "location=" + cityObject.get(userData.getCurrentCity().toUpperCase()) + "&" +
                "actual_since=" + unixTime + "&" +
                "actual_until=" + (unixTime + 86400L) + "&" +
                "is_free=false&" +
                "categories=" + categories + "&" +
                "lon=&" +
                "lat=&" +
                "radius=");
        try (
                CloseableHttpResponse response1 = httpclient.execute(httpGet)
        ){
            final HttpEntity entity1 = response1.getEntity();
            return EntityUtils.toString(entity1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

