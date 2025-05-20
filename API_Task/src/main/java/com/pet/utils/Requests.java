package com.pet.utils;

import com.pet.model.Pet;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Requests {

    private static String BASE_URL;

    private final HashMap<String, String> headerMap;

    public Requests() {
        // Set the base URI once
        BASE_URL = "https://petstore3.swagger.io/api/v3";

        // Initialize headers in constructor, Can add more headers in the map if exists
        headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
    }

    public Response addPet(Pet pet, String endPoint) {
        return given()
                .headers(headerMap)
                .body(pet)
                .baseUri(BASE_URL)
                .basePath(endPoint)
                .when()
                .post(BASE_URL + endPoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response updatePet(Pet pet, String endPoint) {
        return given()
                .headers(headerMap)
                .body(pet)
                .baseUri(BASE_URL)
                .basePath(endPoint)
                .when()
                .put(BASE_URL + endPoint)
                .then()
                .extract()
                .response();
    }

    public Response getPet(Pet pet, String endPoint, String petId) {
        return given()
                .headers(headerMap)
                .body(pet)
                .baseUri(BASE_URL)
                .basePath(endPoint+"/{petId}")
                .pathParam("petId", petId)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public Response deletePet(Pet pet, String endPoint, String petId) {
        return given()
                .headers(headerMap)
                .body(pet)
                .baseUri(BASE_URL)
                .basePath(endPoint+"/{petId}")
                .pathParam("petId", petId)
                .when()
                .delete();
    }
}
