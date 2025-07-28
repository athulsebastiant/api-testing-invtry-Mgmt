package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	
	
	public static Response createUser(User userPayload) {
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(userPayload)
				.when()
				.post(Routes.signup_url)
				;
		return response;
	}
	
	public static Response loginUser(String email, String password) {
		JSONObject data = new JSONObject();
		data.put("email",email);
		data.put("password", password);
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(data.toString())
				.when()
				.post(Routes.login_url);
		return response;
	}
	
	
}
