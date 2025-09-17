package XMLPathQuery;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathTest {
	
	@Test
	public void MRDataXmlTest() {
		
		Response response = RestAssured.given()
				.when()
					.get("http://ergast.com/api/f1/2017/circuits.xml");
		
		
		String responseBody = response.body().asString();
		
		XmlPath xmlPath = new XmlPath(responseBody);
		
		List<String> circuitNames = xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");
		System.out.println("total circuits: "+ circuitNames.size());
		for(String e : circuitNames) {
			System.out.println(e);
		}
		
		System.out.println("---------------");

		//fetch the locality where circuitId="americas"
		
		//find -- for single element
		//findAll - for multiple elements
		//it -- iterator
		
		String locality = xmlPath.getString("**.find{ it.@circuitId == 'americas' }.Location.Locality");
		System.out.println("locality: "+ locality);
		
		String country = xmlPath.getString("**.find{ it.@circuitId == 'americas' }.Location.Country");
		System.out.println("country: "+ country);
		
		System.out.println("-----------");
		
		List<String> localityList = xmlPath.getList("**.findAll{ it.@circuitId == 'americas' }.Location.Locality");
		System.out.println("locality: "+ localityList);
		
		List<String> countryList = xmlPath.getList("**.findAll{ it.@circuitId == 'americas' }.Location.Country");
		System.out.println("country: "+ countryList);

		System.out.println("--------------");
		//fetch the locality where circuitId="americas" or circuitId="bahrain" ===> Austin, Sakhir
		
		List<String> multipleLocalityList = xmlPath.getList("**.findAll{ it.@circuitId == 'americas' || it.@circuitId == 'bahrain' }.Location.Locality");
		System.out.println("country: "+ multipleLocalityList);

		System.out.println("--------------");

		List<String> circuitIds = xmlPath.getList("MRData.CircuitTable.Circuit.@circuitId");
		System.out.println("total circuitsids: "+ circuitIds.size());
		for(String e : circuitIds) {
			System.out.println(e);
		}
		
		System.out.println("---------------");

		//fetch the lat value where circuitId = 'bahrain'
		//fetch the lalongt value where circuitId = 'bahrain'
		
		String latValue = xmlPath.getString("**.find{ it.@circuitId == 'bahrain' }.Location.@lat");
		System.out.println("lat: "+ latValue);

		String longValue = xmlPath.getString("**.find{ it.@circuitId == 'bahrain' }.Location.@long");
		System.out.println("long: "+ longValue);

		System.out.println("---------------");

		String urlValue = xmlPath.getString("**.find{ it.@circuitId == 'americas' }.@url");
		System.out.println("url: "+ urlValue);

		//fetch all the urls from the circuits: findAll -- getList		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
