package RequestResponseSpecification;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RequestSpecReuseTest {
	
	RequestSpecification requestSpec;
	
	@BeforeMethod
	public void setup() {
		 requestSpec = RestAssured.given().log().all()
				.baseUri("https://gorest.co.in/")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
	
	}
	
	@Test
	public void getUserTest() {
		requestSpec.when()
						.get("/public/v2/users")
							.then()
								.assertThat().statusCode(200);
	}
	
	@Test
	public void getSingleUserTest() {
		requestSpec.when()
						.get("/public/v2/users/7876458")
							.then()
								.assertThat().statusCode(200);
	}
	
	@Test
	public void getInvalidUserTest_404() {
		requestSpec.when()
						.get("/public/v2/users/9090")
							.then()
								.assertThat().statusCode(404);
	}
	
	
	
	

}
