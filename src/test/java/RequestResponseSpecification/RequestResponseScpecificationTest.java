package RequestResponseSpecification;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class RequestResponseScpecificationTest {
	
	RequestSpecification reqSpec, reqSpec_InvalidAuth;
	ResponseSpecification resSpec, resSpec_401;
	
	@BeforeTest
	public void setup() {
		reqSpec = given().log().all()
		.baseUri("https://gorest.co.in")
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.header("Content-Type", "application/json");
		
		reqSpec_InvalidAuth = given().log().all()
				.baseUri("https://gorest.co.in")
				.header("Authorization", "Bearer test");
		
		resSpec = expect()
			.statusCode(anyOf(equalTo(200), equalTo(201)))
			.header("Content-Type", "application/json; charset=utf-8")
			.header("Server", "cloudflare")
			.time(lessThan(2000L));
		
		
		resSpec_401 = expect()
				.statusCode(401)
				.header("Content-Type", "application/json; charset=utf-8")
				.header("Server", "cloudflare")
				.time(lessThan(2000L));
		
		
	}

	@Test
	public void getUsersTest() {
		reqSpec.get("/public/v2/users")
				.then()
				.spec(resSpec);
	}
	
	@Test
	public void getUsersWithQueryParamTest() {
		reqSpec
			.queryParam("name", "naveen")
			.queryParam("status", "active")
		.get("/public/v2/users")
				.then()
				.spec(resSpec);
	}
	
	@Test
	public void getUsersTest_401() {
		reqSpec_InvalidAuth
		.get("/public/v2/users")
				.then()
				.spec(resSpec_401);
	}
	
	
	@Test
	public void createUserTest() {
		reqSpec
			.body("{\n"
					+ "\"name\": \"Diksha Johar CPA\",\n"
					+ "\"email\": \"cpa_johar_dikshatesting@marvin.test\",\n"
					+ "\"gender\": \"female\",\n"
					+ "\"status\": \"inactive\"\n"
					+ "}")
		
		.post("/public/v2/users")
				.then().log().all()
				.spec(resSpec);
	}
	
	
	

}
