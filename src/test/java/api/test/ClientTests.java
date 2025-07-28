package api.test;
import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;

import api.payload.Client;
public class ClientTests {
	Client clientPayload;
	String id;
	
	@BeforeClass
	public void setUp() {
		clientPayload = new Client();
	}
}
