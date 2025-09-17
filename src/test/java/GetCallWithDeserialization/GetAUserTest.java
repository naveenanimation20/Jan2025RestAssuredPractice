package GetCallWithDeserialization;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAUserTest {
	
	
	@Test
	public void getSingleUserTest() throws InterruptedException {
				
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response =  given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users/7834409");
		
		response.prettyPrint();
		
		
		//deserialization:		
		ObjectMapper mapper = new ObjectMapper();
		try {
			//User userRes = mapper.readValue(response.getBody().asString(), User.class);
			
			UserLombok userRes = mapper.readValue(response.getBody().asString(), UserLombok.class);

			System.out.println(userRes);
			
			Assert.assertEquals(userRes.getName(), "Trilokesh Varrier");
			Assert.assertEquals(userRes.getEmail(), "varrier_trilokesh@jacobi.test");
			Assert.assertEquals(userRes.getStatus(), "inactive");
			Assert.assertEquals(userRes.getId(), 7834409);

			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	

}
