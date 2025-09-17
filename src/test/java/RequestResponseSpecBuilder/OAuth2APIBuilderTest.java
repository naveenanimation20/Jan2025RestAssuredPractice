package RequestResponseSpecBuilder;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth2APIBuilderTest {
	
	
	public static RequestSpecification oAuth2ReqSepc() {
		return new RequestSpecBuilder()
				.setBaseUri("https://test.api.amadeus.com")
				.setContentType(ContentType.URLENC)
				.addFormParam("grant_type", "client_credentials")
				.addFormParam("client_id", "47Ae5NUW3JK1AzmHO6AGdANAjAcSJ6D0")
				.addFormParam("client_secret", "KRBtzUdpBgSol6Kz")
				.build(); 

	}
	
	public void getToken() {
		//return token;
	}
	
	
	@Test
	public void getAccessTokenTest() {
		Response response = RestAssured.given()
					.spec(oAuth2ReqSepc())
					.when()
						.post("/v1/security/oauth2/token");
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		String accessToken = response.jsonPath().getString("access_token");
		System.out.println("Access Token: "+ accessToken);
		Assert.assertNotNull(accessToken);
	}
	
	
	
	
	
	
	
	}
