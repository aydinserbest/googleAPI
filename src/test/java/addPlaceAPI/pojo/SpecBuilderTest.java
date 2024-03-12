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

        /*
        By using RequestSpecification and ResponseSpecification objects,
        you effectively create reusable templates for your requests and responses.
        This means that you define common properties for your HTTP requests and expected responses just once
        and can then apply these to multiple tests. It streamlines your test code and ensures consistency across different test cases.
        It's a particularly useful approach when you have many tests that share similar setup or validation requirements.
         */

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .build();

        ResponseSpecification responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
    //These objects can then be reused across your tests. This approach reduces code duplication

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
/*
    Second Approach (With Builder Pattern):
    The second approach offers a more customizable and reusable structure for your tests.
    By using the RequestSpecBuilder and ResponseSpecBuilder classes,
    you can predefine common settings across tests (such as URL, Content Type, query parameters, etc.)
    and save them as RequestSpecification and ResponseSpecification objects.
    These objects can then be reused across your tests. This approach reduces code duplication
    and simplifies the management of your tests.

    The builder pattern in the second approach makes your code more modular and manageable.
    Especially for large and complex test suites, centralizing common configurations in one place is
    more efficient than rewriting the same setup within each test method.

    Additionally, the builder pattern allows for centralizing configurations at the test class level or
    even within a global test configuration file, meaning a single change can affect all your tests.
    It also ensures consistency across tests and reduces potential errors, as it eliminates inconsistencies
    that could arise from setting up configurations separately in each test method.

    In conclusion, the second approach is the preferred method for larger-scale projects
    or situations where common configurations are required.
 */
