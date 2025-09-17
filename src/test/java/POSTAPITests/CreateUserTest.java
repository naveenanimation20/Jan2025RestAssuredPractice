package POSTAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserTest {
	
	//1. post-create a user -- userId--201
	//2. get-get a user -- /users/userId-200 with userId
	
	public String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	@Test
	public void createUserWithJSONFileWithStringReplacementTest() throws IOException {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailId();
		
		//convert the json file content to String 
		String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resources/jsons/user.json")));
		String updatedJson = rawJson.replace("{{email}}", emailId);		
		
		//1. post -- create a user
		Integer userId =  given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.contentType(ContentType.JSON)
			.body(updatedJson)
		.when()
			.post("/public/v2/users")
		.then().log().all()
			.assertThat()
				.statusCode(201)
					.extract()
						.path("id");
		System.out.println("user id : "+ userId);
		
		System.out.println("-----------------");
		
		//2. get a user -- GET:
		
		given().log().all()
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when()
			.get("/public/v2/users/"+userId)
			.then().log().all()
				.assertThat()
					.statusCode(200)
					.and()
						.body("id", equalTo(userId));

	}
	
	
	
	

}
