package XMLAPIs;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetAllUsersXMLAPITest {

	@Test
	public void getAllUsersTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		Response response = RestAssured.given().when().get("/public/v2/users.xml");

		String responseBody = response.getBody().asString();

		// create the object of XmlPath
		XmlPath xmlPath = new XmlPath(responseBody);

		// fetching the data:
		String objType = xmlPath.getString("objects.@type");
		System.out.println(objType);
		Assert.assertEquals(objType, "array");

		System.out.println("---------------");

		List<String> idTypeList = xmlPath.getList("objects.object.id.@type");
		System.out.println(idTypeList);
		for (String e : idTypeList) {
			System.out.println(e);
			Assert.assertEquals(e, "integer");
		}

		System.out.println("---------------");

		// fetch all the id values/text:
		List<Integer> idList = xmlPath.getList("objects.object.id");
		System.out.println(idList);

		List<String> nameList = xmlPath.getList("objects.object.name");
		System.out.println(nameList);

		for (String e : nameList) {
			System.out.println(e);
			Assert.assertNotNull(e);
		}

	}

	
	@Test
	public void getAllUserTestWithXml_Deserilization() {
		RestAssured.baseURI = "https://gorest.co.in";
		Response response = RestAssured.given().when().get("/public/v2/users.xml");

		String responseBody = response.getBody().asString();
		
		//xml--->pojo: deserilization:
		XmlMapper mapper = new XmlMapper();
		
		try {
			UserData userData = mapper.readValue(responseBody, UserData.class);
			
			System.out.println("id is: "+ userData.getObjects().get(0).getId().getValue());
			System.out.println("email is: "+ userData.getObjects().get(0).getEmail());
			System.out.println("gender is: "+ userData.getObjects().get(0).getGender());
			System.out.println("status is: "+ userData.getObjects().get(0).getStatus());
			System.out.println("name is: "+ userData.getObjects().get(0).getName());

			
			System.out.println("Objects type: "+ userData.getType());
			System.out.println("id type: "+ userData.getObjects().get(0).getId().getType());
			
			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
