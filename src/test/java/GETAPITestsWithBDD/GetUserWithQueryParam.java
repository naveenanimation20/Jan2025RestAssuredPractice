package GETAPITestsWithBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;


public class GetUserWithQueryParam {
	
	@Test
	public void getUsersTestWithQueryParam() {
				
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.queryParam("name", "naveen")
			.queryParam("status", "inactive")
				.when()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON);
	}
	
	
	@Test
	public void getUsersTestWithQueryParamUsingHashMap() {
				
		RestAssured.baseURI = "https://gorest.co.in";
		
		Map<String,String> userQueryMap = new HashMap<String,String>();
		userQueryMap.put("name", "naveen");
		userQueryMap.put("status", "active");
		userQueryMap.put("gender", "male");

		
		given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.queryParams(userQueryMap)
				.when()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON);
	}

}
