package JaywayJsonPathConcept;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FakeProductAPITest {
	
	
	@Test
	public void getProductAPITest_JsonPath() {

		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().when().get("/products");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
//
		ReadContext ctx = JsonPath.parse(jsonResponse);
//
		List<Number> prices = ctx.read("$[?(@.price > 50)].price");
		System.out.println(prices);

		List<Integer> ids = ctx.read("$[?(@.price > 50)].id");
		System.out.println(ids);

		List<Double> rates = ctx.read("$[?(@.price > 50)].rating.rate");
		System.out.println(rates);

		List<Integer> counts = ctx.read("$[?(@.price > 50)].rating.count");
		System.out.println(counts);
//		
		//using contains:
		List<Integer> singleId = ctx.read("$[?(@.title =~ /.*Backpack.*/i)].id");
		System.out.println(singleId);
		
		//using startswith:
		List<Integer> startsWith = ctx.read("$[?(@.title =~ /^Fjallraven.*/i)].id");
		System.out.println("Starts with Fjallraven: " + startsWith);

		//using endswith:
		List<Integer> endsWith = ctx.read("$[?(@.title =~ /.*Laptops$/i)].id");
		System.out.println("Ends with Laptops: " + endsWith);
		
	}
	
		
	
	
	@Test
	public void getProductAPIJsonTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = RestAssured.given().when().get("/products");
		
		String jsonResponse = response.getBody().asString();
		
		ReadContext ctx = JsonPath.parse(jsonResponse);
		
		//single attribute:
		List<Integer> ids = ctx.read("$.[*].id");
		System.out.println(ids.size());
		System.out.println(ids);
		
		//two attributes:
		List<Map<String, Object>> twoAttrList = ctx.read("$.[*].['id', 'title']");
		System.out.println(twoAttrList);
		System.out.println(twoAttrList.size());
		
		for(Map<String, Object> e : twoAttrList) {
			int id = (Integer)e.get("id");
			String title = (String)e.get("title");
			System.out.println("ID :" + id);
			System.out.println("Title: "+ title);
			System.out.println("--------------");
		}
		
		System.out.println("==============");
		
		//three attributes:
		List<Map<String, Object>> threeAttrList = ctx.read("$.[*].['id', 'title', 'category']");
		System.out.println(threeAttrList);
		System.out.println(threeAttrList.size());
		
		for(Map<String, Object> e : threeAttrList) {
			int id = (Integer)e.get("id");
			String title = (String)e.get("title");
			String category = (String)e.get("category");

			System.out.println("ID :" + id);
			System.out.println("Title: "+ title);
			System.out.println("Category: "+ category);

			System.out.println("--------------");
		}
		
		System.out.println("==============");
		
		//fetch rating.rate:
		//$.[*].rating.rate
		
		List<Number> rateList = ctx.read("$.[*].rating.rate");
		System.out.println(rateList.size());
		System.out.println(rateList);
		
		for(Number d : rateList) {
			System.out.println(d);
		}
		
		System.out.println("==============");
		
		//
//		$[?(@.category == 'jewelery' )].id
//		$[?(@.category == 'jewelery' )].title
//		$[?(@.category == 'jewelery' )].['id', 'title']
//		$[?(@.category == 'jewelery' )].['id', 'title', 'price']
//		$[?(@.category == 'jewelery' )].rating.rate		
				
		List<Integer> jewlIds = ctx.read("$[?(@.category == 'jewelery' )].id");
		System.out.println(jewlIds.size());
		System.out.println(jewlIds);
		
		for(Integer e : jewlIds) {
			System.out.println(e);
		}
		
		System.out.println("==============");

		List<Map<String, Object>> jewlIDTitleList = ctx.read("$[?(@.category == 'jewelery' )].['id', 'title']");
		System.out.println(jewlIDTitleList);
		System.out.println(jewlIDTitleList.size());
		
		for(Map<String, Object> e : jewlIDTitleList) {
			int id = (Integer)e.get("id");
			String title = (String)e.get("title");
			System.out.println("ID :" + id);
			System.out.println("Title: "+ title);
			System.out.println("--------------");
		}
		
		System.out.println("==============");

		
		List<Number> jewlRateList = ctx.read("$[?(@.category == 'jewelery' )].rating.rate");
		System.out.println(jewlRateList.size());
		System.out.println(jewlRateList);
		
		for(Number d : jewlRateList) {
			System.out.println(d);
		}
		
		System.out.println("==============");
		
		//$[? ((@.category == 'jewelery') && (@.price < 10)) ].id --> list of Integer
		//$[? ((@.category == 'jewelery') && (@.price < 10)) ].['id','title'] --> list of map
		//$[? ((@.category == 'jewelery') && (@.price < 10) && (@.rating.rate == 3)) ].['id']
		
		//contains
		//starts with
		//ends with
		//functions
		
		
	}
	
	
	
	

}
