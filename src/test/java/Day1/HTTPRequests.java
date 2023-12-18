package Day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


/* Structure of Rest Assured Test Cases
given()
    content type, set cookies, add auth, add param, set headers info etc....

when()
    get, post, put, delete

then()
    validate status code, extract response, extract headers cookies and response body .....

*/

public class HTTPRequests {

    int id;

    @Test (priority = 1)
    void getUsers(){

        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")

                .then()
                    .statusCode(200)
                    .body("page", equalTo(2))
                    .log().all();  //will display  the json response on console

    }

    @Test (priority = 2)
    void createUser(){

        HashMap data = new HashMap();  //store the data that we want to create
        data.put("name", "Monika Singh");
        data.put("job", "SQL Developer");

        id = given()
                .contentType("application/json")
                .body(data)

            .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");   //to capture the Id from the response

//            .then()
//                .statusCode(201)
//                .log().all();

    }

    @Test (priority = 3, dependsOnMethods = {"createUser"}) //it will only excecute if the create user is done successfully
    void updateUser(){

        HashMap data = new HashMap();  //store the data that we want to create
        data.put("name", "Mohan Pandit");
        data.put("job", "DevOps");

         given()
                .contentType("application/json")
                .body(data)

            .when()
                .put("https://reqres.in/api/users/" + id)


            .then()
                .statusCode(200)
                .log().all();
    }

    @Test (priority = 4)

    void deleteUser(){

        given()

                .when()
                .delete("https://reqres.in/api/users/" + id)

                .then()
                .statusCode(204)
                .log().all();
    }

}
