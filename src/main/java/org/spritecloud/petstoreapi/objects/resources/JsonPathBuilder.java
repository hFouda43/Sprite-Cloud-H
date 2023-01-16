package org.spritecloud.petstoreapi.objects.resources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathBuilder {

    public static JsonPath jsonPathBuilderMethod(Response response){
      return new JsonPath(response.asString());
    }
}
