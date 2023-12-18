package Day2_Ways_To_Create_POSTRequest_Body;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


/*
Different Ways to create POST Request Body
       1. Hashmap
       2. Using org.json library
       3. Using POJO(Plain Old Java Object)
       4. Using external JSON File
 */

public class DifferentWaysToCreatePostRequestBody {

    // 1. POST request body using HashMap

    @Test (priority = 1)
    void testPostUsingHashMap(){

        HashMap data = new HashMap();
        data.put("name", "Rohit Sharma");
        data.put("location", "Australia");
        data.put("phone", "8869614785");

        String courseArr[] = {"C", "Kotlin"};
        data.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Rohit Sharma"))
                .body("location", equalTo("Australia"))
                .body("phone", equalTo("8869614785"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("Kotlin"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();


    }

    // 2. POST request body using org.json library

    @Test (priority = 2)
    void testPostUsingOrgJSONLibrary(){

       //Create JSON Object
        JSONObject data = new JSONObject();
        data.put("name", "Rohit Sharma");
        data.put("location", "Australia");
        data.put("phone", "8869614785");

        String couresArr[] = {"Java", "Azure"};
        data.put("courses", couresArr);


        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Rohit Sharma"))
                .body("location", equalTo("Australia"))
                .body("phone", equalTo("8869614785"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Azure"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();


    }

    // 3. POST request body using POJO Class
        //Will be using the concept of Encapsualtion(getters and setters)

    @Test (priority = 3)
    void testPostUsingPOJO(){

        POJO_Post_Request data = new POJO_Post_Request();

        data.setName("Rohit Sharma");
        data.setLocation("Australia");
        data.setPhone("8869614785");

        String coursesArr[] ={"Java", "Azure"};
        data.setCourses(coursesArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Rohit Sharma"))
                .body("location", equalTo("Australia"))
                .body("phone", equalTo("8869614785"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Azure"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();


    }

    // 3. POST request body using external JSON File

    @Test (priority = 4)
    void testPostUsingExternalJSONFile() throws FileNotFoundException {

       //Open the File
       File f = new File("body.json");

       //Read the file data  (FileReader)
        FileReader fr = new FileReader(f);

        //JSON Tokener
        JSONTokener jt = new JSONTokener(fr);

        JSONObject data = new JSONObject(jt);


        given()
                .contentType("application/json")
                .body(data.toString()) //Ass data is in form of JSON so we need to convert it into String

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Rohit Sharma"))
                .body("location", equalTo("Australia"))
                .body("phone", equalTo("8869614785"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Azure"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();


    }

    @Test(priority = 5)
    void testDelete(){

        given()

                .when()
                .delete("http://localhost:3000/students/7")

                .then()
                .statusCode(200);
    }


}
