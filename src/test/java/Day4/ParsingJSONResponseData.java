package Day4;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {

    //    @Test
    void testJSONResponse() {


        //Approach 1 --> Without capturing the response

 /*       given()
                .contentType(ContentType.JSON)


                .when()
                .get("http://localhost:3000/store")


                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("book[3].title", equalTo("To Kill a Mockingbird"));  //Used the JSONPath Finder to locate the element

*/


        //Approach 2 --> Capturing whole response into a variable and then performing the operations

   /*     Response res = given()
                .contentType(ContentType.JSON)

                .when()

                .get("http://localhost:3000/store");


        Assert.assertEquals(res.getStatusCode(), 200);   //validation of status code using TestNG assert
        Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");

        String bookname = res.jsonPath().get("book[3].title").toString();  //JsonPath will give in Object format so we need to convert it into String in order to validate
        Assert.assertEquals(bookname, "To Kill a Mockingbird");



    */

    }

    @Test
    void testJSONResponseBodyData() {


        //Approach 2 --> Capturing whole response into a variable and then performing the operations

        Response res = given()
                .contentType(ContentType.JSON)

                .when()

                .get("http://localhost:3000/store");


     /*   Assert.assertEquals(res.getStatusCode(), 200);   //validation of status code using TestNG assert
        Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");

        String bookname = res.jsonPath().get("book[3].title").toString();  //JsonPath will give in Object format so we need to convert it into String in order to validate
        Assert.assertEquals(bookname, "To Kill a Mockingbird");

        */


        //SEARCH FOR TITLE OF THE BOOK IN JSON ---> VALIDATION 1

        //Q -> We have different books so we need to read all the title and print the same on the console. (If order is gettig changed then we need
        //     to print thr whole data title and then check if it is present or not)


        //Json object class
        //(We need to convert the response (res) into the JSON Object format i.e. string so we are using the JSONObjectClass)


        JSONObject jo = new JSONObject(res.asString());  //converting response to JSON object type

   /*

        boolean status = false;

        for(int i =0; i<jo.getJSONArray("book").length(); i++){

            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            System.out.println(bookTitle);

            if(bookTitle.equals("To Kill a Mockingbird")){
                status = true;
                break;

                //If status is True means Book found, if false then book not found
            }
        }

        Assert.assertEquals(status,true);

        */


        //VALIDATE TOTAL PRICE OF THE BOOKS

        // Q--> Total price of all the books and compare it with the value

        double totalPrice = 0;
        for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

            String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
            totalPrice = totalPrice + Double.parseDouble(price);

        }

        System.out.println("Total price of the Books is: " + totalPrice);
        Assert.assertEquals(totalPrice, 220.43);
    }
}
