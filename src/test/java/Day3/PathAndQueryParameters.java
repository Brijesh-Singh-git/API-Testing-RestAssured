package Day3;


import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class PathAndQueryParameters {


    //https://reqres.in/api/users?page=2&id=5

    @Test
    void testQueryAndPathParameters(){

        given()
                .pathParams("myPath", "users")  //Path parameters
                .queryParam("page", 2)    //Query parameters
                .queryParam("id", 5)     //Query parameters


                .when()
                .get("https://reqres.in/api/{myPath}")

                .then()
                .statusCode(200)
                .log().all();

    }
}
