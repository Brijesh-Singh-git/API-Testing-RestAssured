package Day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingDemo {

    @Test
    void testLogs(){

        given()

                .when()
                .get("https://reqres.in/api/users?page=2")


                .then()
                //.log().body(); //It will print only the response body
                //.log().cookies();
                .log().headers();
                //.log().all();





    }
}
