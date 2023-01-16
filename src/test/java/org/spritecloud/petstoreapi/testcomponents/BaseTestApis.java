package org.spritecloud.petstoreapi.testcomponents;

import io.restassured.response.Response;
import org.spritecloud.petstoreapi.objects.pojo.Pet;
import org.spritecloud.petstoreapi.objects.requestpreparation.Utils;
import org.spritecloud.petstoreapi.objects.resources.EndPoints;
import java.io.IOException;
import static io.restassured.RestAssured.given;

//This class is used in sending the requests
public class BaseTestApis {
    Response response = null;

    //A method used in sending the Add Pet Api
    public Response addPetApI(Pet payload) {
        try {
            response = given().spec(Utils.addPetRequestSpecification())
                    .body(payload)
                    .when().post(EndPoints.getPetEndPoint())
                    .then().extract().response();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //A method used in sending the Get Pet Api
    public Response getPetApi(int pathParameter) {
        // Sending the Get Pet API request and getting the response
        try {
            response = given().spec(Utils.getPetRequestSpecification(pathParameter))
                    .when().get(EndPoints.getPetEndPoint() + "/{pet_Id}").then().extract().response();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //A method used in sending the Update Pet Api
    public Response updatePetApi(int pathParameter, String updatedPetName, String updatedPetStatus) {
        // Sending the Update Pet API request and getting the response
        try {
            response = given().spec(Utils.updatePetRequestSpecification(pathParameter)).
                    formParam("name", updatedPetName).formParam("status", updatedPetStatus)
                    .when().post(EndPoints.getPetEndPoint() + "/{pet_Id}").
                    then().extract().response();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //A method used in sending the delete pet Api
    public Response deletePetApi(int pathParameter) {
        try {
            response = given().spec(Utils.deletePetRequestSpecification(pathParameter))
                    .when().
                    delete(EndPoints.getPetEndPoint() + "/{pet_Id}").then().extract().response();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
