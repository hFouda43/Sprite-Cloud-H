package org.spritecloud.petstoreapi.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.spritecloud.petstoreapi.objects.pojo.Pet;
import org.spritecloud.petstoreapi.objects.resources.JsonPathBuilder;
import org.spritecloud.petstoreapi.objects.resources.TestDataBuild;
import org.spritecloud.petstoreapi.testcomponents.BaseTestApis;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PetApiTests extends BaseTestApis {
    //Creating an object of test data class to load the payloads
    TestDataBuild data = new TestDataBuild();
    //Defining the response object
    Response response;
    //Defining the global response objects used by all tests
    static Pet petResponseObject;
    Pet getPetResponseObject;
    JsonPath jsonPath;

    // Add new pet
    @Test(priority = 1)
    public void addPetTest() {

        // Sending the Add Pet API request
        response = addPetApI(data.addPetPayload());
        petResponseObject = response.as(Pet.class);
        //Validating the returned status code and pet name
        Assert.assertEquals(response.statusCode(), data.successStatusCode());
        Assert.assertEquals(petResponseObject.getName(), data.addPetPayload().getName());

    }

    // Verify that the added test can be retrieved
    @Test(priority = 2)
    public void getPetTest() {

        // Sending the Get Pet API request
        response = getPetApi(petResponseObject.getId());
        getPetResponseObject = response.as(Pet.class);
        //Validating that the id of the queried pet matches to the id of the added pet
        Assert.assertEquals(response.statusCode(), data.successStatusCode());
        Assert.assertEquals(getPetResponseObject.getId(), petResponseObject.getId());

    }

    // updating the added pet name and status using update pet form data request
    @Test(priority = 3)
    public void updatePetTest() {
        //Sending the Updated pet API
        response = updatePetApi(petResponseObject.getId(), data.updatePetName(), data.updatePetStatus());
        jsonPath = JsonPathBuilder.jsonPathBuilderMethod(response);
        // validating the response status code along with the response message & code
        Assert.assertEquals(response.statusCode(), data.successStatusCode());
        Assert.assertEquals(jsonPath.getInt("code"), data.successStatusCode());
        Assert.assertEquals(jsonPath.get("message"), String.valueOf(petResponseObject.getId()));

    }

    // get the updated pet and make sure that the name and status are updated
    @Test(priority = 4)
    public void getUpdatedPetTest() {

        // Sending the Get Pet API
        response = getPetApi(petResponseObject.getId());
        getPetResponseObject = response.as(Pet.class);
        //Validating the status code, and that the pet name and status are successfully updated
        Assert.assertEquals(response.statusCode(), data.successStatusCode());
        Assert.assertEquals(getPetResponseObject.getName(), data.updatePetName());
        Assert.assertEquals(getPetResponseObject.getStatus(), data.updatePetStatus());

    }

    //Delete the added pet
    @Test(priority = 5)
    public void deletePetTest() {
        //Sending the delete pet API
        response = deletePetApi(petResponseObject.getId());
        jsonPath = JsonPathBuilder.jsonPathBuilderMethod(response);
        // Validating the response status code along with the returned message and code
        Assert.assertEquals(response.statusCode(), data.successStatusCode());
        Assert.assertEquals(jsonPath.getInt("code"), data.successStatusCode());
        Assert.assertEquals(jsonPath.get("message"), String.valueOf(petResponseObject.getId()));

    }

    //Delete the deleted pet
    @Test(priority = 6)
    public void deleteDeletedPetTest() {

        // Sending the delete Pet API
        response = deletePetApi(petResponseObject.getId());
        // Validating that the request should error out as the resource is not found!
        Assert.assertEquals(response.statusCode(), data.failureStatusCode404());

    }
}
