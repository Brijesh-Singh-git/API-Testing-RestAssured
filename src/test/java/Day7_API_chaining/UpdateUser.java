package Day7_API_chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class UpdateUser {

    @Test
    void test_updateUser(ITestContext context){

        Faker faker = new Faker();

        JSONObject data = new JSONObject();  //creating a jsonobject to update a data

        data.put("name", faker.name().fullName());
        data.put("gender", "Female");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "active");

        String bearerToken = "fddf4c306a1a13fdd054d7a334421903a58d5e14de052801eb3f0bd4e23f7f26";

        //int id = (int) context.getAttribute("user_id");  // this id should come from the createUser.java

        int id = (int) context.getSuite().getAttribute("user_id");


        given()
                .header("Authorization", "Bearer "+bearerToken)
                .pathParams("id", id)
                .contentType("application/json")
                .body(data.toString())

                .when()
                    .put("https://gorest.co.in/public/v2/users/{id}")

                .then()
                    .statusCode(200)
                    .log().all();



    }


}
