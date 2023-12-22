package Day5;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class XMLSchemaValidation {

    @Test
    void xmlSchemaValidation(){

        given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")

                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchemaData.xsd"));



    }
}
