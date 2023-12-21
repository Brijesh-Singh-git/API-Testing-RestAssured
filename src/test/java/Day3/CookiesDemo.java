package Day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class CookiesDemo {


    @Test
    void testCookies(){

        given()

                .when()
                .get("https://www.google.com/")


                .then()
                .cookie("AEC", "Ackid1TRS5cbw_te7OgzI2-sPIxCrdX5hGfpVQBltfHX8J870SUbDd8cwTc")  //On every response the Cookie value will change
                .log().all();


    }

    @Test
    void getCookieInfo(){

       Response res = given()

                .when()
                .get("https://www.google.com/");  //Capturing whole response into a single variable

                //get single cookie info
                //String cookie_value = res.getCookie("AEC");
                //System.out.println("Value of cookie is ====> " + cookie_value);


                //get all cookie value
                Map<String, String> cookies_value = res.getCookies();
                System.out.println(cookies_value.keySet());

                //To get the values of all the cookies we iterate through for loop

                for(String k :cookies_value.keySet()) {

                    String cookie_value = res.getCookie(k);
                    System.out.println(k + " --->" +" " +cookie_value);
                }


    }
}
