package POSTAPITests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserAPITest {
	
	public String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	
	@Test
	public void createUserWithJSONStringTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailId();
		
		given().log().all()
			.contentType(ContentType.JSON)
			.body("{\n"
					+ "    \"name\": \"seema\",\n"
					+ "    \"gender\": \"female\",\n"
					+ "    \"email\": \""+emailId+"\",\n"
					+ "    \"status\": \"active\"\n"
					+ "}")
		.when()
			.post("/users")
		.then().log().all()
			.assertThat()
				.statusCode(201);
		
	}
	
	
	@Test
	public void createUserWithJSONFileTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/jsons/user.json"))
		.when()
			.post("/public/v2/users")
		.then().log().all()
			.assertThat()
				.statusCode(201);
		
	}
	
	
	@Test
	public void createUserWithJSONFileWithStringReplacementTest() throws IOException {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailId();
		
		//convert the json file content to String 
		String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resources/jsons/user.json")));
		String updatedJson = rawJson.replace("{{email}}", emailId);		
		
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
	}
	
	
	//4. pojo - java object -- serialization -- deserialization --> Jackson + lombok api + builder patterns
	
	

}
