package rest;

import io.restassured.RestAssured;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
public class RESTResourceSetup {

    static void Setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = 8080;
        } else {
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "/Kwetter/api";
        }

        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }

        RestAssured.baseURI = baseHost;
    }

}
