package PetAPI;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import PetAPI.Pet.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreatePetTest {
	
	@Test
	public void createAPetTest() {
		
		RestAssured.baseURI = "https://petstore3.swagger.io";
				
		Pet.Category category = new Pet.Category(1, "Dog");
		
		List<String> photoUrls = Arrays.asList("https://ex.com", "https://dog.com");
		
		Pet.Tag tag1 = new Pet.Tag(1, "Red");
		Pet.Tag tag2 = new Pet.Tag(2, "Black");

		List<Tag> tags = Arrays.asList(tag1, tag2);
		
		Pet pet = new Pet(101, "Ronney", "available", category, photoUrls, tags);
		
		//pojo to json: serialization
		Response response = given().log().all()
			.contentType(ContentType.JSON)
			.body(pet)
		.when().log().all()
			.post("/api/v3/pet");
		
		response.prettyPrint();
		
		//Deserialization: json to pojo:
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Pet petRes = mapper.readValue(response.getBody().asString(), Pet.class);
			System.out.println(petRes.getName());
			System.out.println(petRes.getId());
			System.out.println(petRes.getStatus());
			System.out.println(petRes.getCategory().getName());
			System.out.println(petRes.getCategory().getId());
			System.out.println(petRes.getPhotoUrls());
			
			System.out.println(petRes.getTags().get(0).getId());
			System.out.println(petRes.getTags().get(0).getName());

			System.out.println(petRes.getTags().get(1).getId());
			System.out.println(petRes.getTags().get(1).getName());

			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Test
	public void createAPetUsingBuilderTest() {
		
		RestAssured.baseURI = "https://petstore3.swagger.io";
				
		
		Pet.Category category = new Pet.Category.CategoryBuilder()
				.id(200)
				.name("Cat")
				.build();
		
		
		Tag tag1 = new Tag.TagBuilder()
					.id(300)
					.name("Red")
					.build();
		
		Tag tag2 = new Tag.TagBuilder()
				.id(400)
				.name("White")
				.build();
		
		List<Tag> tags = Arrays.asList(tag1, tag2);
		
		List<String> photoUrls = Arrays.asList("https://ex.com", "https://dog.com");

		Pet pet = new Pet.PetBuilder()
				.id(1)
				.name("Bunny")
				.status("available")
				.category(category)
				.tags(tags)
				.photoUrls(photoUrls)
				.build();
				
		
		
		//pojo to json: serialization
		Response response = given().log().all()
			.contentType(ContentType.JSON)
			.body(pet)
		.when().log().all()
			.post("/api/v3/pet");
		
		response.prettyPrint();
		
		//Deserialization: json to pojo:
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Pet petRes = mapper.readValue(response.getBody().asString(), Pet.class);
			System.out.println(petRes.getName());
			System.out.println(petRes.getId());
			System.out.println(petRes.getStatus());
			System.out.println(petRes.getCategory().getName());
			System.out.println(petRes.getCategory().getId());
			System.out.println(petRes.getPhotoUrls());
			
			System.out.println(petRes.getTags().get(0).getId());
			System.out.println(petRes.getTags().get(0).getName());

			System.out.println(petRes.getTags().get(1).getId());
			System.out.println(petRes.getTags().get(1).getName());

			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
