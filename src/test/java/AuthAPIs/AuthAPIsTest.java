package AuthAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class AuthAPIsTest {
	
	//basic
	//digest
	//api key
	//Oauth1
	//Oauth2
	//JWT
	//Bearer token
	
	@Test
	public void basicAuthAPITest() {
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
		RestAssured.given().log().all()
					.auth()
						.basic("admin", "admin")
					.when()
						.get("/basic_auth")
							.then().log().all()
								.assertThat().statusCode(200);
	}
	
	
	@Test
	public void digestAuthAPITest() {
		RestAssured.baseURI = "https://postman-echo.com";
		
		RestAssured.given().log().all()
					.auth()
						.digest("postman", "password")
					.when()
						.get("/digest-auth")
							.then().log().all()
								.assertThat().statusCode(200);
	}
	
	@Test
	public void preemptiveAuthAPITest() {
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
		RestAssured.given().log().all()
					.auth()
						.preemptive().basic("admin", "admin")
					.when()
						.get("/basic_auth")
							.then().log().all()
								.assertThat().statusCode(200);
	}
	
	
	
	
	
	

}
