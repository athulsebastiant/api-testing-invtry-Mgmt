package api.utilities;

import org.json.JSONObject;

import api.endpoints.UserEndpoints;
import io.restassured.response.Response;

public class AuthTokenUtility {
	private static String authToken = null;
	private static String DEFAULT_EMAIL = "testuser101@gmail.com";
	private static String DEFAULT_PASSWORD =  "password123";
	
	public static String getAuthToken() {
		if(authToken == null || authToken.isEmpty()) {
			authToken = loginAndGetToken(DEFAULT_EMAIL,DEFAULT_PASSWORD);
		}
		return authToken;
		
	}
	
	public static String getAuthToken(String email, String password) {
        return loginAndGetToken(email, password);
    }
	
	
	private static String loginAndGetToken(String email, String password) {
		try {
			Response response = UserEndpoints.loginUser(email, password);
			if(response.getStatusCode() != 200) {
				throw new RuntimeException("Login failed with status code: " + response.getStatusCode());
		          
			}
			
			JSONObject jsonResponse = new JSONObject(response.asString());
			String message = jsonResponse.getString("message");
			
			if(!"Login successful".equals(message)) {
				 throw new RuntimeException("Login failed with message: " + message);
			}
			
		String token = jsonResponse.getString("token");
		
		if(token == null || token.isEmpty()) {
			throw new RuntimeException("Token not found in login response");
		}
		
		System.out.println("Authentication successful. Token obtained.");
        return token;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error during authentication: " + e.getMessage());
			throw new RuntimeException("Authentication failed", e);
		}
	}
	
	public static String refreshToken() {
		authToken = null;
		return getAuthToken();
	}
	
	public static String refreshToken(String email, String password) {
        return loginAndGetToken(email, password);
    }
	
	public static void clearToken() {
        authToken = null;
    }
	
	public static String getCurrentToken() {
        return authToken;
    }
	
	public static boolean hasValidToken() {
        return authToken != null && !authToken.isEmpty();
    }

	
}
