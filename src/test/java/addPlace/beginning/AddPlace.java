package addPlace.beginning;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlace {
    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().queryParam("key", "qaclick123")
                .body("""
                        {
                          "location": {
                            "lat": -38.383494,
                            "lng": 33.427362
                          },
                          "accuracy": 50,
                          "name": "Frontline house",
                          "phone_number": "(+91) 983 893 3937",
                          "address": "29, side layout, cohen 09",
                          "types": [
                            "shoe park",
                            "shop"
                          ],
                          "website": "http://google.com",
                          "language": "French-IN"
                        }
                                                
                        """)
                .when().post("/maps/api/place/add/json")
                .then().statusCode(200).body("scope", equalTo("APP"))
                .extract().response().asString();
        JSONObject json = new JSONObject(response);
        System.out.println(json.toString(4)); // This will print the formatted response
        //System.out.println("all response as json = " + response);

        JsonPath js = new JsonPath(response);
        Object id = js.get("place_id");
        System.out.println("id = " + id);
    }

}
