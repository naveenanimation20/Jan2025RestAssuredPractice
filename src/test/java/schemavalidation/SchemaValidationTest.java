package schemavalidation;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class SchemaValidationTest {
	
	
	@Test
	public void getUsersAPISchemaTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		RestAssured.given()
						.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.when()
						.get("/public/v2/users")
					.then()
						.assertThat()
							.statusCode(200)
							.and()
							.body(matchesJsonSchemaInClasspath("getuserschema.json"));
	}
	
	
	@Test
	public void createUserAPISchemaTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		RestAssured.given()
						.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
						.body(new File("./src/test/resources/jsons/createuser.json"))
						.contentType(ContentType.JSON)
					.when()
						.post("/public/v2/users")
					.then()
						.assertThat()
							.statusCode(201)
							.and()
							.body(matchesJsonSchemaInClasspath("createuserschema.json"));
	}
	
	

}
