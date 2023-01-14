package org.spritecloud.petstoreapi.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.spritecloud.petstoreapi.objects.pojo.Pet;
import org.spritecloud.petstoreapi.objects.resources.EndPoints;
import org.spritecloud.petstoreapi.objects.resources.TestDataBuild;
import org.spritecloud.petstoreapi.testcomponents.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PetApiTests extends Utils {
    //Creating an object of test data class to load the payloads
    TestDataBuild data = new TestDataBuild();
    //Defining the requests and responses
    RequestSpecification addPetRequest;
    RequestSpecification getPetRequest;
    RequestSpecification updatePetUsingFormDataRequest;
    RequestSpecification deletePetRequest;
    Response response;

    //Defining the global response objects used by all tests
    static Pet addPetResponseObject;
    Pet getPetResponseObject;
    String petResponse;
    JsonPath jsonPath;

// Add new pet
@Test(priority = 1)
    public void addPetTest(){
    try {
        // Sending the Add Pet API request and getting the response
        addPetRequest = given().spec(requestSpecification()).body(data.addPetPayload());
        response = addPetRequest.when().post(EndPoints.getPetEndPoint())
                .then().extract().response();
        addPetResponseObject = response.as(Pet.class);
        //Validating the returned status code and pet name
        Assert.assertEquals(response.statusCode(), data.successStatusCode());
        Assert.assertEquals(addPetResponseObject.getName(), data.addPetPayload().getName());

    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Verify that the added test can be retrieved
@Test(priority = 2)
    public void getPetTest(){
    try {
        // Sending the Get Pet API request and getting the response
        getPetRequest = given().spec(requestSpecification()).pathParam("pet_Id", addPetResponseObject.getId());
        response = getPetRequest.when().get(EndPoints.getPetEndPoint() + "/{pet_Id}").then().extract().response();
        getPetResponseObject = response.as(Pet.class);
        //Validating that the id of the queried pet matches to the id of the added pet
        Assert.assertEquals(response.statusCode(), data.successStatusCode());
        Assert.assertEquals(getPetResponseObject.getId(), addPetResponseObject.getId());
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// updating the added pet name and status using update pet form data request
@Test(priority = 3)
    public void updatePetTest(){
    try {
        // Sending the Update Pet API request and getting the response
        updatePetUsingFormDataRequest = given().spec(requestSpecification()).
                pathParam("pet_Id", addPetResponseObject.getId()).
                contentType("application/x-www-form-urlencoded").
                formParam("name", data.updatePetName()).formParam("status", data.updatePetStatus());
        response = updatePetUsingFormDataRequest.when().post(EndPoints.getPetEndPoint() + "/{pet_Id}").
                then().extract().response();
        petResponse = response.asString();
        jsonPath = new JsonPath(petResponse);
// validating the response status code along with the response message & code
        Assert.assertEquals(response.statusCode(), data.successStatusCode());
        Assert.assertEquals(jsonPath.getInt("code"), data.successStatusCode());
        Assert.assertEquals(jsonPath.get("message"), String.valueOf(addPetResponseObject.getId()));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// get the updated pet and make sure that the name and status are updated
@Test(priority = 4)
    public void getUpdatedPetTest(){
    try {
        // Sending the Get Pet API request and getting the response
        getPetRequest = given().spec(requestSpecification()).pathParam("pet_Id", addPetResponseObject.getId());
        response = getPetRequest.when().get(EndPoints.getPetEndPoint() + "/{pet_Id}").then().extract().response();
        getPetResponseObject = response.as(Pet.class);
        //Validating the status code, and that the pet name and status are successfully updated
        Assert.assertEquals(response.statusCode(), data.successStatusCode());
        Assert.assertEquals(getPetResponseObject.getName(), data.updatePetName());
        Assert.assertEquals(getPetResponseObject.getStatus(), data.updatePetStatus());
    } catch (IOException e) {
        e.printStackTrace();
    }
}

//Delete the added pet
@Test(priority = 5)
    public void deletePetTest(){
    try {
        // Sending the delete Pet API request and getting the response
        deletePetRequest = given().spec(requestSpecification()).header("api_key", "special-key")
                .pathParam("pet_Id", addPetResponseObject.getId());
        response = deletePetRequest.when().delete(EndPoints.getPetEndPoint() + "/{pet_Id}").then().extract().response();
        petResponse = response.asString();
        jsonPath = new JsonPath(petResponse);
   // Validating the response status code along with the returned message and code
        Assert.assertEquals(response.statusCode(), data.successStatusCode());
        Assert.assertEquals(jsonPath.getInt("code"), data.successStatusCode());
        Assert.assertEquals(jsonPath.get("message"), String.valueOf(addPetResponseObject.getId()));

    } catch (IOException e) {
        e.printStackTrace();
    }
}

//Delete the deleted pet
    @Test(priority = 6)
    public void deleteDeletedPetTest(){
        try {
            // Sending the delete Pet API request and getting the response
            deletePetRequest = given().spec(requestSpecification()).header("api_key", "special-key")
                    .pathParam("pet_Id", addPetResponseObject.getId());
            response = deletePetRequest.when().delete(EndPoints.getPetEndPoint() + "/{pet_Id}").then().extract().response();
        // Validating that the request should error out as the resource is not found!
            Assert.assertEquals(response.statusCode(), data.failureStatusCode404());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
