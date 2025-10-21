package com.rest;
import PojoClasses.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;
import org.testng.annotations.Test;

public class POJOExample {

    @Test
    public void pojoclassexample() throws JsonProcessingException, JSONException {

        Header header= new Header("Content-Type","application/json");
        List<Header> headerList= new ArrayList<Header>();
        headerList.add(header);

        Body body= new Body("raw","{\"data\": \"123\"}");

        Request request= new Request("https://postman-echo.com/post","POST",headerList, body,"This is a sample POST Request");

        RequestRoot requestRoot= new RequestRoot("Sample POST Request",request);
        List<RequestRoot> requestRootList= new ArrayList<>();
        requestRootList.add(requestRoot);

        Folder folder= new Folder("This is a Folder", requestRootList);
        List<Folder> folderlist= new ArrayList<>();
        folderlist.add(folder);

        Info info= new Info("Sample Collection 1","This is just a sample collection.","https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        Collection collection= new Collection(info,folderlist);

        PojoclassRoot collectionroot= new PojoclassRoot(collection);









        String collectiouid= given()
                .baseUri("https://api.getpostman.com")

                .body(collectionroot)
                .when()
                .post("/collections")
                .then()
                .log().all()
                .extract().response().path("collection.uid");

        PojoclassRoot deserialCollectionroot=given()
                .baseUri("https://api.getpostman.com")
                .pathParam("CollectionUid", collectiouid)
                .when()
                .get("/collections/{CollectionUid}")
                .then()
                .extract().response()
                .as(PojoclassRoot.class);


        ObjectMapper objectMapper= new ObjectMapper();
        String collectionrootstr= objectMapper.writeValueAsString(collectionroot);
        String deserialzedCollectionstr= objectMapper.writeValueAsString(deserialCollectionroot);



        JSONAssert.assertEquals(
                collectionrootstr,
                deserialzedCollectionstr,
                new CustomComparator(
                        JSONCompareMode.STRICT,
                        new Customization("collection.info.uid", (a, b) -> true),
                        new Customization("collection.info._postman_id", (a,b) -> true),
                        new Customization("collection.info.updatedAt", (a,b) -> true)
                )
        );




    }
}
