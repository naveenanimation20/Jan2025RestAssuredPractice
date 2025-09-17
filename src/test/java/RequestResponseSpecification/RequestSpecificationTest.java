package RequestResponseSpecification;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationTest {
	
	
	@Test
	public void reqSpecTest() {
		RequestSpecification requestSpec = RestAssured.given().log().all()
					.baseUri("https://jsonplaceholder.typicode.com")
					.header("Content-Type", "application/json");
		
		//1. 
		requestSpec.get("/posts")
					.then().log().all()
					.statusCode(200);
		
		//2. 
		requestSpec.get("/comments")
					.then().log().all()
					.statusCode(200);
		
		//3.
		requestSpec
				.body("{"
						+ "    title: 'foo',\n"
						+ "    body: 'bar',\n"
						+ "    userId: 1,\n"
						+ "  }")
				.post("/posts")
				.then().log().all()
				.statusCode(201);
		
	}
	
	
	@Test
	public void reqSpecGoRestAPITest() {
		RequestSpecification requestSpec = RestAssured.given().log().all()
					.baseUri("https://gorest.co.in")
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
		
		
		requestSpec
				.when()
					.get("/public/v2/users")
					.then()
					.statusCode(200);
		
		
	}
	
	
	@Test
	public void reqSpecGoRestAPIWithQueryParamsTest() {
		RequestSpecification requestSpec = RestAssured.given().log().all()
					.baseUri("https://gorest.co.in/")
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.queryParam("status", "inactive")
					.queryParam("name", "naveen");

		
		requestSpec
				.when()
					.get("/public/v2/users")
					.then()
					.statusCode(200);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
