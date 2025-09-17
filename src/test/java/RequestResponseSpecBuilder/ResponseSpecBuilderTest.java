package RequestResponseSpecBuilder;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {
	
	public static ResponseSpecification responseSpec() {
		ResponseSpecification resSpec = new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("server", "cloudflare")
			.build();
		
		return resSpec; 
	}
	
	
	public static ResponseSpecification responseSpec_401_AuthFail() {
		ResponseSpecification resSpec = new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(401)
			.expectHeader("server", "cloudflare")
			.build();
		
		return resSpec; 
	}
	
	
	
	@Test
	public void getUsersTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.when()
				.get("/public/v2/users")
			.then()
				.assertThat()
					.spec(responseSpec());
	
	}
	
	
	@Test
	public void getUsersAuthFailTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.given()
			.header("Authorization", "Bearer test")
			.when()
				.get("/public/v2/users")
			.then()
				.assertThat()
					.spec(responseSpec_401_AuthFail());
	
	}
	
	

}
