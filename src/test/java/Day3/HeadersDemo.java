package Day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class HeadersDemo {

//    @Test
    void testHeaders(){

        given()

                .when()
                .get("https://www.google.com/")


                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding", "gzip")
                .and()
                .header("Server", "gws")
                .log().all();

    }

    //Capture Information from Headers

    @Test
    void getHeadersInfo(){

       Response res = given()

                .when()
                .get("https://www.google.com/");


        //get single header Info
//        String header_value = res.getHeader("Content-Type");
//        System.out.println("The Value of Content-Type header is ====> " + header_value);


        //get all the headers Info
        Headers myHeaders =res.getHeaders();

        for(  Header hd :myHeaders){

            System.out.println(hd.getName() + " ----> " + hd.getValue());
        }



    }

}
