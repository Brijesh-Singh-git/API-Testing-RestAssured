package Day5;


import Day2_Ways_To_Create_POSTRequest_Body.POJO_Post_Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.transform.stc.POJO;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

//Pojo --- Serialize ----> JSON Object ---- de-serialize ---> Pojo
public class SerializationDeserialization {

    @Test
    void connvertPojo2Json() throws JsonProcessingException {

        //Created java object using POJO Class
        POJO_Students_Class  studpojo = new POJO_Students_Class();  //Pojo class object

        studpojo.setName("Rohit Sharma");
        studpojo.setLocation("Australia");
        studpojo.setPhone("8869614785");

        String coursesArr[] ={"Java", "Azure"};
        studpojo.setCourses(coursesArr);



        //Now converting this Java Object into the JSON object through Serialization
        ObjectMapper objMapper = new ObjectMapper();  //Import through jackson api

        String jsonData =  objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(studpojo);  //this converts it into JSON object, and returns it into string
        System.out.println(jsonData);

    }


    //Json to Pojo ---> Deserialization
    @Test
    void connvertJson2Pojo() throws JsonProcessingException {

        String jsonData = "{\n" +
                "  \"name\" : \"Rohit Sharma\",\n" +
                "  \"location\" : \"Australia\",\n" +
                "  \"phone\" : \"8869614785\",\n" +
                "  \"courses\" : [ \"Java\", \"Azure\" ]\n" +
                "}";

        //Now converting this Json String into the Pojo object through De-Serialization
        ObjectMapper objMapper = new ObjectMapper();  //Import through jackson api

        POJO_Students_Class stupojo = objMapper.readValue(jsonData, POJO_Students_Class.class);  //Converts json to Pojo Class

        System.out.println("Name : " + stupojo.getName());
        System.out.println("Location : " + stupojo.getLocation());
        System.out.println("Phone : " + stupojo.getPhone());
        System.out.println("Course1 : " + stupojo.getCourses()[0]);
        System.out.println("Course2 : " + stupojo.getCourses()[1]);


    }
}
