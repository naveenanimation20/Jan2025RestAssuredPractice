package GETAPITestsWithBDD;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;


public class GetUserPostWithPathParam {
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{7820539, "Vobis absconditus libero qui aequitas."},
			{7820530, "Harum vehemens consectetur sulum ater cumque."},
			{7820520, "Saepe tabula varietas tamdiu et recusandae subvenio virgo."}
		};
	}
	
	
	@Test(dataProvider = "getUserData")
	public void getUserPostWithPathParamTest(int userId, String title) {
		
		RestAssured.baseURI = "https://gorest.co.in";

		given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.pathParam("userId", userId)
		.when()
			.get("/public/v2/users/{userId}/posts")
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.and()
				.body("title", hasItem(title));
	}
	
	
	
	@Test()
	public void getUserWithPathParamUsingHashMapTest() {
		
		RestAssured.baseURI = "https://reqres.in";
		
//		//https://reqres.in
//		/api
//		/users
//		?page=2
		
		Map<String, String> pathParamMap = new HashMap<String, String>();
		pathParamMap.put("firstpath", "api");
		pathParamMap.put("secondpath", "users");


		given().log().all()
			.pathParams(pathParamMap)
			.queryParam("page", 2)
		.when()
			.get("/{firstpath}/{secondpath}")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	
	
}
