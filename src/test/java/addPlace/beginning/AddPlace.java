package addPlace.beginning;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlace {
    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        /*You can enclose long string expressions in a "text block" created using three double-quote marks (""")
        This enhances readability and makes the code cleaner.
         */
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
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .extract().response().asString();
        /*
        Both .then().statusCode(200) and .then().assertThat().statusCode(200) are used
        to validate the status code from a REST response in Rest Assured.
        They are doing essentially the same job of checking that the returned status code equals to 200.
        The difference is that .assertThat() provides better readability and is more flexible
        since it comes from AssertJ and supports a wide variety of assertions, not just for status code.
        Using .assertThat() can make your test cases more expressive and easier to understand,
        especially when you chain multiple assertions together.
        However, using .statusCode(200) is perfectly fine if you just want to check the status code.
        It's not a mandatory to use .assertThat(), it's just a preference based on your testing needs
        and how expressive and easily readable you want your test cases to be.

        n the context of code, "readability" refers to how easily a programmer can understand what the code is doing just by looking at it.
        Readable code tends to have clear logic and avoids overly complex structures and syntax.
        When I say .assertThat() improves readability, I mean that it makes the code more expressive and easier to understand.
        It clearly states that you are making an assertion about the state of the system.
        For example, .then().assertThat().statusCode(200) can be read almost like English: "Then, assert that the status code is 200."
        This improves the readability of your test cases, making them easier to understand and maintain.
         */

        /*
        f you want to format your JSON response in a pretty way,
        you can use the org.json library to format your JSON response.
        This code first writes the response in a proper format.
        In order to use the JSONObject class, you need to add the dependency of the org.json library to your project.
        The toString() method has a specific version for the JSONObject class,
        allowing you to return your JSON data as a string in a reader-friendly, or "pretty-printed," format.
        The optional parameter (4) you pass signifies indentation.
        This parameter determines how much indentation will be added before each JSON item.
        This makes it easier to read complex and long JSON data.
         */
        JSONObject json = new JSONObject(response);
        System.out.println(json.toString(4)); // This will print the formatted response
        //System.out.println("all response as json = " + response);

        JsonPath js = new JsonPath(response);
        Object id = js.get("place_id");
        System.out.println("id = " + id);
    }

}
