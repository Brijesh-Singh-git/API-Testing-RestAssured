package Day7_API_chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;



public class GetUser {

    @Test
    void test_getUser(ITestContext context){

        //int id = (int) context.getAttribute("user_id");  // this id should come from the createUser.java
        int id = (int) context.getSuite().getAttribute("user_id");


        String bearerToken = "fddf4c306a1a13fdd054d7a334421903a58d5e14de052801eb3f0bd4e23f7f26";

            given()
                .header("Authorization", "Bearer "+bearerToken)
                .pathParams("id", id)

                .when()
                    .get("https://gorest.co.in/public/v2/users/{id}")

                .then()
                    .statusCode(200)
                    .log().all();

    }


}
