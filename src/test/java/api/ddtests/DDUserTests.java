package api.ddtests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDUserTests {
	public static String token;
	//@Test(priority = 1,dataProvider = "UserData",dataProviderClass = DataProviders.class )
	public void testSignupUser(String username, String email, String password) {
		User userPayload = new User();
		userPayload.setUsername(username);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().body();
		JSONObject jsonResponse = new JSONObject(response.asString());
		String message = jsonResponse.getString("message");
		JSONObject userObject =  jsonResponse.getJSONObject("user");
		String responseUserName = userObject.getString("username");
		String responseEmail = userObject.getString("email");
		Assert.assertEquals(responseUserName, userPayload.getUsername());
		Assert.assertEquals(responseEmail, userPayload.getEmail());
		Assert.assertEquals(message, "User registered successfully");
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test(priority = 2,dataProvider = "UserData",dataProviderClass = DataProviders.class)
    public void testLogin(String username, String email, String password) {
        System.out.println("Testing with email: " + email + ", password: " + password);
        Response response = UserEndpoints.loginUser(email,password);
		response.then().log().body();
		JSONObject jsonResponse = new JSONObject(response.asString());
		String message = jsonResponse.getString("message");
		JSONObject userObject =  jsonResponse.getJSONObject("user");
		String responseUsername = userObject.getString("username");
		String responseEmail = userObject.getString("email");
		token = jsonResponse.getString("token");
		Assert.assertEquals(responseUsername, username);
		Assert.assertEquals(responseEmail, email);
		Assert.assertEquals(message, "Login successful");
	Assert.assertEquals(response.getStatusCode(), 200);
    }
	
	
}
