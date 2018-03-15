package rest;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static rest.RESTResourceSetup.Setup;

public class UserProfileRESTResourceTest {

    @BeforeClass
    public static void setup() {
        Setup();
    }

    @Test
    public void getAllAccountsTest() {
        given()
                .when().get("/profiles")
                .then()
                .statusCode(200)
                .body(containsString("Martijn"));
    }

    @Test
    public void getAccountByIdTest() {
        given()
                .when().get("/profiles/2")
                .then()
                .statusCode(200)
                .body(containsString("Hans"));
    }

    @Test
    public void getAccountByFailTest() {
        given()
                .when().get("/profiles/100")
                .then()
                .statusCode(404);
    }

    @Test
    public void getAccountByUsernameTest() {
        given()
                .when().get("/profiles/find/MartijnPol")
                .then()
                .statusCode(200)
                .body(containsString("van der Pol"));
    }

    @Test
    public void countAllAccountsTest() {
        given().when().get("/profiles/count")
                .then()
                .statusCode(200)
                .body(containsString("2"));
    }

}