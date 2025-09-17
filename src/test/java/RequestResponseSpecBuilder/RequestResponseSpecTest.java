package RequestResponseSpecBuilder;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseSpecTest {
	
	
	public static RequestSpecification userReqSpec() {
		RequestSpecification reqSpec = new RequestSpecBuilder()
			.setBaseUri("https://gorest.co.in")
			.setContentType(ContentType.JSON)
			.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.build();
		
		return reqSpec;
	}
	
	public static ResponseSpecification responseSpec() {
		ResponseSpecification resSpec = new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("server", "cloudflare")
			.build();
		
		return resSpec; 
	}
	
	
	@Test
	public void getUsersTest() {
		
		RestAssured.given()
					.spec(userReqSpec())
						.when()
							.get("/public/v2/users")
								.then()
									.assertThat()
										.spec(responseSpec());
	}
	
	
	
	
	

}
