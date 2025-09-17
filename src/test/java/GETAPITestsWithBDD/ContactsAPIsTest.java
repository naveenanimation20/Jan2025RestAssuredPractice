package GETAPITestsWithBDD;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class ContactsAPIsTest {
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

	}
	
	
	@Test(priority = 1)
	public void getContactsAPITest() {
		
		
		given().log().all()
			.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDg3MWE2NmY2ZDEzYzAwMTM3Y2IzMWEiLCJpYXQiOjE3NDQwMzgzMjR9.akCpS_ueV3Uo8GpDFMR8gKeKn-YBorVhuQtXgxxxGv0")
				.when()
					.get("/contacts")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON);
									
			
	}
	
	@Test(priority = 2)
	public void getContactsAPIAuthErrorTest() {
		
		
		given().log().all()
			.header("Authorization", "Bearer -naveen")
				.when()
					.get("/contacts")
						.then().log().all()
							.assertThat()
								.statusCode(401);
									
									
			
	}
	
	
	@Test(priority = 3)
	public void getContactsAPIInvalidTokenTest() {
		
		
		String errorMessg = given().log().all()
									.header("Authorization", "Bearer -testing")
											.when()
													.get("/contacts")
															.then()
																	.extract()
																			.path("error");
							
			System.out.println(errorMessg);
			Assert.assertEquals(errorMessg, "Please authenticate.");
			
	}
	
	

}
