package Testcases;

import Data.DataProviders;
import com.pet.model.Category;
import com.pet.model.Pet;
import com.pet.model.Tag;
import com.pet.utils.Requests;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class Pet_Testcases {

    private static String EndPoint = "/pet";

    Pet pet = new Pet();
    Category category = new Category();
    Tag tag = new Tag();

    Requests requests = new Requests();
    JsonPath jsonPath;
    Response response;

    List<Tag> actualTags;
    List<Tag> expectedTags;

    @BeforeMethod
    public void BeforeMethod() {

        pet.setId("10");
        pet.setName("doggie");
        pet.setStatus("available");
        pet.setPhotoUrls(Collections.singletonList("string"));

        category.setId("1");
        category.setName("Dogs");
        pet.setCategory(category);

        tag.setId("0");
        tag.setName("tagName");
        pet.setTags(Collections.singletonList(tag));

//        pet.setTags(List.of(tag1, tag2));


    }

    @Test
    public void AddSinglePet_HappyScenario() {

        // Call request to get response and assert on it
        response = requests.addPet(pet, EndPoint);

        // Print Response
        System.out.println("Response:\n" + response.asPrettyString());

        jsonPath = response.jsonPath();
        actualTags = jsonPath.getList("tags", Tag.class);
        expectedTags = pet.getTags();

        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPath.getString("id"), pet.getId());
        Assert.assertEquals(jsonPath.getString("name"), pet.getName());
        Assert.assertEquals(jsonPath.getString("status"), pet.getStatus());
        Assert.assertEquals(jsonPath.getString("photoUrls"), pet.getPhotoUrls().toString());
        Assert.assertEquals(jsonPath.getString("category.id"), category.getId());
        Assert.assertEquals(jsonPath.getString("category.name"), category.getName());

        // Loop for asserting on all tags in the response
        for (int i = 0; i < expectedTags.size(); i++) {
            Assert.assertEquals(actualTags.get(i).getId(), expectedTags.get(i).getId(), "Mismatch at tag index " + i + 1);
            Assert.assertEquals(actualTags.get(i).getName(), expectedTags.get(i).getName(), "Mismatch at tag index " + i + 1);
        }

        System.out.println("Pet added successfully\n");
    }

    @Test(dataProvider = "petData", dataProviderClass = DataProviders.class)
    public void AddMultiplePets_HappyScenario(String petId, String petName) {

        pet.setId(petId);
        pet.setName(petName);

        // Call request to get response and assert on it
        response = requests.addPet(pet, EndPoint);

        // Print Response
        System.out.println("Response:\n" + response.asPrettyString());

        jsonPath = response.jsonPath();
        actualTags = jsonPath.getList("tags", Tag.class);
        expectedTags = pet.getTags();

        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPath.getString("id"), pet.getId());
        Assert.assertEquals(jsonPath.getString("name"), pet.getName());
        Assert.assertEquals(jsonPath.getString("status"), pet.getStatus());
        Assert.assertEquals(jsonPath.getString("photoUrls"), pet.getPhotoUrls().toString());
        Assert.assertEquals(jsonPath.getString("category.id"), category.getId());
        Assert.assertEquals(jsonPath.getString("category.name"), category.getName());

        // Loop for asserting on all tags in the response
        for (int i = 0; i < expectedTags.size(); i++) {
            Assert.assertEquals(actualTags.get(i).getId(), expectedTags.get(i).getId(), "Mismatch at tag index " + i + 1);
            Assert.assertEquals(actualTags.get(i).getName(), expectedTags.get(i).getName(), "Mismatch at tag index " + i + 1);
        }

        System.out.println("Pet added successfully\n");
    }

    @Test
    public void UpdatePet_HappyScenario() {

        // Update pet name to be doggieeee instead of doggie
        pet.setName("doggieeee");

        // Call request to get response and assert on it
        response = requests.updatePet(pet, EndPoint);

        // Print Response
        System.out.println("Response:\n" + response.asPrettyString());

        jsonPath = response.jsonPath();
        actualTags = jsonPath.getList("tags", Tag.class);
        expectedTags = pet.getTags();

        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPath.getString("id"), pet.getId());
        Assert.assertEquals(jsonPath.getString("name"), pet.getName());
        Assert.assertEquals(jsonPath.getString("status"), pet.getStatus());
        Assert.assertEquals(jsonPath.getString("photoUrls"), pet.getPhotoUrls().toString());
        Assert.assertEquals(jsonPath.getString("category.id"), category.getId());
        Assert.assertEquals(jsonPath.getString("category.name"), category.getName());

        // Loop for asserting on all tags in the response
        for (int i = 0; i < expectedTags.size(); i++) {
            Assert.assertEquals(actualTags.get(i).getId(), expectedTags.get(i).getId(), "Mismatch at tag index " + i );
            Assert.assertEquals(actualTags.get(i).getName(), expectedTags.get(i).getName(), "Mismatch at tag index " + i );
        }

        System.out.println("Pet updated successfully\n");
    }

    @Test(dataProvider = "petData", dataProviderClass = DataProviders.class)
    public void GetPet_HappyScenario(String petId, String petName) {

        pet.setId(petId);
        pet.setName(petName);

        // Call request to get response and assert on it
        response = requests.getPet(pet, EndPoint, pet.getId());

        // Print Response
        System.out.println("Response:\n" + response.asPrettyString());

        jsonPath = response.jsonPath();
        actualTags = jsonPath.getList("tags", Tag.class);
        expectedTags = pet.getTags();

        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPath.getString("id"), pet.getId());
        Assert.assertEquals(jsonPath.getString("name"), pet.getName());
        Assert.assertEquals(jsonPath.getString("status"), pet.getStatus());
        Assert.assertEquals(jsonPath.getString("photoUrls"), pet.getPhotoUrls().toString());
        Assert.assertEquals(jsonPath.getString("category.id"), category.getId());
        Assert.assertEquals(jsonPath.getString("category.name"), category.getName());

        // Loop for asserting on all tags in the response
        for (int i = 0; i < expectedTags.size(); i++) {
            Assert.assertEquals(actualTags.get(i).getId(), expectedTags.get(i).getId(), "Mismatch at tag index " + i );
            Assert.assertEquals(actualTags.get(i).getName(), expectedTags.get(i).getName(), "Mismatch at tag index " + i );
        }

        System.out.println("Pet found successfully\n");
    }

    @Test
    public void DeletePet_HappyScenario() {

        pet.setId("10");

        // Call request to get response and assert on it
        response = requests.deletePet(pet, EndPoint, pet.getId());

        // Print Response
        System.out.println("Response:\n" + response.asString());

        // Validate the response
        Assert.assertEquals(response.asString(), "Pet deleted");

        System.out.println("Pet deleted successfully\n");

        // Assert with a get request to ensure that pet has been deleted and not found
        Response getResponse = requests.getPet(pet, EndPoint, pet.getId());

        // Validate the response
        Assert.assertEquals(getResponse.getStatusCode(), 404);
    }

}
