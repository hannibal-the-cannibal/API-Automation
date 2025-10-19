package com.rest;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class loggingfilters {
    String token ="";
    @Test
    public void postrequestwithfile() throws FileNotFoundException {
        File file= new File("src/main/resources/CreateEntityPayload.json");

        PrintStream Fileoutputsstream= new PrintStream(new File("Restassured.log"));

        given().
                baseUri("https://cpasaapiqa.crowe.com").
                headers("Authorization", "Bearer " + token).
                filter(new RequestLoggingFilter(LogDetail.BODY,Fileoutputsstream)).
                filter(new ResponseLoggingFilter(Fileoutputsstream)).
                body(file).

                when().
                post("/api/v1/entity").

                then().
                log().all().
                assertThat().
                statusCode(200);


    }
}
