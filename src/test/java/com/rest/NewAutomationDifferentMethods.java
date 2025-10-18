package com.rest;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class NewAutomationDifferentMethods {
    String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6ImV4dGVybmFsdXMiLCJyb2xlIjoiRXh0ZXJuYWwiLCJjbGllbnRJRCI6IjExOCIsIm5iZiI6MTc2MDgxNjg3MywiZXhwIjoxNzYwODE4NjczLCJpYXQiOjE3NjA4MTY4NzN9.YvHbegVLipOWgFoFd71fp31Be-BZKM06hEfFwjj1Yyc";
    public void beforesetup(){
        RestAssured.baseURI="https://cpasaapiqa.crowe.com";
    }

    @Test
    public void postrequest(){
        String payload= "{    \n" +
                "    \"EntityName\": \"Test SiteDict show_addentity 12\",\n" +
                "    \"EntityTypeId\": 0,\n" +
                "    \"taxId\": \"\",\n" +
                "    \"ReportingFranchiseId\": 824,\n" +
                "    \"parentId\": 437569,\n" +
                "    \"fiscalYearEndMonth\": 12,\n" +
                "    \"countryId\": 1335,\n" +
                "    \"stateId\": 1246,\n" +
                "    \"accountNumber\": \"\",\n" +
                "    \"typeOfContractorId\": 0,\n" +
                "    \"SuretyAgent\" : 102113,   \n" +
                "}";
        given().
                headers("Authorization", "Bearer " + token).
                contentType("application/json").
                body(payload).

                when().
                post("/api/v1/entity").

                then().
                log().all().
                assertThat().
                statusCode(200).
                assertThat().
                body("Entityname",equalTo("Test SiteDict show_addentity 12"),
                        "entityId.toString()", matchesPattern("^\\d{6}$"));

    }

    @Test
    public void postrequestwithfile(){
        File file= new File("src/main/resources/CreateEntityPayload.json");

        given().
                headers("Authorization", "Bearer " + token).
                contentType("application/json").
                body(file).

                when().
                post("/api/v1/entity").

                then().
                log().all().
                assertThat().
                statusCode(200).
                assertThat().
                body("Entityname",equalTo("Test SiteDict show_addentity 122"),
                        "entityId.toString()", matchesPattern("^\\d{6}$"));

    }

    @Test
    public void postrequestwithJSONArrayasList(){
        Map<String,String> map= new HashMap<>();
        given().
                headers("Authorization", "Bearer " + token).
                contentType("application/json").
                body(map).

                when().
                post("/api/v1/entity").

                then().
                log().all().
                assertThat().
                statusCode(200).
                assertThat().
                body("Entityname",equalTo("Test SiteDict show_addentity 12"),
                        "entityId.toString()", matchesPattern("^\\d{6}$"));

    }

    @Test
    public void putrequest(){
        String payload="{\n" +
                "    \"entityId\": 437573,\n" +
                "    \"taxId\": \"112211332\"\n" +
                "}";
        given().
                headers("Authorization", "Bearer " + token).
                contentType("application/json").
                body(payload).

                when().
                put("/api/v1/entity").

                then().
                log().all().
                assertThat().
                body("entityId.toString()",equalTo("437573"));

    }

    @Test
    public void deleterequest(){
        int entityId = 438543;
        String username = "abajpai";

        given().
                headers("Authorization", "Bearer " + token).
                contentType("application/json").
                pathParam("entityId", entityId)
                .pathParam("username", username).

        when().
                delete("/api/v1/entity/{entityId}/{username}").

                then()
                .log().all().
                statusCode(200);
    }

    @Test
    public void multiplequeryparam(){


        given().
                headers("Authorization", "Bearer " + token).
                contentType("application/json").
                queryParam("startDateTime","2025-10-15").
                queryParam("endDateTime","2025-10-17").


                when().
                get("/api/v1/datagroup/search").

                then()
                .log().all().
                statusCode(200);
    }

}
