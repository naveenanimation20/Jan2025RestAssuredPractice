package BookingAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class BookingAPITest {
	
	String tokenID;
	int newBookingId;
	
	@BeforeTest
	public void getToken() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Credentials creds = new Credentials("admin", "password123");
		 tokenID = given().log().all()
			.contentType(ContentType.JSON)
			.body(creds)
			.when()
			.post("/auth")
			.then().log().all()
			.assertThat().statusCode(200)
				.extract().path("token");
		System.out.println("token id: "+ tokenID);
	}
	
	@BeforeMethod
	public void setup() {
		newBookingId = createBooking();
	}
	
	
	
	@Test
	public void getBookingTest() {		
		given().log().all()
			.pathParam("bookingId", newBookingId)
			.when()
				.get("/booking/{bookingId}")
				.then()
					.assertThat()
					.statusCode(200)
					.and()
					.body("firstname", equalTo("Jim"))
					.and()
					.body("bookingdates.checkin", equalTo("2018-01-01"));

	}
	
	
	@Test
	public void createBookingTest() {
		Assert.assertNotNull(newBookingId);
	}
	
	@Test
	public void updateBookingTest() {
		given().log().all()
			.pathParam("bookingId", newBookingId)
			.contentType(ContentType.JSON)
			.header("Accept", "application/json")
			.header("Cookie", "token="+tokenID)
			.body("{\n"
					+ "    \"firstname\" : \"Naveen\",\n"
					+ "    \"lastname\" : \"Brown\",\n"
					+ "    \"totalprice\" : 111,\n"
					+ "    \"depositpaid\" : true,\n"
					+ "    \"bookingdates\" : {\n"
					+ "        \"checkin\" : \"2018-01-01\",\n"
					+ "        \"checkout\" : \"2019-01-01\"\n"
					+ "    },\n"
					+ "    \"additionalneeds\" : \"Lunch\"\n"
					+ "}")
			.when()
				.put("/booking/{bookingId}")
				.then().log().all()
				.assertThat()
					.statusCode(200)
						.and()
						.body("firstname", equalTo("Naveen"))
						.and()
						.body("additionalneeds", equalTo("Lunch"));
	}
	
	
	@Test
	public void deleteBookingTest() {
		given().log().all()
			.pathParam("bookingId", newBookingId)
			.contentType(ContentType.JSON)
			.header("Cookie", "token="+tokenID)
			.when()
				.delete("/booking/{bookingId}")
				.then().log().all()
				.assertThat()
					.statusCode(201);
						
	}
	
	
	
	public int createBooking() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		int bookingID = given().log().all()
			.contentType(ContentType.JSON)
			.body("{\n"
					+ "    \"firstname\" : \"Jim\",\n"
					+ "    \"lastname\" : \"Brown\",\n"
					+ "    \"totalprice\" : 111,\n"
					+ "    \"depositpaid\" : true,\n"
					+ "    \"bookingdates\" : {\n"
					+ "        \"checkin\" : \"2018-01-01\",\n"
					+ "        \"checkout\" : \"2019-01-01\"\n"
					+ "    },\n"
					+ "    \"additionalneeds\" : \"Breakfast\"\n"
					+ "}")
			.when()
				.post("/booking")
				.then().log().all()
				.assertThat().statusCode(200)
				.extract().path("bookingid");
		System.out.println("new booking id: "+ bookingID);
		return bookingID;
	}
	
	
}
