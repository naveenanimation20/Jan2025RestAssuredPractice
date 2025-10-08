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
	public void createAPetWithAssertionsTest() {
		
		RestAssured.baseURI = "https://petstore.swagger.io";
		
		//create the object of Pet class:
		
		Pet.Category category = new Pet.Category(101, "Dog");
		
		List<String> photoUrls =  Arrays.asList("https://dog1.com", "https://dog2.com", "https://dog3.com");
		
		Pet.Tag tag1 = new Pet.Tag(201, "Red");
		Pet.Tag tag2 = new Pet.Tag(202, "Black");
		Pet.Tag tag3 = new Pet.Tag(202, "White");

		
		List<Tag> tags = Arrays.asList(tag1, tag2, tag3);
		
		Pet pet = new Pet(1, "Tommy", "available", photoUrls, tags, category);
		
		Response response = given().log().all()
			.contentType(ContentType.JSON)
			.body(pet) //serialization : 
		.when()
			.post("/v2/pet");
		
		JsonPath js = response.jsonPath();
		
		Assert.assertEquals(js.getInt("id"), pet.getId());
		Assert.assertEquals(js.getInt("category.id"), category.getId());
		Assert.assertEquals(js.getString("name"), pet.getName());
		Assert.assertEquals(js.getString("status"), pet.getStatus());
		Assert.assertEquals(js.getList("photoUrls"), pet.getPhotoUrls());
//		Assert.assertEquals(js.getInt("tags[0].id"), pet.getTags().get(0).getId());
//		Assert.assertEquals(js.getInt("tags[1].id"), pet.getTags().get(1).getId());
//		Assert.assertEquals(js.getInt("tags[2].id"), pet.getTags().get(2).getId());
		
		for(int i=0; i<tags.size(); i++) {
			Assert.assertEquals(js.getInt("tags["+i+"].id"), pet.getTags().get(i).getId());
		}

		for(int i=0; i<tags.size(); i++) {
			Assert.assertEquals(js.getString("tags["+i+"].name"), pet.getTags().get(i).getName());
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
