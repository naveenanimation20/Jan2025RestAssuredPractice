package AuthAPIs;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OAuth2WithSpotifyAPI {
	
	
private String accessToken;
	
	@BeforeMethod
	public void getAccessToken() {
		RestAssured.baseURI = "https://accounts.spotify.com";
		
		Response response = RestAssured.given()
					.contentType(ContentType.URLENC)
					.formParam("grant_type", "client_credentials")
					.formParam("client_id", "62069924600648fda55b8881750d2306")
					.formParam("client_secret", "f2b13f9d1b11470c95347662eb561e71")
					.when()
						.post("/api/token");
					
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		accessToken = response.jsonPath().getString("access_token");
		System.out.println("Access Token: "+ accessToken);
		
	}
	
	
	//if we oauth2 with header, we need to append it with Bearer. 
		@Test
		public void getAlbumsTest() {
			RestAssured.baseURI = "https://api.spotify.com";
			Response response = RestAssured.given().log().all()
					.header("Authorization", "Bearer " + accessToken)
					.when()
						.get("/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");
			
			ReadContext ctx = JsonPath.parse(response.asString());
			List<Map<String, Object>> list = ctx.read("$.images.[?(@.height == 300)].['url', 'width']");
			System.out.println(list);
			System.out.println(list.size());
			
			//$.tracks.items[*].artists[*].name
			//$.tracks.items[*].name
					
		}
		
		//if we use oauth2() directly, no need to append it with Bearer. 
		@Test
		public void getFlightDetailsWithOAuth2Test() {
			RestAssured.baseURI = "https://api.spotify.com";
			RestAssured.given().log().all()
					.auth()
						.oauth2(accessToken)
					.when()
						.get("/v1/albums/4aawyAB9vmqN3uQ7FjRGTy")
						.then().log().all()
							.assertThat()
								.statusCode(200);
					
		}

}
