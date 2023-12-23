package StudentAPI_Chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class CreateUser {

    @Test
    void test_createUser(ITestContext context)

    {

        Faker faker = new Faker();

        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("location", "Delhi");
        data.put("phone", faker.phoneNumber().phoneNumber());

        String coursesArr[] ={"Java", "Azure"};
        data.put("courses",coursesArr);


        int id = given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")
                .jsonPath().getInt("id");

        System.out.println("Generated ID is: ----> " + id);

        context.getSuite().setAttribute("user_id", id);



    }


}
