package rest;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static rest.RESTResourceSetup.Setup;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
public class KweetRESTResourceTest {

    @BeforeClass
    public static void setup() {
        Setup();
    }

    @Test
    public void findById() {
        given().when().get("/kweets/kweet/?id=1").then().body(containsString("Test"));
    }

}
