package AuthAPIs;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.testng.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OAuth2APITest {
	
	private String accessToken;
	
	@BeforeMethod
	public void getAccessToken() {
		RestAssured.baseURI = "https://test.api.amadeus.com";
		
		Response response = RestAssured.given()
					.contentType(ContentType.URLENC)
					.formParam("grant_type", "client_credentials")
					.formParam("client_id", "47Ae5NUW3JK1AzmHO6AGdANAjAcSJ6D0")
					.formParam("client_secret", "KRBtzUdpBgSol6Kz")
					.when()
						.post("/v1/security/oauth2/token");
					
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		accessToken = response.jsonPath().getString("access_token");
		System.out.println("Access Token: "+ accessToken);
		
	}
	
	//if we oauth2 with header, we need to append it with Bearer. 
	@Test
	public void getFlightDetailsTest() {
		RestAssured.given().log().all()
				.header("Authorization", "Bearer " + accessToken)
				.when()
					.get("/v1/shopping/flight-destinations?origin=PAR&maxPrice=200")
					.then().log().all()
						.assertThat()
							.statusCode(200);
				
	}
	
	//if we use oauth2() directly, no need to append it with Bearer. 
	@Test
	public void getFlightDetailsWithOAuth2Test() {
		RestAssured.given().log().all()
				.auth()
					.oauth2(accessToken)
				.when()
					.get("/v1/shopping/flight-destinations?origin=PAR&maxPrice=200")
					.then().log().all()
						.assertThat()
							.statusCode(200);
				
	}
	
	
	@Test
    public void getFlightDetailsWithOAuth2WithResponseCheckTest() {
        // Assuming accessToken is defined elsewhere in the class or passed in
        // Perform the API request
        Response response = RestAssured.given().log().all()
                .auth()
                .oauth2(accessToken)
                .when()
                .get("/v1/shopping/flight-destinations?origin=PAR&maxPrice=200");
        
        response.prettyPrint();
        
        // Validate response status code
        assertEquals(200, response.getStatusCode(), "Response status code should be 200");
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Parse response to POJO
            FlightDestinationsResponse flightDestinationsResponse = 
                mapper.readValue(response.body().asString(), FlightDestinationsResponse.class);
            
            // Assert response is not null
            assertNotNull(flightDestinationsResponse, "Response should not be null");
            
            // Validate data array exists and is not empty
            assertNotNull(flightDestinationsResponse.getData(), "Data should not be null");
            assertFalse(flightDestinationsResponse.getData().isEmpty(), "Data should not be empty");
            
            // Validate the first flight destination
            FlightDestinationsResponse.FlightDestination firstDestination = flightDestinationsResponse.getData().get(0);
            assertNotNull(firstDestination, "First destination should not be null");
            assertEquals("flight-destination", firstDestination.getType(), "Type should be flight-destination");
            assertNotNull(firstDestination.getOrigin(), "Origin should not be null");
            assertNotNull(firstDestination.getDestination(), "Destination should not be null");
            assertNotNull(firstDestination.getDepartureDate(), "Departure date should not be null");
            assertNotNull(firstDestination.getReturnDate(), "Return date should not be null");
            
            // Validate price
            assertNotNull(firstDestination.getPrice(), "Price should not be null");
            assertNotNull(firstDestination.getPrice().getTotal(), "Total price should not be null");
            assertTrue(Double.parseDouble(firstDestination.getPrice().getTotal()) <= 200.0, 
                    "Price should be less than or equal to 200");
            
            // Validate links
            assertNotNull(firstDestination.getLinks(), "Links should not be null");
            assertNotNull(firstDestination.getLinks().getFlightDates(), "Flight dates link should not be null");
            assertNotNull(firstDestination.getLinks().getFlightOffers(), "Flight offers link should not be null");
            
            // Validate dictionaries
            assertNotNull(flightDestinationsResponse.getDictionaries(), "Dictionaries should not be null");
            assertNotNull(flightDestinationsResponse.getDictionaries().getCurrencies(), "Currencies should not be null");
            assertNotNull(flightDestinationsResponse.getDictionaries().getLocations(), "Locations should not be null");
            
            // Validate a specific location exists (assuming destination of first flight)
            String firstDestCode = firstDestination.getDestination();
            assertNotNull(flightDestinationsResponse.getDictionaries().getLocations().get(firstDestCode), 
                    "Dictionary should contain first destination");
            
            // Validate meta information
            assertNotNull(flightDestinationsResponse.getMeta(), "Meta should not be null");
            assertEquals("EUR", flightDestinationsResponse.getMeta().getCurrency(), "Currency should be EUR");
            assertNotNull(flightDestinationsResponse.getMeta().getLinks(), "Meta links should not be null");
            assertNotNull(flightDestinationsResponse.getMeta().getDefaults(), "Meta defaults should not be null");
            
            // Validate there is at least one entry that matches our sample data
            boolean foundMatchingDestination = flightDestinationsResponse.getData().stream()
                    .anyMatch(flight -> flight.getOrigin().equals("ORY") && 
                                       flight.getDestination().equals("MAD"));
            
            assertTrue(foundMatchingDestination, "Should find at least one Paris to Madrid flight");
            
            // Print summary of results
            System.out.println("Successfully validated flight destinations response with " + 
                               flightDestinationsResponse.getData().size() + " destinations");
            
        } catch (JsonMappingException e) {
            fail("JSON mapping exception occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            fail("JSON processing exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
	
	
	
	
	

}
