package CreateUserWithPOJOAndLombok;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateAUserWithLombokTest {
	
	
	public String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	@Test
	public void addUserWithLombokTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		UserLombok user = new UserLombok("seema", getRandomEmailId(), "female", "active");
		
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
	
	
	@Test
	public void addUserWithLombokBuilderTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//builder pattern using lombok:
		UserLombok user = new UserLombok.UserLombokBuilder()
						.name("Seema")
						.email(getRandomEmailId())
						.status("active")
						.gender("female")
						.build();
		
		System.out.println(user);
		
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
	
	
	@Test
	public void addUserWithLombokBuilderWithGetterTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//builder pattern using lombok:
		UserLombok user = new UserLombok.UserLombokBuilder()
						.name("Seema")
						.email(getRandomEmailId())
						.status("active")
						.gender("female")
						.build();
		
		System.out.println(user);
		
		//1. post -- create a user using pojo to json (serialization) using jackson(databind) lib
				Response response = given().log().all()
					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.contentType(ContentType.JSON)
					.body(user)
				.when()
					.post("/public/v2/users");
				
				
				JsonPath js = response.jsonPath();
				
		Assert.assertEquals(js.getString("name"), user.getName());
		Assert.assertEquals(js.getString("gender"), user.getGender());
		Assert.assertEquals(js.getString("email"), user.getEmail());
		Assert.assertEquals(js.getString("status"), user.getStatus());
		Assert.assertNotNull(js.getString("id"));
		
	
	}
	
	
	
	
	
	

}
