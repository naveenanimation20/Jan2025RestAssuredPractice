package DeleteCallWithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import CreateUserWithPOJOAndLombok.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteUSerTest {
	
	public String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@opencart.com";
	}

	@Test
	public void updateUserTest() {

		// 1. post

		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("Seema", getRandomEmailId(), "female", "inactive");

		// 1. post -- create a user using pojo to json (serialization) using
		// jackson(databind) lib
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
		System.out.println("user id : " + userId);

		System.out.println("-----------------");


		// 2. get
		given().log().all()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
				.get("/public/v2/users/" + userId)
				.then().log().all()
				.assertThat()
				.statusCode(200).and()
				.body("id", equalTo(userId));
		
		System.out.println("-----------------");

		
		//3. delete:
		given().log().all()
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when()
			.delete("/public/v2/users/"+userId)
			.then().log().all()
			.assertThat()
			.statusCode(204);
			
		
		System.out.println("-----------------");

		
		// 4. get
				given().log().all()
						.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
						.when()
						.get("/public/v2/users/" + userId)
						.then().log().all()
						.assertThat()
						.statusCode(404)
						.and()
						.body("message", equalTo("Resource not found"));

	}

}
