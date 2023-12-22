package Day4;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingXMLResponseData {

//    @Test
    void testXMLResponse(){

        //Approach 1 --> Without storing the response into the variable

   /*     given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")

                .then()
                .statusCode(200)
                .header("Content-Type", "application/xml; charset=utf-8")
                .body("TravelerinformationResponse.page", equalTo("1"))  //to validate if it is Page 1
                .body("TravelerinformationResponse.travelers.Travelerinformation[2].id", equalTo("11135"))  //To validate the Id of 2nd Traveller
                .body("TravelerinformationResponse.travelers.Travelerinformation[2].email", equalTo("van.19v@mail.ru"));  //To validate the email of 2nd Traveller

    */


        //Approach 2 --> Capturing the response into a variable


        Response res = given()

                             .when()
                             .get("http://restapi.adequateshop.com/api/Traveler?page=1");


        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");

        String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();                   //convert it into String
        Assert.assertEquals(pageNo, "1");

        String emailId = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[2].email").toString();
        Assert.assertEquals(emailId, "van.19v@mail.ru");


    }


    @Test
    void testXMLResponseBody(){


        //Approach 2 --> Capturing the response into a variable


        Response res = given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");



        //Validation 1 -> Capture the total number of travellers

        XmlPath xmlobj = new XmlPath(res.asString());

        //List<String> travellers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
        //Assert.assertEquals(travellers.size(), 10);   //to get the total number of Travellers



        //Validation 2 -> To verify traveller name is present on the file or not, and the order of file is changing randomly

        //first capture all the traveller names
        List<String> traveller_names = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

        //now write the loop to iterate over the names and search the particualr name
        boolean status = false;
        for(String traveller_name : traveller_names) {

            //System.out.println(traveller_name);

            if(traveller_name.equals("Ashor")){
                status = true;
                break;
            }

        }
        Assert.assertEquals(status, true);

    }
}
