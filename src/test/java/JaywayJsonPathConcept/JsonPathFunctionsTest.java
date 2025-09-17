package JaywayJsonPathConcept;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathFunctionsTest {
	
	@Test
	public void getProductAPITest_JsonPath() {

		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
//
		ReadContext ctx = JsonPath.parse(jsonResponse);

		Double minPrice = ctx.read("min($[*].price)");
		System.out.println("min price: "+ minPrice);
		
		
		Double maxPrice = ctx.read("max($[*].price)");
		System.out.println("max price: "+ maxPrice);
		
		Double avgPrice = ctx.read("avg($[*].price)");
		System.out.println("avg price: "+ avgPrice);
		
		Integer length = ctx.read("length($)");
		System.out.println("Length: "+ length);
				
		Double sumPrice = ctx.read("sum($[*].price)");
		System.out.println("sum of prices: "+ sumPrice);
		
		
	}

}
