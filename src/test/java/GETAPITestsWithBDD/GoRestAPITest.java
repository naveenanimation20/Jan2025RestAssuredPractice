package GETAPITestsWithBDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import java.util.List;


public class GoRestAPITest {
	
	
	@Test
	public void getSingleUserTest() throws InterruptedException {
				
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response =  given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users/7817908");
		
		
						
		System.out.println("status code: " + response.statusCode());
		System.out.println("status line: " + response.statusLine());
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertTrue(response.statusLine().contains("200 OK"));
		
		response.prettyPrint();
		
		//fetch the json response body:
		
		JsonPath js = response.jsonPath();
		
		int userId = js.getInt("id");
		System.out.println("user id : " + userId);
		Assert.assertEquals(userId, 7817908);
		
		String name = js.getString("name");
		System.out.println("user name : "+ name);
		Assert.assertEquals(name, "Bhargavi Bharadwaj VM");
		
		String status = js.getString("status");
		System.out.println("user status : "+ status);
		Assert.assertEquals(status, "active");
		
		
	}
	
	
	
	@Test
	public void getAllUsersTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response =  given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users");
		
		
						
		System.out.println("status code: " + response.statusCode());
		System.out.println("status line: " + response.statusLine());
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertTrue(response.statusLine().contains("200 OK"));
		
		response.prettyPrint();
		
		
		JsonPath js = response.jsonPath();
		
		List<Integer> idList = js.getList("id");
		System.out.println(idList);
		
		List<String> nameList = js.getList("name");
		System.out.println(nameList);
		
	}
	
	
	
	
	

}
