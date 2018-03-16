package rest;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static rest.RESTResourceSetup.Setup;

/**
 * Created by Martijn van der Pol on 16-03-18
 **/
public class HashtagRESTResourceTest {

    @BeforeClass
    public static void setup() {
        Setup();
    }

    @Test
    public void findAll() {
        given()
                .when().get("/hashtags")
                .then()
                .statusCode(200)
                .body(containsString("LoveJEA"));
    }

    @Test
    public void findById() {
        given()
                .when().get("/hashtags/1")
                .then()
                .statusCode(200)
                .body(containsString("LoveJEA"));
    }

    @Test
    public void findAllBySubject() {
        given()
                .when().get("/hashtags/find/LoveJEA")
                .then()
                .statusCode(200)
                .body(containsString("1"));
    }

}
