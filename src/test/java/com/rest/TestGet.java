package com.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;  // ✅ use this import

import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestGet {

    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6ImV4dGVybmFsdXMiLCJyb2xlIjoiRXh0ZXJuYWwiLCJjbGllbnRJRCI6IjExOCIsIm5iZiI6MTc2MDc3MjU4NywiZXhwIjoxNzYwNzc0Mzg3LCJpYXQiOjE3NjA3NzI1ODd9.VHhZ6f9nrLRd_Iohp9721vIVDGx04W90GqlOEV798fA";

    @Test
    public void test() {
       Response resobj= given()
                .headers("Authorization", "Bearer " + token)
               // blacklisting headers in log
               .config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-key")))

               //for logging purpose
                .log().all()
                .when()
                .get("https://cpasaapiqa.crowe.com/api/v1/entity/130536")
                .then()
                .log().all()
                // logging if there is error
               .log().ifError()

               //logging if there is failure in validation
               .log().ifValidationFails()
                .assertThat()
                .statusCode(200)


               .body("$", hasKey("clientId"))
                .body(
                        "clientId", equalTo(118),
                        "entityName", equalTo("16773 Test Blank Rows 1")
                ).
                extract().response();

        JsonPath jsonPath= new JsonPath(resobj.asString());
        System.out.println(jsonPath.getInt("clientId"));
       System.out.println(jsonPath.getMap("").size());
       System.out.println(resobj);
       assertThat("entityName", equalTo("16773 Test Blank Rows 1"));
    }
}
