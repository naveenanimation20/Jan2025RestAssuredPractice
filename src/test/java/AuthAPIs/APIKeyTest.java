package AuthAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APIKeyTest {
	
	@Test
	public void getAlbumsTest() {
		RestAssured.baseURI = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=AIzaSyBR7n4Pe7IceyuQDSkmcmNiTjU-xJgieQI";
		
		RestAssured.given().log().all()
				.queryParam("key", "AIzaSyBR7n4Pe7IceyuQDSkmcmNiTjU-xJgieQI")
				.contentType(ContentType.JSON)
				.body("{\n"
						+ "    \"contents\": [\n"
						+ "        {\n"
						+ "            \"parts\": [\n"
						+ "                {\n"
						+ "                    \"text\": \"Explain how xpath works in selenium\"\n"
						+ "                }\n"
						+ "            ]\n"
						+ "        }\n"
						+ "    ]\n"
						+ "}")
				
				.when().urlEncodingEnabled(false)
					.post()
					.then().log().all()
					.assertThat()
					.statusCode(200);
					
	}
	

}
