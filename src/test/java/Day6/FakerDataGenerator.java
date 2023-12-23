package Day6;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerDataGenerator {

    @Test
    void testGenerateDummyData(){

        //It will generate a random data everytime when we run

        Faker faker = new Faker();

        String fullname = faker.name().fullName();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();

        String phoneno = faker.phoneNumber().cellPhone();

        String email = faker.internet().emailAddress();

        String username = faker.name().username();
        String password = faker.internet().password();

        System.out.println(fullname);
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(phoneno);
        System.out.println(email);
        System.out.println(username);
        System.out.println(password);

    }
}
