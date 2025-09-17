package DesrializationWithJSONArrayResponse;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllProductsTest {

	@Test
	public void getAllProductsAPITest() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given()
				.when()
				.get("/products");

		response.prettyPrint();

		// Deserilization: json array to pojo
		ObjectMapper mapper = new ObjectMapper();
		try {
			Product[] product = mapper.readValue(response.getBody().asString(), Product[].class);

			for (Product p : product) {
				System.out.println("id: " + p.getId());
				System.out.println("title: " + p.getTitle());
				System.out.println("price: " + p.getPrice());
				System.out.println("description: " + p.getDescription());
				System.out.println("image: " + p.getImage());
				System.out.println("category: " + p.getCategory());


				System.out.println("rate: " + p.getRating().getRate());
				System.out.println("count: " + p.getRating().getCount());

			}

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
