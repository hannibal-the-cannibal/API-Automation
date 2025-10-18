package com.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

public class HandlingHeaders {

    Header headerobj1 = new Header("Connection", "keep-alive");
    Header headerobj2 = new Header("Connection1", "keep-alive2");

    Headers headers= new Headers(headerobj1,headerobj2);

    @Test
    public void headershandle() {
       Headers extractheaders= given()
                .baseUri("https://cpasaapiqa.crowe.com")
                // for one header
                //.header("Connection", "keep-alive")

                // here we are passing headers with our request
                // for multiple headers we can also create Headers object
                .headers(headers)
                .when()
                .get("/api/v1/entity/130536")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                //asserting our Request headers
                .header("Connection","keep-alive")

                //asserting response headers
                .extract()
                .headers();
        System.out.println(extractheaders.get("Connection").getName());
        System.out.println(extractheaders.get("Connection").getValue());
    }
}
