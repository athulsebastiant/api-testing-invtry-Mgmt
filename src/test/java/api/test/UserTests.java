package api.test;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	User userPayload;
	public static String token;
	
	@BeforeClass
	public void setUp() {
		userPayload = new User();
		userPayload.setUsername("TestUser101");
		userPayload.setPassword("password123");
		userPayload.setEmail("testuser101@gmail.com");
	}
	
	@Test(priority = 1)
	public void testSignupUser() {
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().body();
		JSONObject jsonResponse = new JSONObject(response.asString());
		String message = jsonResponse.getString("message");
		JSONObject userObject =  jsonResponse.getJSONObject("user");
		String username = userObject.getString("username");
		String email = userObject.getString("email");
		Assert.assertEquals(username, this.userPayload.getUsername());
		Assert.assertEquals(email, this.userPayload.getEmail());
		Assert.assertEquals(message, "User registered successfully");
		Assert.assertEquals(response.getStatusCode(), 201);
		
	}
	
	@Test(priority = 2)
	public void testGetUserById() {
		Response response = UserEndpoints.loginUser(this.userPayload.getEmail(),this.userPayload.getPassword());
		response.then().log().body();
		JSONObject jsonResponse = new JSONObject(response.asString());
		String message = jsonResponse.getString("message");
		JSONObject userObject =  jsonResponse.getJSONObject("user");
		String username = userObject.getString("username");
		String email = userObject.getString("email");
		token = jsonResponse.getString("token");
		Assert.assertEquals(username, this.userPayload.getUsername());
		Assert.assertEquals(email, this.userPayload.getEmail());
		Assert.assertEquals(message, "Login successful");
	Assert.assertEquals(response.getStatusCode(), 200);
	}
}
