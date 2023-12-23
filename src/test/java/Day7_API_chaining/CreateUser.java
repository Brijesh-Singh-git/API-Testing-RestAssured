package Day7_API_chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {

    @Test
     void test_createUser(ITestContext context) {

        Faker faker = new Faker();

        JSONObject data = new JSONObject();  //creating a jsonobject to create a data

        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        String bearerToken = "fddf4c306a1a13fdd054d7a334421903a58d5e14de052801eb3f0bd4e23f7f26";

        int id = given()
                .header("Authorization", "Bearer "+bearerToken)
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");    //We are not capturing the whole response instead we are just capturing the ID generated

        System.out.println("Generated Id is:---> " + id);

        //context.setAttribute("user_id", id);  //we are using ITestContext context to use the generated ID in our all test cases
        context.getSuite().setAttribute("user_id", id); // For suite level

    }


}
