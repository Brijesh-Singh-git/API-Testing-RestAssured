package StudentAPI_Chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Test
    void test_updateUser(ITestContext context){

        Faker faker = new Faker();

        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("location", "Ireland");
        data.put("phone", faker.phoneNumber().phoneNumber());

        String coursesArr[] ={"DevOps", "Cybersecurity"};
        data.put("courses",coursesArr);


        int id = (int) context.getSuite().getAttribute("user_id");

        given()
                .contentType("application/json")
                .body(data.toString())
                .pathParams("id", id)

                .when()
                .put("http://localhost:3000/students/{id}")

                .then()
                .statusCode(200)
                .log().all();


    }
}
