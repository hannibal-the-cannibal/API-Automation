package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class SerialandDeserial {
    String token="";
    @Test
    public void postrequestwithJSONArrayasList() throws JsonProcessingException {
        Map<String,String> map= new HashMap<>();
        map.put("entityId","33451");
        map.put("fiscalYearEndMonth","9");

        ObjectMapper objectMapper= new ObjectMapper();
        String mainobj= objectMapper.writeValueAsString(map);
        given().
                headers("Authorization", "Bearer " + token).
                baseUri("https://cpasaapiqa.crowe.com").
                contentType("application/json").
                body(mainobj).

                when().
                post("/api/v1/entity/FiscalYearEndMonth").

                then().
                log().all().
                assertThat().
                statusCode(200);


    }
}
