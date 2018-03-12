package rest;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static rest.RESTResourceSetup.Setup;

public class UserAccountRESTResourceTest {

    @BeforeClass
    public static void setup() {
        Setup();
    }

    @Test
    public void getAllAccountsTest() {
        given().when().get("/accounts").then().body(containsString("martijn.pol@hotmail.com"));
    }

    @Test
    public void getAccountByIdTest() {
        given().when().get("/accounts/findById?id=1").then().body(containsString("martijn.pol@hotmail.com"));
    }

    @Test
    public void countAllAccountsTest() {
        given().when().get("/accounts/count").then().body(containsString("2"));
    }

}