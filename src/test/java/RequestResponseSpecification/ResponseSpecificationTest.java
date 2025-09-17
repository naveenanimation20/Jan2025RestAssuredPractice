package RequestResponseSpecification;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class ResponseSpecificationTest {
	
	
	@Test
	public void responseSpecTest() {
		ResponseSpecification resSpec = expect()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.header("Server", "cloudflare");
		
		
		given()
			.baseUri("https://gorest.co.in")
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when()
			.get("/public/v2/users")
		.then()
			.spec(resSpec);
	}
	
	
	
	

}
