package CreateUserWithPOJOAndLombok;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreateUserTestWithPOJO {
	
	public String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User("Tom", getRandomEmailId(), "male", "active");
		
		//1. post -- create a user using pojo to json (serialization) using jackson(databind) lib
		Integer userId = given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.contentType(ContentType.JSON)
			.body(user)
		.when()
			.post("/public/v2/users")
		.then().log().all()
			.statusCode(201)
			.extract()
			.path("id");
System.out.println("user id : "+ userId);

System.out.println("-----------------");

//2. get a user -- GET:

given().log().all()
.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
.when()
.get("/public/v2/users/"+userId)
.then().log().all()
	.assertThat()
		.statusCode(200)
		.and()
			.body("id", equalTo(userId));
			
		
	}
	
	
	
//	@Test
//	public void addUserWithRecordTest() {
//		RestAssured.baseURI = "https://gorest.co.in";
//		
//		UserRecord user = new UserRecord(null, "John Doe", "johnae@example.com", "male", "active");
//		
//		//1. post -- create a user using pojo to json (serialization) using jackson(databind) lib
//		Response response = given().log().all()
//			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
//			.contentType(ContentType.JSON)
//			.body(user)
//		.when()
//			.post("/public/v2/users");
//		
//
//System.out.println("-----------------");
//
////Deserialization
//ObjectMapper mapper = new ObjectMapper();
//try {
//	UserRecord userRes = mapper.readValue(response.asString(), UserRecord.class);
//	System.out.println(userRes);
//} catch (JsonProcessingException e) {
//	e.printStackTrace();
//}
//			
//		
//	}
	

	

}
