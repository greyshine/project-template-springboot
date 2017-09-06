package de.greyshine.springboottemplate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestTester {

	static final String SERVER_HTTP = "http://localhost:8080";
	static final String SERVER_HTTPS = "https://localhost:8443";
	static final String SERVER = SERVER_HTTP;

	static {

		RestAssured.config = RestAssured.config().sslConfig( new SSLConfig().relaxedHTTPSValidation() );
		RestAssured.baseURI = SERVER;
	}
	
	static RequestSpecification initWithCredentials(String inUser, String inPassword) {
		// https://www.w3.org/Protocols/HTTP/HTRQ_Headers.html#z9
		return given().header("Authorization", "user "+ inUser +":"+ inPassword);
	}

	/**
	 * invoke when server is running
	 */
	@Test
	public void testMultipartUpload() {

		Assert.assertTrue(new File("src/test/resources/helloworld.txt").isFile());

		final Response r = given().//
				formParam("field", "value1").//
				formParam("fields", "value2a", "value2b").//
				multiPart("file", new File("src/test/resources/helloworld.txt")).when()//
				.post("/mp-form-submit");

		r.then().assertThat()//
				.statusCode(200)//
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
				.body("field", equalTo("value1"))//
				.body("fields", equalTo("[value2a, value2b]"))//
				.body("file", equalTo("helloworld"));

		System.out.println(r);
		System.out.println(r.getStatusCode());
		System.out.println(r.getContentType());
		System.out.println( r.asString() );
		
	}

	@Test
	public void testSomeModel() {

		final Response r = given().//
				formParam("value", "someValue").//
				when()//
				.post("/form-submit-model");

		System.out.println(r.asString());
	}
	
	@Test
	public void testEntiyPostWithCredentialsSimple() {
		
		String theJson = "{\"hello\":\"world\"}";
		
		final Response r = initWithCredentials("hello","world")//
				.contentType( ContentType.JSON )
				.body( theJson.getBytes() )
				.post("/secured/entity-post-simple");

		System.out.println( r.asString() );
				
		r.then().assertThat().statusCode( 200 );
		
	}

	@Test
	public void testEntiyPostWithCredentialsModel() {
		
		String theJson = "{\"value\":\"someValue\"}";
		
		final Response r = initWithCredentials("hello","world")//
				.contentType( ContentType.JSON )
				.body( theJson.getBytes() )
				.post("/secured/entity-post-model");
		
		System.out.println( r.asString() );
		
		r.then().assertThat().statusCode( 200 );
		
	}
	
	@Test
	public void requestJmsSuccess() {
		
		final Response r = given()//
				.contentType( ContentType.JSON )
				.get("/jms/simple-success");
		
		r.then().assertThat().statusCode( 200 );
	}

}
