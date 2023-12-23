package StudentAPI_Chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class GetUser {

    @Test
    void test_getUser(ITestContext context){

        int id = (int) context.getSuite().getAttribute("user_id");

        given()
                .pathParams("id", id)

                .when()
                .get("http://localhost:3000/students/{id}")


                .then()
                .statusCode(200)
                .log().all();




    }
}
