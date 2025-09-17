package RequestResponseSpecBuilder;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {
	
	public static RequestSpecification userReqSpec() {
		RequestSpecification reqSpec = new RequestSpecBuilder()
			.setBaseUri("https://gorest.co.in")
			.setContentType(ContentType.JSON)
			.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.build();
		
		return reqSpec;
	}
	
	
	@Test
	public void getUsersTest() {
		RestAssured.given()
					.spec(userReqSpec())	
					.when()
						.get("/public/v2/users")
					.then()
						.assertThat().statusCode(200);
					
	}
	
	
	@Test
	public void getUsersWithQueryParamTest() {
		RestAssured.given()
					.queryParam("name", "naveen")
					.queryParam("status", "active")
					.spec(userReqSpec())	
					.when()
						.get("/public/v2/users")
					.then()
						.assertThat().statusCode(200);
					
	}
	
	
	
	

}
