package api.endpoints;
import static api.test.UserTests.token;
import static io.restassured.RestAssured.*;

import api.payload.Client;
import api.utilities.AuthTokenUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class ClientEndpoints {

	public static Response createClient(Client clientPayload) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.body(clientPayload)
				.when()
				.post(Routes.post_client_url)
				;
		return response;
	}
	
	public static Response getClientByid(String clientId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id", clientId)
				.when()
				.get(Routes.get_client_by_id_url)
				;
		return response;
	}
	
	public static Response updateClient(String clientid,Client clientPayload) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id",clientid)
				.body(clientPayload)
				.when()
				.put(Routes.put_client_url)
				;
		return response;
	}
	
	public static Response deleteClient(String clientId) {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.pathParam("id", clientId)
				.when()
				.delete(Routes.delete_client_url)
				;
		return response;
	}
	
	public static Response getAllClients() {
		String authToken = AuthTokenUtility.getAuthToken();
		Response response = 
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+authToken)
				.when()
				.get(Routes.get_clients_url)
				;
		return response;
	}
}
