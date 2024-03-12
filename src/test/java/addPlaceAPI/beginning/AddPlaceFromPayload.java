package addPlaceAPI.beginning;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceFromPayload {
    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        /*You can enclose long string expressions in a "text block" created using three double-quote marks (""")
        This enhances readability and makes the code cleaner.
         */
        String response = given().queryParam("key", "qaclick123")
                .body(Payload.addPlace("new york, first avenue"))
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .extract().response().asString();

        JSONObject json = new JSONObject(response);
        System.out.println(json.toString(4)); // This will print the formatted response
        //System.out.println("all response as json = " + response);

        JsonPath js = new JsonPath(response);
        Object id = js.get("place_id");
        System.out.println("id = " + id);
    }

}
