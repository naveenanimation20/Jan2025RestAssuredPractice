package XMLAPIs;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FormulaOneAPITest {
	
	
	@Test
	public void getFormulaOneTest() {
		
		
		RestAssured.baseURI = "http://ergast.com";
		
		Response response = RestAssured.given()
						.when()
							.get("/api/f1/2017/circuits.xml");
		
		String responseData = response.getBody().asString();
		
		//xml---pojo: deserilization:
		XmlMapper mapper = new XmlMapper();
		
		try {
			MRData mrData = mapper.readValue(responseData, MRData.class);
			
			System.out.println(mrData.getSeries());
			System.out.println(mrData.getUrl());
			System.out.println(mrData.getOffset());
			System.out.println(mrData.getTotal());
			System.out.println(mrData.getLimit());
			
			String season = mrData.getCircuitTable().getSeason();
			System.out.println("season :" + season );
			
			System.out.println(mrData.getCircuitTable().getCircuits().get(0).getCircuitId());
			
			System.out.println(mrData.getCircuitTable().getCircuits().get(0).getCircuitName());
			
			System.out.println(mrData.getCircuitTable().getCircuits().get(0).getLocation().getCountry());

			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
