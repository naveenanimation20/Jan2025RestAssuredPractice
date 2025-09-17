package XMLPathQuery;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class UserResponseWithXmlPathQuery {

	@Test
	public void getUsersXMLTest() {

		Response response = RestAssured.given().when().get("https://gorest.co.in/public/v2/users.xml");

		String responseBody = response.body().asString();

		XmlPath xmlPath = new XmlPath(responseBody);

		// Fetching the single id

		// String singleId = xmlPath.getString("objects.object.id");

		String firstId = xmlPath.getString("objects.object[0].id");
		System.out.println("First ID: " + firstId);

		System.out.println("------------------------------------------------");
		
		String id = xmlPath.getString("objects.object.find { it.name == 'Prasanna Tagore' }.id");
		System.out.println("ID: " + id);


	}

}
