package Day6;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Authentications {

    //Basic Authentication
    @Test(priority = 1)
    void basicAuthentication() {

        given()
                .auth().basic("postman", "password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    //Digest Authentication
    @Test(priority = 2)
    void digestAuthentication() {

        given()
                .auth().digest("postman", "password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    //Preemptive Authentication
    @Test(priority = 3)
    void preemptiveAuthentication() {

        given()
                .auth().preemptive().basic("postman", "password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    //Bearer Token Authentication
    @Test(priority = 4)
    void bearerTokenAuthentication() {

        String bearerToken = "ghp_kldtL3zjMl6uv7uVjp23T0ktNfKEKT3ZvLRH";

        given()
                .header("Authorization", "Bearer " + bearerToken)

                .when()
                .get("https://api.github.com/user/repos")

                .then()
                .statusCode(200)
                .log().all();
    }

    //OAuth1.0 Token Authentication
    @Test(priority = 5)
    void oAuth1Authentication() {

        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret") //this is for OAuth1.0 authentication

                .given()
                .get("url")

                .then()
                .statusCode(200)
                .log().all();


    }


    //OAuth2.0 Token Authentication
    @Test(priority = 6)
    void oAuth2Authentication() {

        given()
                .auth().oauth2("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")

                .given()
                    .get("https://api.github.com/user/repos")

                .then()
                    .statusCode(200)
                    .log().all();

    }

    //API Key Authentication
    @Test(priority = 7)
    void apiKeyAuthentication() {

        //Method1
    /*   given()
                .queryParam("appid", "500f63d7ce3744470efaf81bf76d5dd0") //appid is our API key

                .when()
                .get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")

                .then()
                //.statusCode(200)
                .log().all();
*/

        given()
                .queryParam("appid", "500f63d7ce3744470efaf81bf76d5dd0") //appid is our API key

                .pathParams("myPath", "data/2.5/forecast/daily")

                .queryParam("q", "Delhi")

                .queryParam("units", "metric")

                .queryParam("cnt", "7")

                .when()
                .get("https://api.openweathermap.org/{myPath}")

                .then()
                .log().all();
    }

}
