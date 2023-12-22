package Day5;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JSONSchemaValidation {

    //Json to jsonSchema Convertor ----> https://www.liquid-technologies.com/online-json-to-schema-converter

    @Test
    void jsonSchemaValidation(){

        given()

                .when()
                .get("http://localhost:3000/store")


                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));

    }
}
