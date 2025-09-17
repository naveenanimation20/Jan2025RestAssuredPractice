package FakeUserAPI;

import org.testng.annotations.Test;

import FakeUserAPI.FakeUser.Address;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class FakeUserTest {
	
	@Test
	public void createAFakeUserTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
				
		Address.GeoLocation geoLocation = new Address.GeoLocation("-37.3159", "81.1496");
		
		Address address = new Address("Bangalore", "new road st", 9090, "12926-3874", geoLocation);
		
		FakeUser.Name name = new FakeUser.Name("Revathy", "Verma");
		
		FakeUser fakeUser = new FakeUser("revathy@gmail.com", "revathytest", "test@123", "1-570-236-7033", name, address);
		
		int userId =  given().log().all()
			.body(fakeUser)
			.when().log().all()
				.post("/users")
					.then()
						.extract().path("id");
	
		System.out.println(userId);
		
	}
	
	
	@Test
	public void createAFakeUserUsingBuilderTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		
		
		Address.GeoLocation geoLocation = new Address.GeoLocation.GeoLocationBuilder()
									.lat("-90.899")
									.longitude("987.90")
									.build();
		
		Address address = new Address.AddressBuilder()
					.street("new road st")
					.city("LA")
					.number(12345)
					.zipcode("1-570-236-7033")
					.geoLocation(geoLocation)
					.build();
		
		FakeUser.Name name = new FakeUser.Name.NameBuilder()
				.firstname("Ranjit")
				.lastname("Singh")
				.build();
		
		FakeUser fakeUser =	new FakeUser.FakeUserBuilder()
						.email("ranjit@open.com")
						.password("ranjit@123")
						.phone("1-570-236-7033")
						.username("ranjittesting")
						.address(address)
						.name(name)
						.build();
		
				
//		Address.GeoLocation geoLocation = new Address.GeoLocation("-37.3159", "81.1496");
//		
//		Address address = new Address("Bangalore", "new road st", 9090, "12926-3874", geoLocation);
//		
//		FakeUser.Name name = new FakeUser.Name("Revathy", "Verma");
//		
//		FakeUser fakeUser = new FakeUser("revathy@gmail.com", "revathytest", "test@123", "1-570-236-7033", name, address);
		
		int userId =  given().log().all()
			.body(fakeUser)
			.when().log().all()
				.post("/users")
					.then()
						.extract().path("id");
	
		System.out.println(userId);
		
	}
	


}
