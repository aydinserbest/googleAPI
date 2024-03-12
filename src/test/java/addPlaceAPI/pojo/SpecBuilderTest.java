package addPlaceAPI.pojo;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
    @Test
    public void addPlace(){

        Place place = getPlace();
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .build();
        ResponseSpecification responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


        RequestSpecification request = given().spec(requestSpecification)
                .body(place);


        Response response = request.when().post("/maps/api/place/add/json")
                .then().spec(responseSpecBuilder).extract().response();
        String responseBody = response.getBody().asString();

        JSONObject json = new JSONObject(responseBody);
        System.out.println(json.toString(4));
    }

    private static Place getPlace() {
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
        return place;
    }
}
