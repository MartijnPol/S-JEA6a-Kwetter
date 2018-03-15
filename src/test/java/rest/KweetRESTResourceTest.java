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
        given()
                .when().get("/kweets/1")
                .then()
                .statusCode(200)
                .body(containsString("Test"));
    }

    @Test
    public void findByIdFail() {
        given()
                .when().get("/kweets/100")
                .then()
                .statusCode(404);
    }

    @Test
    public void findAllKweetsByMessage() {
        given()
                .when().get("/kweets/find/Test")
                .then()
                .statusCode(200)
                .body(containsString("1"));
    }

}
