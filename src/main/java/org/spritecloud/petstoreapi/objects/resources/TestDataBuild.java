package org.spritecloud.petstoreapi.objects.resources;


import org.spritecloud.petstoreapi.objects.pojo.CategoryDetailsList;
import org.spritecloud.petstoreapi.objects.pojo.Pet;
import org.spritecloud.petstoreapi.objects.pojo.TagsDetailsList;


import java.util.ArrayList;

public class TestDataBuild {

    public Pet addPetPayload() {
        Pet addPetRequestPayLoad = new Pet();
        CategoryDetailsList category = new CategoryDetailsList();
        category.setId(1);
        category.setName("Dogs");
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add("test");
        ArrayList<TagsDetailsList> tags = new ArrayList<>();
        TagsDetailsList tags1 = new TagsDetailsList();
        tags1.setId(1);
        tags1.setName("tags");
        tags.add(tags1);
        addPetRequestPayLoad.setId(4313);
        addPetRequestPayLoad.setCategory(category);
        addPetRequestPayLoad.setPhotoUrls(photoUrls);
        addPetRequestPayLoad.setName("Nutella");
        addPetRequestPayLoad.setTags(tags);
        addPetRequestPayLoad.setStatus("available");
        return addPetRequestPayLoad;
    }

    public int successStatusCode() {
        return 200;
    }

    public int failureStatusCode404() {
        return 404;
    }

    public String updatePetName() {
        return "Nutella_updated";
    }

    public String updatePetStatus() {
        return "Sold";
    }


}
