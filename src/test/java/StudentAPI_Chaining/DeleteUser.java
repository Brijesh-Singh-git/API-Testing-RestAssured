package StudentAPI_Chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    @Test
    void test_deleteUser(ITestContext context){


        int id = (int) context.getSuite().getAttribute("user_id");

        given()
                .pathParams("id", id)


                .when()
                .delete("http://localhost:3000/students/{id}")


                .then()
                .statusCode(200)
                .log().all();


    }
}
