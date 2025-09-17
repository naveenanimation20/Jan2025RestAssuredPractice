package PostAPIWithDifferentBodyTypes;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class PostAPIWithDifferentBody {
	
	@Test
	public void bodyWithTextTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "hi this is naveen here";
		
		given().log().all()
			.contentType(ContentType.TEXT)
			.body(payload)
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
		
	}
	
	
	@Test
	public void bodyWithJavaScriptTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<script>\n"
				+ "document.getElementById(\"demo\").innerHTML = 10.50;\n"
				+ "</script>";
		
		given().log().all()
			.contentType("application/javascript;charset=utf-8")
			.body(payload)
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
		
	}
	
	
	@Test
	public void bodyWithHTMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<html>\n"
				+ "<body>\n"
				+ "<h2>JavaScript Numbers</h2>\n"
				+ "<p id=\"demo\"></p>\n"
				+ "</body>\n"
				+ "</html>";
		
		given().log().all()
			.contentType(ContentType.HTML)
			.body(payload)
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
		
	}
	
	
	
	@Test
	public void bodyWithXMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<dependency>\n"
				+ "			<groupId>io.rest-assured</groupId>\n"
				+ "			<artifactId>rest-assured</artifactId>\n"
				+ "			<version>5.5.1</version>\n"
				+ "		</dependency>";
		
		given().log().all()
			.contentType("application/xml;charset=utf-8")
			.body(payload)
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
		
	}
	
	
	@Test
	public void bodyWithMultiPartTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";		
		
		given().log().all()
			.contentType(ContentType.MULTIPART)
			.multiPart("resume", new File("/Users/naveenautomationlabs/Desktop/HTTPMethodsCheatSheet.pdf"))
			.multiPart("name", "naveen")
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
			
		
	}
	
	
	@Test
	public void bodyWithPDFFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";		
		
		given().log().all()
			.contentType("application/pdf")
			.body(new File("/Users/naveenautomationlabs/Desktop/hdfc.pdf"))
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	@Test
	public void bodyWithImageFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";		
		
		given().log().all()
			.contentType("image/jpeg")
			.body(new File("/Users/naveenautomationlabs/Desktop/1742103247293.jpeg"))
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
			
		
	}
	

}
