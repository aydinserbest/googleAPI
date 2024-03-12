package addPlaceAPI.pojo;

import io.restassured.RestAssured;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddPlaceTest {
    public static void main(String[] args) {

        Place place = new Place();

        place.setAccuracy(50);
        place.setAddress("29, side layout, cohen 09");
        place.setLanguage("French-IN");
        place.setName("Frontline house");
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("http://google.com");

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        place.setTypes(types);


        Location location = new Location();
        location.setLng(33.427362);
        location.setLat(-38.383494);
        place.setLocation(location);

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123")
                .body(place)
                .when().post("/maps/api/place/add/json")
                .then().log().all().statusCode(200)
                .extract().response().asString();

        JSONObject json = new JSONObject(response);
        System.out.println(json.toString(4));

//        String responseString = response.asString();
//        System.out.println(responseString);


//        JsonPath js = new JsonPath(response);
//        String id = js.getString("place_id");
//        System.out.println("id = " + id);
    }

}
