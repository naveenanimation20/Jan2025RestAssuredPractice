package DesrializationWithJSONArrayResponse;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GetAllUsersTest {

	
	@Test
	public void getAllUsersTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given()
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when()
			.get("/public/v2/users");
		
		response.prettyPrint();
		
		//Deserilization: json to pojo
		
		ObjectMapper mapper =  new ObjectMapper();
		try {
			//json arrays as response ---> User[].class
			User[] userRes = mapper.readValue(response.getBody().asString(), User[].class);
			
			for(User user : userRes) {
				System.out.println("id: "+ user.getId());
				System.out.println("name: "+ user.getName());
				System.out.println("email: "+ user.getEmail());
				System.out.println("gender: "+ user.getGender());
				System.out.println("status: "+ user.getStatus());
			}
			
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
